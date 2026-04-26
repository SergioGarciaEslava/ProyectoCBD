# Prompt - vista base de detalle de pedido

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Implementar la vista base de detalle de pedido.

Quiero:

- `GET /orders/{id}`
- metodo de servicio para cargar un pedido por id
- plantilla `templates/orders/detail.html`

Mostrar:

- `id`
- `customerSnapshot`
- `orderedAt`
- `status`
- `shippingAddress`
- `total`

Restricciones:

- en este commit no hace falta todavia desarrollar en detalle `lineItems` ni `statusHistory` en la vista
- solo estructura base de la pantalla
- desde el listado debe poder accederse al detalle

## Verificacion documentada

- `mvn -q "-Dtest=OrderControllerTest" test`
