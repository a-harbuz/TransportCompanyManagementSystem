FROM openjdk:21
#EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ./target/TransportCompanyManagementSystem.jar /myproj/app.jar
ENTRYPOINT ["java","-jar","/myproj/app.jar"]
