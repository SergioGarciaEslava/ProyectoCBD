# Sesion - WI-019 dashboard inicial y navegacion de demo

## Objetivo

Convertir el `index.html` en un dashboard de entrada a la demo academica que comunique el proposito del proyecto y oriente al tribunal hacia las herramientas y secciones relevantes para defender el uso de RavenDB.

## Cambios realizados

- `src/main/resources/templates/index.html`:
  - Kicker actualizado a "Proyecto academico - CBD".
  - Texto del `lead` reescrito para describir el modelado documental en RavenDB.
  - Lista `concept-chips` con los conceptos demostrados: Documentos, Subdoc. embebidos, RQL, Auto-Index.
  - Descripciones de las tarjetas principales (`home-strip`) ampliadas para indicar que demuestra cada seccion (`Auto/Products/ByName`, snapshot embebido, `lineItems` y `statusHistory`).
  - Nueva seccion `tools-strip` con dos tarjetas: Swagger UI (`/swagger-ui.html`) y Estado RavenDB (`/health-db`), ambas con `target="_blank"`.
- `src/main/resources/static/css/app.css`:
  - Reglas `.concept-chips`, `.concept-chip` para la fila de píldoras informativas.
  - Regla `.tools-strip` (grid de 2 columnas) que se une visualmente al `.home-strip` por debajo, sin gap.
  - Ajuste del `border-radius` de `.home-strip` para encajar con `.tools-strip`.
  - Responsive: `.tools-strip` colapsa a 1 columna en pantallas estrechas (max-width 760px).

## Decisiones

- WI-018 (refactor a fragmentos Thymeleaf) se aplaza por mejor relacion riesgo/beneficio. Ver issue #27 y `DECISIONES_IA.md`.
- Las descripciones de las tarjetas usan tecnicismos (`Auto/Products/ByName`, `lineItems`, `customerSnapshot`) intencionadamente, porque el publico objetivo es academico y conoce el dominio.
- No se introduce un bloque "recorrido sugerido" en este sprint. Queda como mejora futura en `docs/IA/prompts/2026-04-25_plan_sprint_demo.md`.
- Los enlaces a Swagger UI y `/health-db` abren en pestaña nueva (`target="_blank"`, `rel="noopener"`) para no perder el contexto de demo.

## Verificacion

- `mvn test` paso 58 tests, sin fallos.
- Arranque local con `mvn spring-boot:run` en puerto 8081.
- `curl -sI http://localhost:8081/` devolvio `200 text/html` con `Content-Language: es-ES`.
- `curl -s http://localhost:8081/health` devolvio `{"application":"RavenShop","status":"UP"}`.

## Pendiente

- WI-020: pulido del detalle de pedido, busqueda por nombre con auto-index y paneles RQL didacticos.
- WI-018: aplazado, sin fecha. Issue #27 abierta como mejora futura.
