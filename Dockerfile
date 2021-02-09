FROM openjdk:11-jdk-slim
WORKDIR /home/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "robofinance-0.0.1-SNAPSHOT.jar"]