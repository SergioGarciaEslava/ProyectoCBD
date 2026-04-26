# Prompt - orden descendente en listado de pedidos

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Ajustar el listado de pedidos para que se ordene por `orderedAt` descendente.

Quiero:

- orden descendente por fecha en el servicio o consulta a RavenDB
- mantener filtros existentes
- no anadir opciones de ordenacion extra todavia

Restricciones:

- implementacion simple
- debe seguir siendo coherente con la demo RQL prevista

Done when:

- los pedidos mas recientes aparecen primero
- el orden se mantiene tambien cuando hay filtros

## Verificacion documentada

- `mvn -q "-Dtest=OrderServiceTest" test`
