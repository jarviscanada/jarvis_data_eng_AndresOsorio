#! /bin/bash

############### SET_UP ###############

# must be signed in as centos user
if [ $(whoami) != "centos" ]; then
    echo "must be signed in as centos user"
    exit 1
fi

# check that the psql container has already been created
if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -lt 2 ]; then
        echo "must run psql_docker.sh first to create the psql server"
        exit 1
fi 

# get args
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# to be able to access the psql server without interactive password 
export PGPASSWORD="$psql_password"

# check that args are provided
if [ $# -ne 5 ]; then
    echo "must provide psql_host, db_name, psql_user and psql_password"
    exit 1
fi

############### HOST_USAGE ###############

############### tmp vars ############### 

mem_info=$(cat /proc/meminfo)

# '-S m' switches output to Megabytes (MB), '-t' adds a timestamp
v_mem_info=$(vmstat -S m -t)

############### usage info ############### 

# in MB
memory_free=$(echo "$v_mem_info" | tail -1 | awk '{print $4}' | xargs) 

# in percentage
cpu_idle=$(echo "$v_mem_info" | tail -1 | awk '{print $15}' | xargs)

# in percentage
cpu_kernel=$(echo "$v_mem_info" | tail -1 | awk '{print $14}' | xargs)

# number of disk I/O; '-d' runs disk statistics
disk_io=$(echo "$(vmstat -d)" | tail -1 | awk '{print $10}' | xargs)

# in MB; root directory available disk; '-BMB' shows ouitput in blocks of MB
disk_available=$(echo "$(df -BMB /)" | tail -1 | awk '{print $4}' | sed s/MB// | xargs)

timestamp=$(date +'%y-%m-%d %T')

############### print vars ############### 

# echo "$memory_free"
# echo "$cpu_idle"
# echo "$cpu_kernel"
# echo "$disk_io"
# echo "$disk_available"
# echo "$timestamp"

############### PSQL ############### 

# database host_agent and table host_info must exist before we can proceed
if [ $(psql -h $psql_host -d $db_name -U $psql_user -c "table host_info" | wc -l) -lt 2 ]; then
    echo "database host_agent and table host_info must be created first; run host_info.sh"
    exit 1
fi

# get the id field of the entry with $hostname from table host_info since the table 
# host_usage references table host_info (its id field) through its host_id field
host_id=$(psql -h $psql_host -d $db_name -U $psql_user \
                -c "select id from host_info where hostname='$(hostname -f)';"\
                | tail -3 | head -1 | awk '{print $1}')

insert_st="insert into host_usage
            (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
            values
            ('"$timestamp"', "$host_id", "$memory_free", "$cpu_idle", "$cpu_kernel", "$disk_io",
            "$disk_available");"

# connect to db_name in container and perform the cmd insert_st
psql -h $psql_host -d $db_name -c "$insert_st" -U $psql_user

exit $?
