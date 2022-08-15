FROM bellsoft/liberica-openjdk-alpine:latest
COPY /target/springwork-17-0.0.1-SNAPSHOT.jar /library.app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/library.app"]