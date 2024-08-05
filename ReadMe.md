# 1. Getting Started


## 2. Known Issues

1. findAll() is  code smell. It should have been done by Requesting say a page of 10 records but couldn't reach pagination.
2. The graph library tries to bring in all the relations lazily but it makes several additional queries. Some would  say, of course that is what lazy approach but in reality, when pulling in 10-20 elements for a page, you don't want 50 queries to run on the DB. A simple join query could work. I should have gone for the join query on datatable and only show case lazy loading during employee select. Trying to do achieve both with annotations is complex.
3. I failed to add test cases. the library I am using avoids creating GraphQL types - the contracts. It can read the POJOs you create and map the payload from postman/svelte directly. The problem is that when I didnt realize that the GraphQL types are contracts and the ease of Graph to Java mapping (SPQR) is not supported on Spring 3, So I got caught because tests were running on Spring 3 for GraphQL but SPQR did not work and I would have to write all the graphQL contracts again because test cases are not working. Considering that this would break the heart of the application, I stuck to SPQR.
4. As a tradeoff, you will observe, I have dockerized both front end and back end apps.
5. Couldn't add validations for length of data fields etc.
6. Couldn't add Java docs or Sonar Cube.
7. At present there is no security library implemented on the back-end side. As graphql requires all data requests on the same endpoint, I think this can be done via AOP aspects.
8. Some classes are commented and 

## 3. Background

1. Contact and Addresss both are separate tables so that adding another line of address is easy.

# 4. REQUIREMENTS

1. Please have docker running on your machine. 
2. I hope you wouldn't require JDK 17 but it is being used in the project for Java Records.

# 5. Tests

1.Tests of docker done: 1 mac


`docker-compose up build --d `

This is the only command to run if the dockerizing works well.

## PORTS NEED TO BE AVAILABLE

- Spring boot is running on 8081 
- mysql is running on 3307 
- Svelte app works on 8082. In case you need to run svelte inside IDE, it runs on 8080

For the database port to not be used. You need to ensure that the port 3306, 3307 is not already in use.
If you have MySQL installed on your machine, I suggest you stop the service so that the port is free.

On Mac, the way to check if the port is free or is being used is

`sudo lsof -i: 3306`

This command above will tell you the pid of the process. If being used,  you could kill the process by pid

`kill pid`

pid will be

Same above goes for port 8081, 8080.

On Windows, one could merely go to services and stop mysql.

If the dockerizing doesn't work, the other option is listen on port 3307 on localhost
create a database called employeemanagementdb. And just run the project  via intellij IDEA

# Create Database
`create database employeemanagementdb;`

# Maven commands to run incase you want to start the server locally without Docker. 


Flyway migrations will add the tables on startup.

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