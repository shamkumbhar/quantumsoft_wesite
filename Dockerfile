FROM openjdk:17-jdk-slim
ADD target/quantomsoft.jar quantomsoft.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "quantomsoft.jar"]