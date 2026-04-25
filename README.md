# RavenShop

RavenShop es el repositorio base del proyecto academico de la asignatura Complementos de Bases de Datos. El objetivo es demostrar modelado y consulta de datos complejos en RavenDB mediante una aplicacion minima de productos, clientes y pedidos.

El foco del trabajo esta en documentos, RQL, auto-indexes y evidencias de uso de RavenDB. No pretende ser una tienda completa ni una aplicacion visualmente sofisticada.

## Stack tecnologico

- Backend: Java 21 + Spring Boot 3.5.13
- Frontend: Spring MVC + Thymeleaf
- Base de datos: RavenDB
- Build tool: Maven
- Testing: Spring Boot Test + JUnit

## Alcance actual

Este repositorio ya incluye una base funcional inicial:

- Proyecto Maven/Spring Boot minimo.
- Dependencias base para Web, Thymeleaf, Validation, RavenDB Java Client y Test.
- Portada MVC en `GET /` con navegacion base a productos, clientes y creacion de pedidos.
- Endpoints `GET /health` y `GET /health-db` para comprobaciones de salud.
- Base de dominio y acceso a datos para `Product` y `Customer` (capas `model`, `dto`, `repository`, `service`, `controller`).
- Vista MVC para listar, crear y editar documentos `Customer`.
- Vista MVC sencilla para crear pedidos desde clientes y productos existentes.
- Estructura de paquetes preparada para evolucionar el proyecto.
- Documentacion operativa y trazabilidad inicial del uso de IA.
- Backlog convertido a archivos preparados en `issues/` cuando corresponda.

No incluye CRUDs completos, autenticacion ni despliegue cloud. El proyecto ya incluye conexion base con RavenDB, un mecanismo de seed opcional para demo e instrucciones para levantar RavenDB con Docker en local.

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
|   |-- IA/
|   |   |-- PROMPTS_LOG.md
|   |   |-- DECISIONES_IA.md
|   |   |-- prompts/
|   |   `-- sesiones/
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

- Java 21 o superior.
- Maven 3.8 o superior.
- RavenDB disponible en `http://127.0.0.1:8085` con base `RavenShop`.

### RavenDB local con Docker

Si no tienes RavenDB instalado, puedes levantarlo con Docker:

```bash
docker run --rm --name ravendb-ravenshop \
  -p 8085:8080 \
  -p 38888:38888 \
  -e RAVEN_Setup_Mode=None \
  -e RAVEN_Security_UnsecuredAccessAllowed=PublicNetwork \
  ravendb/ravendb:latest
```

Despues abre RavenDB Studio en:

```text
http://127.0.0.1:8085
```

Crea la base `RavenShop` si no existe. Es el nombre configurado en `src/main/resources/application.properties`.

Arranque:

```bash
mvn spring-boot:run
```

Comprobacion basica:

```bash
curl http://localhost:8081/health
```

Respuesta esperada:

```json
{"application":"RavenShop","status":"UP"}
```

Documentacion interactiva de la API:

- Swagger UI: `http://localhost:8081/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8081/v3/api-docs`
- Salud RavenDB: `http://localhost:8081/health-db`

Swagger UI permite probar los endpoints contra la aplicacion arrancada. Para pruebas con RavenDB, levanta RavenDB en `http://127.0.0.1:8085` y usa la base `RavenShop`, que son los valores configurados en `application.properties`.

## Como ejecutar pruebas

```bash
mvn test
```

## Carga de datos semilla (WI-003)

El seed de RavenDB se ejecuta al arrancar la aplicacion solo si se activa:

```properties
ravenshop.seed.enabled=true
```

Con Docker levantado, puedes arrancar la aplicacion con datos semilla asi:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--ravenshop.seed.enabled=true"
```

Comportamiento del seed:

- Inserta documentos de ejemplo para `products`, `customers` y `orders`.
- Los productos del seed se guardan con el mismo modelo `Product` que usa la aplicacion, para que el listado muestre juntos los sembrados y los creados manualmente.
- Los pedidos incluyen lineas, total e historial de estados.
- Es idempotente: si ya existe el marcador `seed-data/ravenshop-wi003`, no vuelve a cargar datos.

## Creacion de pedidos (WI-008)

La creacion de pedidos esta disponible desde:

```text
http://localhost:8081/orders/new
```

Funcionamiento actual:

- El formulario usa clientes y productos ya existentes en RavenDB.
- No introduce autenticacion, pagos ni flujo avanzado de pedido.
- El usuario selecciona cliente, productos y cantidades.
- El servidor carga los documentos reales de `Customer` y `Product`.
- El pedido se guarda como documento `Order` con `customerSnapshot`, `lineItems`, `statusHistory`, `status = Pending`, `lineTotal` por linea y `total`.
- Los importes se recalculan siempre en servidor; el frontend no envia ni decide `lineTotal` ni `total`.

## Contrato API-first (OpenAPI)

El contrato OpenAPI inicial del proyecto esta en:

- `docs/spec/openapi/ravenshop.openapi.yaml`

Guia de uso para desarrollo y pruebas:

- `docs/spec/openapi/README.md`

## Que falta por implementar

- CRUD completo de clientes y filtros avanzados de productos.
- Consultas RQL y evidencias de auto-indexes.
- Vistas Thymeleaf basicas para la demo.
- Documentacion tecnica final y guion de defensa.

## Uso de IA

El uso de IA debe quedar documentado siempre en `docs/IA/`.

- `docs/IA/PROMPTS_LOG.md`: registro de prompts, resultados y verificaciones.
- `docs/IA/DECISIONES_IA.md`: decisiones sobre que se acepta, modifica o descarta.
- `docs/IA/prompts/`: prompts completos o resumenes suficientemente trazables.
- `docs/IA/sesiones/`: resumen de cada sesion de trabajo.
