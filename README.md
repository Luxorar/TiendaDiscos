# TiendaDiscos

## Cambios realizados — Dockerfile y docker-compose

### Objetivo
Corregir y completar la configuración Docker del proyecto de microservicios para que todos los módulos puedan construirse y ejecutarse correctamente.

### Archivos creados
- **`.dockerignore`**: Excluye `.git`, `target/`, `.idea/`, `.vscode/`, `.github/`, `*.md`, `docker-compose.yml`, `Dockerfile`, `mysql/` del contexto de build para acelerar las construcciones Docker.

### Archivos modificados

#### `docker-compose.yml`
- Se cambió `mysql:latest` → `mysql:8.0` (versión fija).
- Se agregaron los **10 microservicios** como servicios:
  - `administracion-usuario` (8081)
  - `administracion-envios` (8082)
  - `registrar-productos` (8083)
  - `registrar-sede` (8084)
  - `registro-resenas` (8085)
  - `registrar-discos` (8086)
  - `administracion-descuentos` (8087)
  - `carrito-compras` (8088)
  - `manejo-stock` (8089)
  - `administracion-ventas` (8091)
- Cada servicio expone su puerto, usa variables de entorno `SPRING_DATASOURCE_URL` (apuntando al contenedor `mysql`), depende de MySQL con healthcheck, y comparte la red `tiendadiscos-net`.
- Se creó la red bridge `tiendadiscos-net`.

#### `Dockerfile` (raíz)
- Reemplazado: `openjdk:17-slim` (deprecado) → `eclipse-temurin:17-jdk` y `eclipse-temurin:17-jre`.
- Ahora es un builder multi-etapa: compila todos los módulos y copia los JARs resultantes.
- Expone todos los puertos (8081–8089, 8091).

#### Dockerfiles de módulos (x10)
- `EXPOSE 8080` → puerto real según `application.properties`:
  - AdministracionUsuario: 8081
  - AdministracionEnvios: 8082
  - RegistrarProductos: 8083
  - RegistrarSede: 8084
  - RegistroResenas: 8085
  - RegistrarDiscos: 8086
  - AdministracionDescuentos: 8087
  - CarritoCompras: 8088
  - ManejoStock: 8089
  - AdministracionVentas: 8091
- Comando `mvn` corregido: se agregó `-Pnative` para activar el perfil nativo de Spring Boot 3.2.0, necesario para que el goal `native:compile` del plugin `native-maven-plugin` esté disponible.
  - Antes: `mvn -pl <modulo> -am native:compile -DskipTests`
  - Después: `mvn -Pnative -pl <modulo> -am native:compile -DskipTests`

### Iteración 2 — Cambio a JVM estándar

#### Problema detectado
`-Pnative` activaba el `native-maven-plugin` en **todos** los módulos del reactor, incluyendo el parent POM (`<packaging>pom</packaging>`) que no tiene clase main, causando:
```
Error: Please specify class containing the main entry point method.
```
Además, la compilación nativa con GraalVM era extremadamente lenta (~30+ min por módulo).

#### Solución
Se reemplazaron los 10 Dockerfiles de módulos de **native-image (GraalVM)** a **JVM estándar**:

**Antes (native-image):**
```dockerfile
FROM ghcr.io/graalvm/native-image-community:17 AS build
RUN microdnf install -y maven
...
RUN mvn -Pnative -pl <modulo> -am native:compile -DskipTests
...
FROM gcr.io/distroless/base
```

**Después (JVM):**
```dockerfile
FROM maven:3-eclipse-temurin-17 AS build
...
RUN mvn -pl <modulo> -am package -DskipTests
...
FROM eclipse-temurin:17-jre
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

#### Cambios específicos
- **Base image build**: `ghcr.io/graalvm/native-image-community:17` → `maven:3-eclipse-temurin-17` (incluye Maven preinstalado)
- **Base image runtime**: `gcr.io/distroless/base` → `eclipse-temurin:17-jre`
- **Comando Maven**: `mvn -Pnative ... native:compile` → `mvn ... package` (compila JAR estándar)
- **Entrypoint**: `["/app/application"]` → `["java", "-jar", "/app/app.jar"]`
- **Puertos `EXPOSE`**: Corregidos en los 10 módulos (8081–8091)

### No modificado
- **`application.properties`** de cada módulo: Se mantienen con `localhost:3306` para compatibilidad con desarrollo local. En Docker, las variables de entorno `SPRING_DATASOURCE_URL` del `docker-compose.yml` sobreescriben estos valores automáticamente.
- **`mysql/init.sql`**: Ya crea las 10 bases de datos necesarias. Sin cambios.

### Cómo usar
```bash
# Construir todos los servicios
docker compose build

# Construir un servicio específico
docker compose build manejo-stock

# Levantar todo (construye + inicia)
docker compose up -d

# Ver logs de un servicio
docker compose logs -f manejo-stock

# Detener todo
docker compose down
```