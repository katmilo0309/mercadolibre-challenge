 
FROM ubuntu:16.04

# Install prerequisites
RUN apt-get update && apt-get install -y \
curl
CMD /bin/bash
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/bridge-challenge-seg360.jar /app/
ENTRYPOINT ["java", "-jar", "bridge-challenge-seg360.jar"]