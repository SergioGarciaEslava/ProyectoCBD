# Prompt - filtros en listado de pedidos

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Sobre el listado de pedidos ya existente, anadir filtros por:

- estado
- cliente
- total minimo

Quiero:

- filtros por query params en `GET /orders`
- campos de formulario en la vista para `status`, `customer` y `minTotal`
- logica simple en el servicio para aplicar filtros dinamicos sobre RavenDB
- mantener el listado en la misma pagina

Restricciones:

- no crear frontend complejo
- no usar JavaScript innecesario
- codigo facil de defender
- si un filtro viene vacio, no debe aplicarse

Done when:

- los filtros devuelven resultados correctos
- pueden combinarse entre si

## Verificacion documentada

- `mvn -q "-Dtest=OrderServiceTest,OrderControllerTest" test`
- verificacion HTTP real prevista sobre `http://127.0.0.1:8081/orders` con RavenDB local en `http://127.0.0.1:8085`
