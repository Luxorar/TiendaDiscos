FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl RegistrarProductos -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/RegistrarProductos/target/*.jar /app/app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
