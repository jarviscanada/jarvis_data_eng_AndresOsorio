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
    echo "must provide psql_host,psql_port, db_name, psql_user and psql_password"
    exit 1
fi

############### HOST_INFO ###############

############### tmp vars ###############

lscpu_out=$(lscpu)
mem_info=$(cat /proc/meminfo)

############### functions ###############

info() {
    echo "$lscpu_out"
}

############### hardware info ############### 

hostname=$(hostname -f)

# match CPU(s): with egrep; print the 2nd field with awk; xargs is a trick to 
# remove leading and trailing whote spaces
cpu_number=$(info | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

cpu_architecture=$(info | egrep "Architecture" | awk '{print $2}' | xargs)

cpu_model=$(info | egrep "Model name" | awk '{print $3, $4, $5, $6, $7}' | xargs)

cpu_mhz=$(info | egrep "CPU MHz" | awk '{print $3}' | xargs)

L2_cache=$(info | egrep "L2" | awk '{print $3}' | sed s/K// |  xargs) 

total_mem=$(echo "$mem_info" | egrep "MemTotal" | awk '{print $2}' | xargs)

timestamp=$(date +'%Y-%m-%d %T')

############### PSQL ############### 

insert_st="insert into host_info
            (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp)
            values
            ('"$hostname"', "$cpu_number", '"$cpu_architecture"', '"$cpu_model"', "$cpu_mhz", "$L2_cache",
            "$total_mem", '"$timestamp"');"

create_db_st="create database host_agent;"

# connect to psql container and create database db_name; discard error if already exists
psql -h $psql_host -p $psql_port -c "$create_db_st" -U $psql_user 2> /dev/null

# connect to db_name in container and create tables in file ddl.sql 
psql -h $psql_host -p $psql_port -d $db_name -f ../sql/ddl.sql -U $psql_user

# connect to db_name in container and perform the cmd insert_st
psql -h $psql_host -p $psql_port -d $db_name -c "$insert_st" -U $psql_user

exit $?
