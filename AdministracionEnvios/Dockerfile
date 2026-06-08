FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl AdministracionEnvios -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/AdministracionEnvios/target/*.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
