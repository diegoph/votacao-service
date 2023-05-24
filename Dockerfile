#
# Build
#
FROM maven:3.8.4-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

#
# Package
#
FROM openjdk:17-jdk-slim
LABEL maintainer="dgo.schmidt@gmail.com"
COPY --from=build /home/app/target/votacao-service.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]