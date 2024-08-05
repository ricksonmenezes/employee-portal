# Getting Started

Please have docker running on your machine. I hope you wouldn't require JDK 17 but that is what the java is compiling to. 
Tests of docker done: 1 mac


`docker-compose up build --d `

This is the only command to run if the dockerizing works well.

## PORTS

Spring boot is running on 8081
mysql is running on 3307 (or 3306?)
Svelte app works on 3302. Default is 5000 but it has been overrriden. 

For the database port to not be used. You need to ensure that the port 3306, 3307 is not already in use.
If you have MySQL installed on your machine, I suggest you stop the service so that the port is free.

On Mac, the way to check if the port is free or is being used is

`sudo lsof -i: 3306`


This command above will tell you the pid of the process. If being used,  you could kill the process by pid

`kill pid`

pid will be

Same above goes for port 8081

On Windows, one could merely go to services and stop mysql.

If the dockerizing doesn't work, the other option is listen on port 3307 on localhost
create a database called employeemanagementdb. And just run it via intellij IDEA or another IDE.

Flyway migrations will add the tables on startup.

## Known Issues

1. findAll() is  code smell. It should have been done by Requesting say a page of 10 records but couldn't reach pagination.
2. The graph library tries to bring in all the relations lazily but it makes several additional queries. Some would  say, of course that is what lazy approach but in reality, when pulling in 10-20 elements for a page, you don't want 50 queries to run on the DB. A simple join query could work. I should have gone for the join query on datatable and only show case lazy loading during employee select. Trying to do achieve both with annotations is complex.
3. I failed to add test cases. the library I am using avoids creating GraphQL types - the contracts. It can read the POJOs you create and map to them. The problem is that when I did realize that the GraphQL types are contracts and the ease of Graph to Java mapping (SPQR) is not supported on Spring 3, I was a bit caught. What I mean is that I was able to run some tests on Spring 3 with Graph Types on Spring 3 but not with SPQR which works on Spring 2 and upgrading everything at the end was too painful.
4. As a tradeoff, you will observe, I have dockerized both front end and back end apps.
5. Couldn't add validations for length of data fields etc.
6. Couldn't add Java docs or Sonar Cube.
7. At present there is no security library implemented on the back-end side.
8. As graphql requires all data requests on the same endpoint, I think this can be done via AOP aspects.

## IDEAS

1. Contact and Addresss both are separate tables so that adding another line of address is easy.
2.



## OTHER USEFUL COMMANDS
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
