
# Mercado libre Challenge Seg 360

The challenge-seg360 solution was designed using technologies such as Docker, Hystrix and Spring Framwork, 
it consists of 4 micro-service applications.

- challenge-server: Eureka server implementation
- cloud-gateway: Main access point to the microservices system
- challenge-seg360: Consume the free market api and persist the data consulted in a postgres database
- bridge-challenge-seg-360: Intermediate layer between the gateway and the layer that manipulates the data

![enter image description here](https://github.com/katmilo0309/mercadolibre-challenge/blob/master/images/diagram.png)

## Prerequisites

Java 1.8 
Docker
Docker-compose

## Download

To download the project in a console write git clone https://github.com/katmilo0309/mercadolibre-challenge.git


## Installation
After cloning the repository, enter the mercadolibre-challenge directory and execute the following commands:

```sh
#We create a network to communicate the containers

$ docker network create challenge-net

$ cd challenge-server
$ docker image build -t challenge-server .
$ docker container run --network challenge-net --name challenge-server -p 8761:8761 -d challenge-server

$ cd ../bridge-challenge-seg-360
$ docker image build -t bridge-challenge .
$ docker container run --network challenge-net --name  bridge-challenge -p 8086:8086 -d  bridge-challenge

$ cd ../cloud-gateway
$ docker image build -t cloud-gateway .
$ docker container run --network challenge-net --name  cloud-gateway -p 8080:8080 -d  cloud-gateway

$ cd ../challenge-seg360

# This microservice is executed with the docker-compose instruction to simultaneously launch the app 
and the database. It is advisable to use a separate terminal to execute docker compose, since in the 
terminal that is executed, the log will be visible in real time, unlike other executions.
$ docker-compose up --build 
```

## Run it

If we want to confirm through the terminal if the applications are deployed, we must execute the command 
```sh
$ docker ps

#the output should be similar to this

CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS                    NAMES
364e4c4ee309        cloud-gateway             "java -jar cloud-gat…"   2 minutes ago       Up 2 minutes        0.0.0.0:8080->8080/tcp   cloud-gateway
040bd048a165        bridge-challenge          "java -jar bridge-ch…"   8 minutes ago       Up 8 minutes        0.0.0.0:8086->8086/tcp   bridge-challenge
7761c33681aa        challenge-server          "java -jar challenge…"   12 minutes ago      Up 12 minutes       0.0.0.0:8761->8761/tcp   challenge-server
866bce365a61        challenge-seg360:latest   "java -jar challenge…"   19 minutes ago      Up 3 minutes        0.0.0.0:8085->8085/tcp   challenge-seg360
bfd97ccb01a0        postgres                  "docker-entrypoint.s…"   12 hours ago        Up 3 minutes        0.0.0.0:5432->5432/tcp   postgres-db

# And if we execute the command 

$ docker network inspect challenge-net
```

podemos ver la siguiente salida
```json
[
    {
        "Name": "challenge-net",
        "Id": "60b8c1215875a360b6318a5203b7c6613f32e18b102942024bea7cf98835f48c",
        "Created": "2020-08-04T13:20:45.857304296Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    "Subnet": "172.23.0.0/16",
                    "Gateway": "172.23.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "040bd048a1652c86f33e6c0dda003ebec9f3e63260bb6eda5e17a1847779f8a0": {
                "Name": "bridge-challenge",
                "EndpointID": "583d8b49814debec29659c11fd607683ae8432c04fa8de70c9044e49f350523a",
                "MacAddress": "02:42:ac:17:00:03",
                "IPv4Address": "172.23.0.3/16",
                "IPv6Address": ""
            },
            "364e4c4ee3095640a515ec9c2328e5b223a93a47e7083110f70ea4442537e4e6": {
                "Name": "cloud-gateway",
                "EndpointID": "c6299f733823f5d8084ec1a8f6b3b6673fdd5d971d3cff8e98d057b357a80116",
                "MacAddress": "02:42:ac:17:00:06",
                "IPv4Address": "172.23.0.6/16",
                "IPv6Address": ""
            },
            "7761c33681aab02d656e5bce6c6ece91872ac25c7fc27b998bdac99bb635eec1": {
                "Name": "challenge-server",
                "EndpointID": "28f21d2d3e207583fa804de7ddd069417dfe7413525b84ca38e5cfd56203efcd",
                "MacAddress": "02:42:ac:17:00:02",
                "IPv4Address": "172.23.0.2/16",
                "IPv6Address": ""
            },
            "866bce365a610a49d8a1c952e41a50857d56d40f5f030e4d7f30e304376bc605": {
                "Name": "challenge-seg360",
                "EndpointID": "5b80d65938e064eed93099eff4924627b47352efa1219a241c01f4043b7db14b",
                "MacAddress": "02:42:ac:17:00:05",
                "IPv4Address": "172.23.0.5/16",
                "IPv6Address": ""
            },
            "bfd97ccb01a04205745bf53abd0d8d9ea71892d9ac8a96c16c0fd3be79df383c": {
                "Name": "postgres-db",
                "EndpointID": "5a69bd7b98603fe704972e37395a4c5fd23a847de4f8632643633daba9732d10",
                "MacAddress": "02:42:ac:17:00:04",
                "IPv4Address": "172.23.0.4/16",
                "IPv6Address": ""
            }
        },
        "Options": {},
        "Labels": {}
    }
]

```


When executing the deployment of the 4 applications we can enter the eureka server address 
[http://localhost:8761/](http://localhost:8761/) and see that 3 instances are available.

![enter image description here](https://github.com/katmilo0309/mercadolibre-challenge/blob/master/images/eureka.jpg)

To be able to test the services individually we can enter the url's:
- [http://localhost:8086/swagger-ui.html](http://localhost:8086/swagger-ui.html)
- [http://localhost:8085/swagger-ui.html](http://localhost:8085/swagger-ui.html)

used id MLU460998489  for testing

## Additional notes

- To unsubscribe the services in docker-compose press Ctrl + C
- Make sure that in some cases you need to run the Docker and Docker-compose Commands as administrator. 
(In the case of Ubuntu with sudo, for Windows it was not necessary, but it is necessary to run the terminal 
as Administrator)
- If after running docker-compose up or Docker run you get the error "port is already allocated" or "The container 
name '/container-name' is already"run docker ps and copy the name of the container to stop, then run docker stop container_name and try again

Example
```sh
 $ docker container stop challenge-server
 $ docker container rm  challenge-server
```

And run again
