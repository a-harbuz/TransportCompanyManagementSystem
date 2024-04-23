FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ./target/TransportCompanyManagementSystem-0.0.1-SNAPSHOT.jar /myproj/app.jar
ENTRYPOINT ["java","-jar","/myproj/app.jar"]
