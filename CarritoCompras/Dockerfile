FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl CarritoCompras -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/CarritoCompras/target/*.jar /app/app.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
