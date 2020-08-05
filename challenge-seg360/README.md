# Docker-microservice

challenge-seg360 is a spring boot application deployed in docker and connected to mysql and consume the mercado libre item apis.

## Description

This solution was designed using the spring-boot framework and was deployed in docker containers. In turn, we use docker-compose to manage and communicate the application and database containers. Resilience4j was used to manage error tolerance.


## Prerequisites

Java 1.8 
Docker
Docker-compose

## Download

To download the project in a console write git clone https://github.com/katmilo0309/challenge-seg360.git


## Installation
Type cd docker-microservice, then execute de command 
```sh
$ docker-compose build
```

## Run it

To start the application type the command 
```sh
$ docker-compose up
```


To run the main service go to [http://localhost:8060/swagger-ui.html](http://localhost:8060/swagger-ui.html) and in the Items controller Press tryout Paste the following id: MLU460998489 and press execute and you can see the result. Every time we search for an item, the service searches for it in the database, if it is not found there, it consults it from the api and then saves it in the database

## Additional notes

- To unsubscribe the services press Ctrl + C
- Make sure that in some cases you need to run the Docker and Docker-compose Commands as administrator. (In the case of Ubuntu with sudo, for Windows it was not necessary, but it is necessary to run the terminal as Administrator)
- If after running docker-compose up you get the error "port is already allocated" run docker ps and copy the name of the container to stop, then run docker stop container_name and try again

## Health

As for the enpoint health, I have not yet finished, I need to carry out a research work since in the actuator management I have no experience.

[http://localhost:8060/health](http://localhost:8060/health)