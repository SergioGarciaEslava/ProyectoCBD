# Sesion - WI-011 preparar demo de RQL para la defensa

## Objetivo

Dejar una guia breve, defendible y verificable de consultas RQL para ejecutar en RavenDB Studio durante la exposicion.

## Cambios realizados

- `docs/demo_rql.md`: reescritura completa del documento para incluir tres consultas RQL, orden sugerido de ejecucion, precondiciones de la demo y evidencia minima a capturar.
- `docs/IA/prompts/2026-04-26_wi-011_demo_rql_defensa.md`: registro del prompt/resumen de trabajo.
- `docs/IA/sesiones/2026-04-26_wi-011_demo_rql_defensa.md`: resumen de sesion.
- `docs/IA/PROMPTS_LOG.md`: nueva entrada de trazabilidad.
- `docs/IA/DECISIONES_IA.md`: decisiones relevantes de esta tarea.

## Decisiones

- Documentar las consultas contra la estructura documental actual de `Order` y `Product`, porque es la que usa la aplicacion y la que se ha comprobado en la base local.
- Mantener la consulta de `Pending` aunque en la base verificada devolviera `0`, porque sigue siendo la consulta correcta para el caso de uso y basta preparar un pedido nuevo antes de la defensa.
- Incluir los nombres de auto-index observados en la verificacion, pero avisando de que pueden variar si la base ya tenia otras consultas ejecutadas.

## Verificacion

- Comprobado que RavenDB local responde en `http://127.0.0.1:8085`.
- Comprobado que la base `RavenShop` existe y contiene `11` documentos en total.
- Consultas verificadas por HTTP directo contra RavenDB:
  - `from Orders where status = "Pending" order by orderedAt desc`
  - `from Orders where total >= 5000000 and customerSnapshot.city = "Ninguna" order by total desc`
  - `from Products where category = "Coches" or tags = "demo" order by price desc`
- `mvn test`

## Pendiente

- Guardar capturas reales de RavenDB Studio en el momento de preparar la defensa.
- Si se quiere un resultado visible en la query de `Pending`, crear antes un pedido nuevo y no cambiarle el estado.
