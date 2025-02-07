FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/lab1-1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]