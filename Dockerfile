FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install mysql-server -y
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y
COPY . .

COPY --from=build /sql/ /docker-entrypoint-initdb.d/

EXPOSE 3306

CMD ["mysqld"]

RUN mvn clean install

EXPOSE 8080

COPY --from=build /target/CineDev-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
