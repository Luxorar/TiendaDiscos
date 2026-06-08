FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl ManejoStock -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/ManejoStock/target/*.jar /app/app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
