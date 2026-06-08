FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl AdministracionVentas -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/AdministracionVentas/target/*.jar /app/app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
