FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl RegistrarDiscos -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/RegistrarDiscos/target/*.jar /app/app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
