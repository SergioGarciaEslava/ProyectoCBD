# Prompt - accion base de cambio de estado de pedido

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Implementar solo la accion base para cambiar el estado de un pedido.

Quiero:

- una ruta POST del estilo `/orders/{id}/status`
- un metodo en `OrderService` para cargar el pedido y aplicar el cambio
- integracion minima con la vista de detalle para lanzar la accion

Restricciones:

- no implementar todavia comentario opcional
- no rehacer toda la pantalla de detalle
- codigo simple y orientado a una operacion de negocio clara
- si el pedido no existe, manejarlo de forma sencilla y defendible

Done when:

- existe una accion POST para solicitar el cambio de estado
- la arquitectura queda preparada para actualizar `status` e historial

## Verificacion documentada

- `mvn -q "-Dtest=OrderServiceTest,OrderControllerTest" test`
