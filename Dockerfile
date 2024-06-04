FROM openjdk:21
ARG JAR_FILE=target/*.jar
#COPY ./target/TransportCompanyManagementSystem.jar /myproj/app.jar
COPY ./out/artifacts/TransportCompanyManagementSystem_jar/TransportCompanyManagementSystem.jar /myproj/app.jar
ENTRYPOINT ["java","-jar","/myproj/app.jar"]
