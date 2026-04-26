# Prompt - detalle de pedido con lineItems y statusHistory

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Completar la vista de detalle de pedido para mostrar:

- `lineItems`
- `statusHistory`

En `lineItems` quiero ver:

- `productName`
- `category`
- `unitPrice`
- `quantity`
- `lineTotal`

En `statusHistory` quiero ver:

- `status`
- `changedAt`
- `comment`

Restricciones:

- no anadir acciones de edicion de estado todavia
- solo lectura
- vista clara y facil de explicar
- la pantalla debe ayudar a defender el enfoque documental de RavenDB

## Verificacion documentada

- `mvn -q "-Dtest=OrderControllerTest" test`
