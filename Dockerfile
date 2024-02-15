FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y
COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim AS prod

RUN apt-get update
RUN apt-get install mysql-server -y

EXPOSE 3306

COPY --from=build /sql/ /docker-entrypoint-initdb.d/

CMD ["mysqld"]

EXPOSE 8080

COPY --from=build /target/deploy_render-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
