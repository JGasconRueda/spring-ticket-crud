# Start with base image 
FROM openjdk:8-jdk-alpine
# Add a temporary volume 
VOLUME /tmp
# Expose Port 8080 
EXPOSE 8080
#  Add Application Jar File to the Container
ADD target/spring-ticket-crud-0.0.1-SNAPSHOT.jar springticketcrud.jar
# Run the JAR file 
ENTRYPOINT ["java","-jar","/springticketcrud.jar"]