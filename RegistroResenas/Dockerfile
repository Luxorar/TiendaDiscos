FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -pl RegistroResenas -am package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/RegistroResenas/target/*.jar /app/app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
