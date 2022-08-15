FROM maven:3.8.6-openjdk-18 as build
RUN mkdir /project
WORKDIR /project
ADD ./pom.xml /project
RUN mvn dependency:resolve
ADD ./src /project/src
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:18-jdk-alpine
RUN mkdir /app
COPY --from=build /project/target/springwork-17-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "springwork-17-0.0.1-SNAPSHOT.jar"]
