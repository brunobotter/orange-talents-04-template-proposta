FROM openjdk:11-jdk
ARG JAR_FILE=target/proposta-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} proposta.jar
ENTRYPOINT ["java","-jar","/proposta.jar"]
EXPOSE $PROPOSTA_SERVER_PORT

#FROM maven:3.6.3-jdk-11 AS builder
 #COPY src /usr/src/app/src
 #COPY pom.xml /usr/src/app
 #RUN mvn -f /usr/src/app/pom.xml clean package
 #
 #FROM openjdk:11
 #COPY --from=builder /usr/src/app/target/proposta-0.0.1-SNAPSHOT.jar /usr/app/proposta.jar
 #EXPOSE 8080
 #ENTRYPOINT ["java","-jar","/proposta.jar"]