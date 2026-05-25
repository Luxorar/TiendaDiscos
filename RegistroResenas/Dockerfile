FROM ghcr.io/graalvm/native-image-community:17 AS build
RUN microdnf install -y maven
WORKDIR /app
COPY . .
RUN mvn -pl RegistroResenas -am native:compile -DskipTests

FROM gcr.io/distroless/base
COPY --from=build /app/RegistroResenas/target/application /app/application
EXPOSE 8080
ENTRYPOINT ["/app/application"]
