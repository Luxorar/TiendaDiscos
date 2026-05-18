FROM ghcr.io/graalvm/native-image-community:17 AS builder

RUN microdnf install -y maven

WORKDIR /app

COPY pom.xml ./parent-pom.xml
RUN mvn install -f parent-pom.xml -N -B

COPY AdministracionUsuario/pom.xml ./pom.xml
COPY AdministracionUsuario/src/ src/

RUN mvn dependency:go-offline -B
RUN mvn -Pnative native:compile -DskipTests
RUN ls -la /app/target/

FROM gcr.io/distroless/base-debian12

WORKDIR /app

COPY --from=builder /app/target/AdministracionUsuario .

EXPOSE 8080

ENTRYPOINT ["/app/AdministracionUsuario"]