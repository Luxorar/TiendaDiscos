FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl AdministracionDescuentos -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/AdministracionDescuentos/target/*.jar /app/app.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
