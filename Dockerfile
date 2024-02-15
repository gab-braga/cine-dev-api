FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

# Instalação do MySQL durante a construção da imagem
FROM ubuntu:latest
RUN apt-get update
RUN DEBIAN_FRONTEND="noninteractive" apt-get install -y mysql-server

# Copia os scripts SQL para inicializar o banco de dados (com o novo nome do diretório)
COPY ./sql/ /docker-entrypoint-initdb.d/

# Construção da imagem final
FROM openjdk:17-jdk-slim

EXPOSE 8080
EXPOSE 3306

COPY --from=build /target/CineDev-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
