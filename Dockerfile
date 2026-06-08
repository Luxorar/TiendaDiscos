# Build stage: compiles all modules
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# Development stage (optional: run a specific module)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/AdministracionUsuario/target/*.jar ./app.jar
# Override CMD at runtime with: java -jar /app/<module>/target/*.jar
EXPOSE 8081 8082 8083 8084 8085 8086 8087 8088 8089 8091
