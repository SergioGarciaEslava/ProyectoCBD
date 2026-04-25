# Prompt - WI-019 dashboard inicial y navegacion de demo

## Fecha

2026-04-25

## Herramienta

Claude Code (Sonnet 4.6 / Opus 4.7)

## Prompt o resumen fiel

Implementar WI-019: rediseñar la pagina principal `GET /` para que actue como dashboard de entrada a la demo academica de RavenShop.

El plan se acordo previamente en `docs/IA/prompts/2026-04-25_plan_sprint_demo.md`. Tras WI-018 (commit `b35608d`) las plantillas ya tenian estilo propio, asi que el cambio se centra en el contenido del home y no en su estructura visual.

Requisitos discutidos:

- Mantener Spring MVC + Thymeleaf y CSS propio en `app.css`, sin JavaScript ni dependencias nuevas.
- Texto mas explicito sobre el proposito academico y los conceptos que se demuestran (documentos, embebidos, RQL, auto-indexes).
- Acceso visible desde el home a herramientas tecnicas (Swagger UI y `/health-db`).
- Descripciones de las tarjetas principales que indiquen que demuestra cada seccion.
- Mantener `mvn test` en verde.

## Restricciones aplicadas

- Sin nuevas dependencias frontend ni librerias de componentes.
- Sin tocar ficheros Java.
- Sin autenticacion ni cambios de modelo.
- Cambios acotados a `index.html` y a reglas especificas al final de `app.css`.
- WI-018 (refactor a fragments Thymeleaf) queda aplazado para no introducir regresion visual sobre el commit `b35608d` a horas de la entrega.
