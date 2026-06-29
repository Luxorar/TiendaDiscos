# TiendaDiscos

Sistema de microservicios para la gestión de una tienda de discos. Cada módulo es un servicio Spring Boot independiente que se comunica via REST y Feign Clients.

## Arquitectura

```
[Cliente] → [Microservicios] → [MySQL]
                ↕
         [Feign Clients]
```

10 microservicios + 1 API Gateway comparten una base de datos MySQL única.

## Microservicios

| Módulo | Puerto | Descripción |
|--------|--------|-------------|
| AdministracionUsuario | 8081 | Gestión de usuarios y administradores |
| AdministracionEnvios | 8082 | Gestión de envíos y estados |
| RegistrarProductos | 8083 | Catálogo de productos |
| RegistrarSede | 8084 | Gestión de sedes |
| RegistroResenas | 8085 | Reseñas de discos |
| RegistrarDiscos | 8086 | Catálogo de discos |
| AdministracionDescuentos | 8087 | Descuentos y promociones |
| CarritoCompras | 8088 | Carrito de compras por usuario |
| ManejoStock | 8089 | Control de inventario |
| AdministracionVentas | 8091 | Registro y consulta de ventas |
| api-gateway | - | API Gateway (pendiente de configuración) |

## Stack Tecnológico

- **Java 17** — JDK/Temurin
- **Spring Boot 3.2.0** — Framework principal
- **Spring Data JPA** — Persistencia
- **Spring Cloud OpenFeign** — Comunicación entre microservicios
- **Flyway** — Migraciones de base de datos
- **MySQL 8.0** — Base de datos
- **Swagger / OpenAPI** — Documentación de APIs
- **Maven** — Build
- **Docker** — Contenedores

## Requisitos

- Java 17+
- Maven 3.8+
- MySQL 8.0
- Docker y Docker Compose

## Ejecución Local

### 1. Base de datos

```sql
CREATE DATABASE IF NOT EXISTS tiendadiscos;
```

Cada módulo aplica sus migraciones Flyway automáticamente al iniciar.


### 2. Ejecutar con Docker

```bash
# Construir y levantar todos los servicios
docker compose up -d

# Construir un servicio específico
docker compose build <modulo>

# Ver logs
docker compose logs -f <modulo>

# Detener todo
docker compose down
```

## API Documentation

Cada microservicio expone Swagger UI en:

```
http://localhost:<puerto>/swagger
```

Ejemplo: `http://localhost:8081/swagger`

## Endpoints Principales

### AdministracionUsuario (`/api/v1/admin`)
- `GET` — Obtener todos los usuarios
- `POST` — Crear usuario
- `GET /id/{id}` — Usuario por ID
- `GET /name/{name}` — Usuario por nombre
- `PUT /{id}` — Actualizar usuario
- `PUT /id/{id}` — Actualizar puntaje
- `DELETE /{id}` — Eliminar usuario
- `GET /admins` — Todos los administradores
- `POST /admins` — Crear administrador
- `GET /admins/{id}` — Admin por ID
- `GET /admins/name/{name}` — Admin por nombre
- `PUT /admins/{id}` — Actualizar admin
- `DELETE /admins/{id}` — Eliminar admin

### AdministracionEnvios (`/api/v1/envios`)
- `GET` — Todos los envíos
- `POST` — Crear envío
- `PUT /{id}` — Cambiar estado
- `PUT /dir/{id}` — Cambiar dirección
- `DELETE /{id}` — Eliminar envío

### AdministracionDescuentos (`/api/v1/descuentos`)
- `GET` — Todos los descuentos
- `GET /{id}` — Descuento por ID
- `GET /buscar` — Descuento por nombre
- `POST` — Crear descuento
- `PUT /{id}` — Actualizar descuento
- `DELETE /{id}` — Eliminar descuento
- `POST /{nombre}/discos/{id}` — Agregar disco
- `DELETE /descuento/{nombre}` — Quitar disco
- `POST /producto/{nombre}` — Agregar producto
- `DELETE /producto/{nombre}` — Quitar producto

### AdministracionVentas (`/api/v1/ventas`)
- `GET` — Todas las ventas (con filtros opcionales)
- `POST` — Crear venta
- `GET /id/{id}` — Venta por ID
- `GET /user/{u}` — Ventas por usuario
- `GET /productos/{id}` — Productos de una venta
- `DELETE /{id}` — Eliminar venta

### CarritoCompras (`/api/v1/carrito`)
- `GET` — Todos los carritos
- `GET /{userId}` — Carrito por usuario
- `POST` — Crear carrito
- `POST /{userId}/discos/{idDisco}` — Agregar disco
- `POST /{userId}/productos/{idProducto}` — Agregar producto
- `PUT /{userId}` — Actualizar descuento
- `PUT /{userId}/productos` — Modificar producto
- `PUT /{userId}/discos` — Modificar disco
- `GET /{userId}/productos` — Listar productos
- `GET /{userId}/productos/{id}` — Producto específico
- `GET /{userId}/discos` — Listar discos
- `GET /{userId}/discos/{id}` — Disco específico
- `DELETE /{userId}` — Eliminar carrito
- `DELETE /{userId}/discos/{id}` — Quitar disco
- `DELETE /{userId}/productos/{id}` — Quitar producto

### ManejoStock (`/api/v1/stock`)
- `GET` — Todo el stock
- `POST` — Registrar stock
- `GET /{id}` — Stock por ID
- `GET /producto/{nombre}` — Stock por producto
- `GET /sede/{nombre}` — Stock por sede
- `PUT /{id}/cantidad` — Actualizar cantidad
- `DELETE /{id}` — Eliminar registro

### RegistrarDiscos (`/api/v1/productos`)
- `GET` — Todos los discos
- `POST` — Registrar disco
- `GET /{id}` — Disco por ID
- `PUT /{id}` — Actualizar disco
- `DELETE /{id}` — Eliminar disco

### RegistrarProductos (`/api/v1/productos`)
- `GET` — Todos los productos
- `POST` — Registrar producto
- `GET /{id}` — Producto por ID
- `GET /nombre/{nombre}` — Producto por nombre
- `GET /marca/{marca}` — Producto por marca
- `DELETE /{id}` — Eliminar producto

### RegistrarSede
- `api/v1/Sede` — CRUD de sedes
- `api/v1/Disco` — CRUD de discos por sede
- `api/v1/Producto` — CRUD de productos por sede

### RegistroResenas
- `api/v1/Resena` — CRUD de reseñas
- `api/v1/User` — CRUD de usuarios
- `api/v1/Disco` — CRUD de discos

## Base de Datos

Todos los microservicios comparten la base de datos `tiendadiscos`. Las migraciones Flyway se ejecutan al iniciar cada servicio usando tablas de historial independientes.

## Variables de Entorno (Docker)

| Variable | Descripción |
|----------|-------------|
| `SPRING_DATASOURCE_URL` | JDBC URL de MySQL |
| `SPRING_DATASOURCE_USERNAME` | Usuario BD |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña BD |
| `SPRING_PROFILES_ACTIVE` | Perfil activo (ej: `docker`) |
