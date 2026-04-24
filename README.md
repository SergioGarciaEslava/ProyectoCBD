# RavenShop

RavenShop es el repositorio base del proyecto academico de la asignatura Complementos de Bases de Datos. El objetivo es demostrar modelado y consulta de datos complejos en RavenDB mediante una aplicacion minima de productos, clientes y pedidos.

El foco del trabajo esta en documentos, RQL, auto-indexes y evidencias de uso de RavenDB. No pretende ser una tienda completa ni una aplicacion visualmente sofisticada.

## Stack tecnologico

- Backend: Java 17 + Spring Boot 3.5.13
- Frontend: Spring MVC + Thymeleaf
- Base de datos: RavenDB
- Build tool: Maven
- Testing: Spring Boot Test + JUnit

## Alcance actual

Este repositorio ya incluye una base funcional inicial:

- Proyecto Maven/Spring Boot minimo.
- Dependencias base para Web, Thymeleaf, Validation, RavenDB Java Client y Test.
- Portada MVC en `GET /` con navegacion base a productos, clientes y pedidos.
- Endpoints `GET /health` y `GET /health-db` para comprobaciones de salud.
- Base de dominio y acceso a datos para `Product` y `Customer` (capas `model`, `dto`, `repository`, `service`, `controller`).
- Estructura de paquetes preparada para evolucionar el proyecto.
- Documentacion operativa inicial.
- Backlog convertido a archivos preparados en `issues/` cuando corresponda.

No incluye CRUDs completos, autenticacion, Docker ni despliegue cloud. El proyecto ya incluye conexion base con RavenDB y un mecanismo de seed opcional para demo.

## Estructura del repositorio

```text
.
|-- README.md
|-- AGENTS.md
|-- Prompt.md
|-- pom.xml
|-- src/
|   |-- main/
|   |   |-- java/com/gr21/ravenshop/
|   |   |   |-- RavenshopApplication.java
|   |   |   |-- config/
|   |   |   |-- controller/
|   |   |   |-- dto/
|   |   |   |-- model/
|   |   |   |-- repository/
|   |   |   |-- seed/
|   |   |   `-- service/
|   |   `-- resources/
|   |       |-- application.properties
|   |       |-- static/
|   |       `-- templates/
|   `-- test/java/com/gr21/ravenshop/
|-- docs/
|   |-- demo/
|   |-- evidencias/
|   `-- spec/
|       `-- openapi/
|           |-- README.md
|           `-- ravenshop.openapi.yaml
`-- issues/
```

## Como arrancar el proyecto

Requisitos locales:

- Java 17 o superior.
- Maven 3.8 o superior.

Arranque:

```bash
mvn spring-boot:run
```

Comprobacion basica:

```bash
curl http://localhost:8080/health
```

Respuesta esperada:

```json
{"application":"RavenShop","status":"UP"}
```

## Como ejecutar pruebas

```bash
mvn test
```

## Carga de datos semilla (WI-003)

El seed de RavenDB se ejecuta al arrancar la aplicacion solo si se activa:

```properties
ravenshop.seed.enabled=true
```

Comportamiento del seed:

- Inserta documentos de ejemplo para `products`, `customers` y `orders`.
- Los pedidos incluyen lineas, total e historial de estados.
- Es idempotente: si ya existe el marcador `seed-data/ravenshop-wi003`, no vuelve a cargar datos.

## Contrato API-first (OpenAPI)

El contrato OpenAPI inicial del proyecto esta en:

- `docs/spec/openapi/ravenshop.openapi.yaml`

Guia de uso para desarrollo y pruebas:

- `docs/spec/openapi/README.md`

## Que falta por implementar

- CRUD completo de clientes y filtros avanzados de productos.
- Modelo y caso de uso completo de pedidos (`Order`).
- Caso de uso de creacion de pedidos.
- Consultas RQL y evidencias de auto-indexes.
- Vistas Thymeleaf basicas para la demo.
- Documentacion tecnica final y guion de defensa.
