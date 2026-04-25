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
- Dashboard inicial en `GET /` con descripcion academica del proyecto, conceptos demostrados (documentos, subdocumentos embebidos, RQL, auto-indexes), accesos rapidos a productos, clientes y creacion de pedidos, y enlaces a Swagger UI y `/health-db` (WI-019).
- Endpoints `GET /health` y `GET /health-db` para comprobaciones de salud.
- Base de dominio y acceso a datos para `Product` y `Customer` (capas `model`, `dto`, `repository`, `service`, `controller`).
- Vista MVC para listar, crear y editar documentos `Customer` y `Product`.
- Vista MVC sencilla para crear pedidos desde clientes y productos existentes (WI-008).
- Diseño visual comun en todas las vistas con CSS propio (WI-018, sin fragmentos Thymeleaf todavia).
- Busqueda de productos por nombre que dispara el auto-index `Auto/Products/ByName` en RavenDB, paneles RQL didacticos en `/products` y en el detalle de pedido, y diferenciacion visual de subdocumentos embebidos (WI-020).
- Estructura de paquetes preparada para evolucionar el proyecto.
- Documentacion operativa y trazabilidad obligatoria del uso de IA en `docs/IA/`.
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

## Dashboard inicial (WI-019)

La pagina principal `GET /` esta diseñada como entrada a la demo academica:

```text
http://localhost:8081/
```

Que ofrece:

- Descripcion breve del proposito academico del proyecto (modelado documental en RavenDB).
- Lista de conceptos demostrados como píldoras informativas: `Documentos`, `Subdoc. embebidos`, `RQL`, `Auto-Index`.
- Tres tarjetas de acceso rapido a las secciones principales con una nota tecnica de que demuestra cada una:
  - **Productos** &mdash; catalogo, demuestra el auto-index `Auto/Products/ByName`.
  - **Clientes** &mdash; snapshot embebido en pedidos, documento relacionado sin join.
  - **Crear pedido** &mdash; documento con `lineItems`, `customerSnapshot` y `statusHistory`.
- Dos accesos a herramientas tecnicas en pestaña nueva:
  - **Swagger UI** (`/swagger-ui.html`) &mdash; explorar y probar el API REST en vivo.
  - **Estado RavenDB** (`/health-db`) &mdash; conexion activa y latencia con la base de datos.

El objetivo de esta vista es que cualquier persona que abra la aplicacion entienda en pocos segundos el proposito y las piezas tecnicas que se demostraran durante la defensa.

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

## Busqueda por nombre, RQL y auto-index (WI-020)

El listado de productos en `GET /products` admite un parametro opcional `q` que dispara una busqueda por prefijo de nombre. Esta funcionalidad existe **expresamente** para demostrar como RavenDB genera auto-indexes en tiempo de ejecucion sin necesidad de definirlos manualmente.

### Como funciona

1.  Al escribir un termino en el campo de busqueda y enviar el formulario, el navegador hace `GET /products?q=Cafe`.
2.  `ProductController.list(...)` recibe el parametro y delega en `ProductService.searchProductsByName(...)`.
3.  `RavenProductRepository.searchByNamePrefix(...)` ejecuta la siguiente RQL contra RavenDB:

    ```rql
    from Products where startsWith(Name, $namePrefix)
    ```

4.  RavenDB detecta que no existe un indice sobre el campo `Name` y **genera automaticamente** uno llamado `Auto/Products/ByName`. Este indice queda visible en RavenDB Studio (pestaña *Indexes*) y se reutiliza en consultas posteriores.
5.  La vista muestra un panel `RQL ejecutada` con la consulta exacta que se acaba de lanzar y una nota explicando el auto-index resultante.

### Por que se ha implementado asi

- **Demostrabilidad en directo:** durante la defensa basta con escribir `Cafe` en el buscador para que el tribunal vea aparecer un nuevo indice en RavenDB Studio sin haber tocado configuracion ni codigo extra. Es la evidencia mas tangible de auto-index.
- **`startsWith(Name, $q)` y no `search(Name, $q)`:** se descarto el segundo porque generaria un full-text index con analizadores y eso abre una conversacion fuera del alcance academico.
- **Sin librerias de UI:** la busqueda es un `<form method="get">` plano. Cumple el objetivo sin añadir JavaScript.

### Paneles RQL didacticos

Tanto en `/products` como en el detalle de pedido (`/orders/{id}`) hay un bloque `<aside class="rql-panel">` que muestra la RQL equivalente que se ha ejecutado por debajo. Es texto estatico en plantilla, sin instrumentacion, y existe para que cualquier persona que abra la vista entienda como esta hablando la aplicacion con RavenDB.

### Detalle de pedido con subdocumentos diferenciados

La vista `GET /orders/{id}` distingue visualmente:

- el **documento principal** `Orders` (campos planos como `id`, `status`, `total`, `shippingAddress`),
- y los **subdocumentos embebidos** (`customerSnapshot`, `lineItems[]`, `statusHistory[]`), marcados con borde de acento, fondo sutil y un kicker que indica el tipo.

Los estados (`Pending`, `Paid`, `Shipped`, `Cancelled`, `Processing`, `Created`) se muestran como pills de color para que el ciclo de vida del pedido sea legible de un vistazo. Esta vista existe para hacer tangible el modelado documental: en una base relacional el pedido seria 4-5 tablas con joins; aqui es un unico documento con sus colecciones embebidas.

### Refuerzo del seed para la demo

El seed (`RavenDbSeedRunner`) incluye seis productos, tres de ellos con prefijo `Cafe`:

- `products/1-A` Cafe de especialidad 1kg
- `products/5-A` Cafe instantaneo 200g
- `products/6-A` Cafe descafeinado 250g

De este modo, escribir `Cafe` en el buscador devuelve 3 resultados en directo y la demo no depende de crear datos a mano. El identificador del marker de seed es `seed-data/ravenshop-wi020`; al cambiarlo respecto al WI-003 se garantiza que las bases ya sembradas se actualicen al re-arrancar con `--ravenshop.seed.enabled=true`.

## Contrato API-first (OpenAPI)

El contrato OpenAPI inicial del proyecto esta en:

- `docs/spec/openapi/ravenshop.openapi.yaml`

Guia de uso para desarrollo y pruebas:

- `docs/spec/openapi/README.md`

## Que falta por implementar

Sprint final de demo cerrado (ver `docs/IA/prompts/2026-04-25_plan_sprint_demo.md`). Lo que queda pendiente:

Fuera de alcance del sprint demo:

- **WI-009:** listado de pedidos `/orders` con filtros. Permitiria enseñar un segundo auto-index (p. ej. `from Orders where Status = 'Pending'`).
- **WI-010:** actualizacion de estado e historial del pedido (cambio de `Pending` a `Paid`, `Shipped`, etc. via UI).

Aplazado tras la defensa:

- **WI-018:** refactor de plantillas a fragmentos Thymeleaf (`th:fragment` / `th:replace`). Mejora de mantenibilidad sin valor visible para el tribunal.
- **Recorrido sugerido en el dashboard:** bloque numerado que oriente al tribunal por la demo.

Pendiente para la entrega final:

- Documentacion tecnica final y guion de defensa.
- Capturas y evidencias visuales de RavenDB Studio (incluyendo el auto-index `Auto/Products/ByName` generado en directo).
- Checklist operativo previo a la defensa: borrar auto-indexes existentes en Studio, levantar RavenDB con Docker y arrancar con `--ravenshop.seed.enabled=true`.

## Uso de IA

El uso de IA debe quedar documentado siempre en `docs/IA/`.

- `docs/IA/PROMPTS_LOG.md`: registro de prompts, resultados y verificaciones.
- `docs/IA/DECISIONES_IA.md`: decisiones sobre que se acepta, modifica o descarta.
- `docs/IA/prompts/`: prompts completos o resumenes suficientemente trazables.
- `docs/IA/sesiones/`: resumen de cada sesion de trabajo.
