#FROM openjdk
#COPY ./src/main/java/de/telran/transportcompanymanagementsystem/ /proj
#WORKDIR /proj
#CMD ["java", "TransportCompanyManagementSystemApplication.java"]


FROM openjdk:21
COPY ./target/TransportCompanyManagementSystem-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
CMD ["java","-jar", "TransportCompanyManagementSystem-0.0.1-SNAPSHOT.jar"]

#docker run -p 8080:8080 36b03319582e
