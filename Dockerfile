FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get update && apt-get install -y \
    mysql-server \
    openjdk-17-jdk \
    maven

COPY . .
COPY ./sql/ /docker-entrypoint-initdb.d/

EXPOSE 3306

CMD ["mysqld"]

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/CineDev-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
