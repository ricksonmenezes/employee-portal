# 1. Getting Started


## 2. Known Issues

1. findAll() is  code smell. It should have been done by Requesting say a page of 10 records but couldn't reach pagination.
2. The graph library tries to bring in all the relations lazily but it makes several additional queries. Some would  say, of course that is what lazy approach but in reality, when pulling in 10-20 elements for a page, you don't want 50 queries to run on the DB. A simple join query could work. I should have gone for the join query on datatable and only show case lazy loading during employee select. Trying to do achieve both with annotations is complex.
3. I failed to add test cases. the library I am using avoids creating GraphQL types - the contracts. It can read the POJOs you create and map the payload from postman/svelte directly. The problem is that when I didnt realize that the GraphQL types are contracts and the ease of Graph to Java mapping (SPQR) is not supported on Spring 3, So I got caught because tests were running on Spring 3 for GraphQL but SPQR did not work and I would have to write all the graphQL contracts again because test cases are not working. Considering that this would break the heart of the application, I stuck to SPQR.
4. 
5. Couldn't add validations for length of data fields etc.
6. Couldn't add Java docs or Sonar Cube.
7. At present there is no security library implemented on the back-end side. As graphql requires all data requests on the same endpoint, I think this can be done via AOP aspects.
8. Some classes are commented. No cleanup done. 

## 3. Background

1. Contact and Addresss both are separate tables so that adding another line of address is easy.
2. Reason why test cases are missing: the library I am using avoids creating GraphQL types - the contracts. It can read the POJOs you create and map the payload from postman/svelte directly. The problem is that when I didnt realize that the GraphQL types are contracts and the ease of Graph to Java mapping (SPQR) is not supported on Spring 3, So I got caught because tests were running on Spring 3 for GraphQL but SPQR did not work and I would have to write all the graphQL contracts again because test cases are not working. Considering that this would break the heart of the application, I stuck to SPQR.
3. As a tradeoff for lack of test cases, I have dockerized both front end and back end apps.

# Features I couldn't manage to do

1. Reason why test cases are missing: the library I am using avoids creating GraphQL types - the contracts. It can read the POJOs you create and map the payload from postman/svelte directly. The problem is that when I didnt realize that the GraphQL types are contracts and the ease of Graph to Java mapping (SPQR) is not supported on Spring 3, So I got caught because tests were running on Spring 3 for GraphQL but SPQR did not work and I would have to write all the graphQL contracts again because test cases are not working. Considering that this would break the heart of the application, I stuck to SPQR.
2. As a tradeoff for lack of test cases, I have dockerized both front end and back end a

# 4. REQUIREMENTS

1. Please have docker running on your machine. 
2. I hope you wouldn't require JDK 17 but it is being used in the project for Java Records. (tested on windows, was not required)
3. Please make sure you are logged into docker. On windows, git bash often can give issues with "docker-credentials". It's safe to go to Docker Desktop app and start the terminal window there 

## 5 PORTS NEED TO BE AVAILABLE

- Spring boot is running on 8081
- mysql is running on 3307
- Svelte app works on 8082. In case you need to run svelte inside IDE, it runs on 8080

For the database port to not be used. You need to ensure that the port 3306, 3307 is not already in use.
If you have MySQL installed on your machine, I suggest you stop the service so that the port is free.

On Mac, the way to check if the port is free or is being used is

`sudo lsof -i: 3307`

This command above will tell you the pid of the process. If being used,  you could kill the process by pid

`kill pid`

pid will be

Same above goes for port 8081, 8080.

On Windows, one could merely go to services and stop mysql.

If the dockerizing doesn't work, the other option is listen on port 3307 on localhost
create a database called employeemanagementdb. And just run the project  via intellij IDEA

# Create Database
`create database employeemanagementdb;`

# Maven commands to run in case you want to start the server locally without Docker.
1. maven is whatever maven is available on your system and compatible with this project. This project uses apache maven 3.9.6. It may have a shortcut like ./mvnw etc

To give you an example, this is what I get when I do maven --version on my machine
./mvnw --version
Apache Maven 3.9.7 (8b094c9513efc1b9ce2d952b3b9c8eaedaf8cbf0)
Maven home: /Users/ricksonmenezes/.m2/wrapper/dists/apache-maven-3.9.7/2a4cb831
Java version: 21.0.2, vendor: Eclipse Adoptium, runtime: /Users/ricksonmenezes/.sdkman/candidates/java/21.0.2-tem

`maven clean package`

In order to run below, you need to first start database service - either the docker file or configure database via local MySQL
To try the  mysql docker file

Using Docker mysql
`docker compose --env-file .env  -f deployment/docker-compose/infra.yml up -d`

`java -jar target/employeeportal-0.0.1-SNAPSHOT.jar`

Flyway migrations will add the tables on startup.


# 6. COMMANDS TO RUN

1. Go to an empty folder in your machine open on terminal (choice of terminal discussed on 4.3)
2. `git clone https://github.com/ricksonmenezes/employee-portal`
3. you will find a new folder employee-portal. cd /employee-portal
4. once inside, run command below

`docker compose up --build`

If all goes well, please move on to start the employee-svelte docker container. 

# 7. Tests done on docker

1.Tests of docker done: 1 mac and 1 windows

This is the only command to run if the dockerizing works well.


## OTHER USEFUL COMMANDS FOR DEV WORK
./mvnw clean verify;

./mvnw  spring-boot:build-image -DskipTests

docker compose -f deployment/docker-compose/infra.yml up -d
docker compose -f deployment/docker-compose/apps.yml up -d
OR
docker compose -f deployment/./docker-compose/infra.yml -f deployment/docker-compose/apps.yml up -d
docker compose -f deployment/docker-compose/infra.yml --env-file .env -f deployment/docker-compose/apps.yml up -d



# To only run the database
docker compose --env-file .env  -f deployment/docker-compose/infra.yml up -d

# To only run the app
docker compose --env-file .env  -f deployment/docker-compose/apps.yml up -d


# bash into mysql shell
docker exec -it employee-management-db mysql -u user -p employeemanagementdb

docker-compose up build --d  
docker exec -it employee-management-db mysql -u user -p employeemanagementdb

# Users for logging in to svelte app
username: user
password: userpassword

username: admin
password: adminpassword