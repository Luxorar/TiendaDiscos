FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl AdministracionUsuario -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/AdministracionUsuario/target/*.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
