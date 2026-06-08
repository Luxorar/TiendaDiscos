FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl RegistrarSede -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/RegistrarSede/target/*.jar /app/app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
