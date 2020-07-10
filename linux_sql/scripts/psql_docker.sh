#! /bin/bash

# must be signed in as centos (sudo su centos)
if [ $(whoami) != "centos" ]; then
    echo "must be signed in as centos"
    exit 1
fi

# get arguments
action=$1
db_username=$2
db_password=$3
export PGPASSWORD="$db_password"

# check that first arg is valid
case $action in
	create | start | stop )
	:
	;;
	* )
	echo First arg must be "[create | start | stop]"
	exit 1
esac

# functions
# chekc that if the container has already been created
# if already created wc -l should output 2
created() {
	if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ] 
	then
		return 0
	else
		return 1
	fi
}

# check if the container is running; if wc -l = 2 then it is running
running() {
	if [ $(docker ps -f name=jrvs-psql | wc -l) -eq 2 ] 
	then
		return 0
	else
		return 1
	fi
}

create() {
	# cmd2 only runs iff cmd1 exits with a non-zero status;
	# if cmd exits with 0 it means docker deamon is already running
	# and cmd2 is not run
	systemctl status docker || systemctl start docker

	if created 
	then
		echo "container already created"; exit 1
	fi

	# if we get here then the container has not been created, create it
	# get the latest docker image
	docker pull postgres

	# create a new volume to connect to container
	docker volume create pgdata

	# create a container using psql image with name=jrvs-psql
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -e POSTGRES_USER=${db_username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres	

	# confirm that the container was created
	if ! created
	then
		echo "container could not be created"; exit 1
	fi

	# exit with exit status of the creation of container
	 exit $?
}

# start docker is docker deamon is not running
# systemctl status docker || systemctl start docker
case $action in
	create ) 
	if [ $# -eq 3 ]	
	then
		create
	else
		echo "must provide username and password args for the create option"
		exit 1
	fi
	;;
	start )
	if ! created
	then
		echo "container has not been created"; exit 1
	elif running
	then
		echo "container is already running"; exit 1
	else
		docker container start jrvs-psql
		exit $?
	fi
	;;
	stop )
	if ! created
	then
		echo "container has not been created"; exit 1
	elif ! running
	then
		echo "container is not running"; exit 1
	else
		docker container stop jrvs-psql
	fi
	exit $?
esac
