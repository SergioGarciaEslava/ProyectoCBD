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

Este repositorio contiene solo el bootstrap inicial:

- Proyecto Maven/Spring Boot minimo.
- Dependencias base para Web, Thymeleaf, Validation, RavenDB Java Client y Test.
- Endpoint `GET /health` para comprobar que la aplicacion arranca.
- Estructura de paquetes preparada para evolucionar el proyecto.
- Documentacion operativa y trazabilidad inicial del uso de IA.
- Backlog convertido a archivos preparados en `issues/` cuando corresponda.

No incluye CRUDs, autenticacion, Docker, seed de datos ni conexion real con RavenDB. Esas tareas quedan en el backlog.

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
|   |       |-- application.yml
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

## Contrato API-first (OpenAPI)

El contrato OpenAPI inicial del proyecto esta en:

- `docs/spec/openapi/ravenshop.openapi.yaml`

Guia de uso para desarrollo y pruebas:

- `docs/spec/openapi/README.md`

## Que falta por implementar

- Configuracion real de `DocumentStore` para RavenDB.
- Modelos documentales `Product`, `Customer`, `Order` y, opcionalmente, `Review`.
- Datos semilla para la demo.
- CRUD minimo de productos y clientes.
- Caso de uso de creacion de pedidos.
- Consultas RQL y evidencias de auto-indexes.
- Vistas Thymeleaf basicas para la demo.
- Documentacion tecnica final y guion de defensa.

## Uso de IA

El uso de IA debe quedar documentado siempre en `docs/IA/`.

- `docs/IA/PROMPTS_LOG.md`: registro de prompts, resultados y verificaciones.
- `docs/IA/DECISIONES_IA.md`: decisiones sobre que se acepta, modifica o descarta.
- `docs/IA/prompts/`: prompts completos o resumenes suficientemente trazables.
- `docs/IA/sesiones/`: resumen de cada sesion de trabajo.
