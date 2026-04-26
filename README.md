# RavenShop

RavenShop es una aplicacion academica minima construida con Java 21, Spring Boot, Thymeleaf y RavenDB. El proyecto sirve para demostrar modelado documental, consultas RQL y auto-indexes sobre un dominio sencillo de productos, clientes y pedidos.

## Requisitos previos

- Java 21 o superior
- Maven 3.8 o superior
- Docker, si quieres levantar RavenDB con el comando propuesto en este README

## Stack

- Java 21
- Spring Boot 3
- Spring MVC + Thymeleaf
- RavenDB
- Maven
- JUnit + Spring Boot Test

## Configuracion por defecto

La aplicacion esta preparada para arrancar con esta configuracion:

- App Spring Boot: `http://localhost:8081`
- RavenDB: `http://127.0.0.1:8085`
- Base de datos RavenDB: `RavenShop`

Valores definidos en [src/main/resources/application.properties].

## Levantar RavenDB

Si ya tienes RavenDB instalado, basta con que este disponible en `http://127.0.0.1:8085`.

Si no lo tienes, puedes levantarlo con Docker:

```bash
docker run --rm --name ravendb-ravenshop \
  -p 8085:8080 \
  -p 38888:38888 \
  -e RAVEN_Setup_Mode=None \
  -e RAVEN_Security_UnsecuredAccessAllowed=PublicNetwork \
  ravendb/ravendb:latest
```

Despues:

1. Abre RavenDB Studio en `http://127.0.0.1:8085`.
2. Crea la base de datos `RavenShop` si todavia no existe.

## Arrancar la aplicacion

Este repositorio no incluye Maven Wrapper, asi que el arranque normal es:

```bash
mvn spring-boot:run
```

Si todo va bien, la aplicacion quedara disponible en:

- Home: `http://localhost:8081/`
- Health: `http://localhost:8081/health`
- Health RavenDB: `http://localhost:8081/health-db`
- Swagger UI: `http://localhost:8081/swagger-ui.html`

Comprobacion minima:

```bash
curl http://localhost:8081/health
```

Respuesta esperada:

```json
{"application":"RavenShop","status":"UP"}
```

## Cargar datos semilla

El seed esta desactivado por defecto:

```properties
ravenshop.seed.enabled=false
```

Para arrancar la aplicacion cargando datos de ejemplo en RavenDB:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--ravenshop.seed.enabled=true"
```

El seed:

- inserta productos, clientes y pedidos de ejemplo
- es util para probar rapidamente la aplicacion y la demo de RavenDB
- es idempotente mientras exista el marcador `seed-data/ravenshop-wi022`

Si ya ejecutaste el seed antes y quieres volver a cargarlo desde cero, elimina la base `RavenShop` o borra el documento marcador correspondiente antes de volver a arrancar con seed activado.

## Ejecutar pruebas

```bash
mvn test
```

## Recorrido minimo de uso

Una vez arrancados RavenDB y la aplicacion, este es el recorrido minimo recomendado:

1. Abre `http://localhost:8081/`.
2. Entra en `Productos` y comprueba que el listado carga correctamente.
3. Crea un producto nuevo en `http://localhost:8081/products/new`.
4. Entra en `Clientes` y crea un cliente en `http://localhost:8081/customers/new`.
5. Entra en `Crear pedido` en `http://localhost:8081/orders/new`.
6. Selecciona un cliente, anade una o varias lineas y crea el pedido.
7. Revisa el detalle del pedido en `http://localhost:8081/orders/{id}`.
8. Cambia el estado del pedido desde su formulario de detalle.
9. Entra en `http://localhost:8081/orders` y prueba los filtros por estado, cliente y total minimo.
10. Vuelve a `http://localhost:8081/products` y prueba la busqueda por nombre.

## Funcionalidades principales

### Productos

- listado en `GET /products`
- creacion en `GET /products/new` y `POST /products`
- edicion en `GET /products/{id}/edit` y `POST /products/{id}`
- borrado en `POST /products/{id}/delete`
- busqueda por prefijo de nombre con parametro `q`

### Clientes

- listado en `GET /customers`
- creacion en `GET /customers/new` y `POST /customers`
- edicion en `GET /customers/{id}/edit` y `POST /customers/{id}`

### Pedidos

- listado en `GET /orders`
- filtros por `status`, `customer` y `minTotal`
- creacion en `GET /orders/new` y `POST /orders`
- detalle en `GET /orders/{id}`
- cambio de estado en `POST /orders/{id}/status`

### API y comprobaciones tecnicas

- Swagger UI: `GET /swagger-ui.html`
- OpenAPI JSON: `GET /v3/api-docs`
- salud basica: `GET /health`
- salud de RavenDB: `GET /health-db`

## Demo minima con seed

Si arrancas con seed activado, puedes hacer una demo minima sin crear datos a mano:

1. Abre `http://localhost:8081/products`.
2. Busca `Cafe` para ver resultados inmediatos en productos.
3. Abre `http://localhost:8081/orders`.
4. Filtra pedidos por estado o por total minimo.
5. Entra en el detalle de un pedido para ver `customerSnapshot`, `lineItems` y `statusHistory`.
6. Abre RavenDB Studio y ejecuta las consultas RQL documentadas en [docs/demo_rql.md].

## Estructura basica del repositorio

```text
src/main/java/com/gr21/ravenshop/
  config/       configuracion RavenDB y propiedades
  controller/   controladores MVC y endpoints tecnicos
  dto/          formularios y DTOs
  model/        documentos de dominio
  repository/   acceso a RavenDB
  seed/         carga opcional de datos semilla
  service/      logica de aplicacion

src/main/resources/
  templates/    vistas Thymeleaf
  static/       CSS y recursos estaticos
  application.properties

src/test/
  pruebas unitarias y MVC

docs/
  IA/           trazabilidad de uso de IA
  spec/         documentos tecnicos
  demo_rql.md   consultas RQL de ejemplo
```

## Problemas comunes

### La app no conecta con RavenDB

Revisa:

- que RavenDB este arrancado en `http://127.0.0.1:8085`
- que la base `RavenShop` exista
- que `ravendb.url` y `ravendb.database` coincidan con tu entorno

Puedes comprobarlo en:

- `http://localhost:8081/health-db`

### El puerto 8081 o 8085 ya esta en uso

- cambia el puerto de la app en `server.port`
- o libera el proceso que ya esta usando ese puerto
- si usas Docker, asegurate de no tener otro contenedor de RavenDB exponiendo `8085`

### El seed no vuelve a cargar datos

El seed no se repite si ya existe el marcador `seed-data/ravenshop-wi022`.

Opciones:

- borrar la base `RavenShop` y crearla de nuevo
- borrar el documento marcador y volver a arrancar con `--ravenshop.seed.enabled=true`

### RavenDB indica `DocumentCollectionMismatchException`

Si una base local se sembro con una version anterior que guardaba `customers/1-A` como `CustomerDocs` u `orders/1-A` como `OrderDocs`, RavenDB no permite convertir esos documentos a `Customers` u `Orders` mediante una actualizacion.

Las altas nuevas de clientes y pedidos usan IDs explicitos no secuenciales para evitar chocar con esos documentos antiguos. Aun asi, la opcion mas limpia para la demo es borrar la base `RavenShop`, crearla de nuevo y arrancar otra vez con:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--ravenshop.seed.enabled=true"
```

### El formulario de pedidos aparece vacio o falla al crear

Para crear pedidos necesitas tener clientes y productos disponibles en RavenDB. Si no los tienes:

- crea datos manualmente desde la aplicacion
- o arranca con seed activado

### La busqueda de productos no devuelve resultados

La busqueda por `q` funciona por prefijo de nombre. Prueba con terminos como:

- `Cafe`
- `Te`
- inicio exacto del nombre del producto

## Documentacion adicional

- RQL de ejemplo: [docs/demo_rql.md]
- Contrato OpenAPI: [docs/spec/openapi/ravenshop.openapi.yaml]
- Guia OpenAPI: [docs/spec/openapi/README.md]
- Trazabilidad IA: [docs/IA/PROMPTS_LOG.md]

## Uso de IA

La declaracion de uso de IA para la entrega esta en [docs/uso_ia.md](docs/uso_ia.md). Ese documento resume herramientas usadas, partes del trabajo afectadas, proposito de cada uso, enlaces a prompts y criterio de revision humana.

La trazabilidad completa se conserva en:

- [docs/IA/PROMPTS_LOG.md](docs/IA/PROMPTS_LOG.md)
- [docs/IA/DECISIONES_IA.md](docs/IA/DECISIONES_IA.md)
- [docs/IA/prompts/](docs/IA/prompts/)
- [docs/IA/sesiones/](docs/IA/sesiones/)
