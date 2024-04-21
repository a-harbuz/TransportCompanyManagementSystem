FROM openjdk
#COPY ./src/main/java/de/telran/transportcompanymanagementsystem/ .
#WORKDIR /java
RUN mkdir -p /usr/src/app/
WORKDIR /usr/src/app/
COPY . /usr/src/app/

RUN javac ./src/main/java/de/telran/transportcompanymanagementsystem/TransportCompanyManagementSystemApplication.java
CMD ["java", "transportcompanymanagementsystem"]
