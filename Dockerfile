# Estágio para a configuração do MySQL
FROM mysql:latest AS mysql_stage
COPY ./sql/ /docker-entrypoint-initdb.d/

# Estágio de construção da aplicação Java
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

# Estágio final para a execução da aplicação Java
FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /app/target/CineDev-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]