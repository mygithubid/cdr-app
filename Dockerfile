FROM openjdk:17
MAINTAINER joel.com
COPY target/cdr-app-0.0.1-SNAPSHOT.jar cdr-app.jar
ENTRYPOINT ["java","-jar","/cdr-app.jar"]