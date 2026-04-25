# Sesion 2026-04-25 - Calculo de totales de pedido en servidor

## Objetivo

Anadir una forma simple y defendible de calcular `lineTotal` y `total` de pedidos en servidor.

## Cambios realizados

- Se anadio `Order.recalculateTotals()`.
- Cada `OrderLineItem` calcula su `lineTotal` como `unitPrice * quantity`.
- `Order.total` se calcula como suma de los `lineTotal`.
- `OrderService` llama a `order.recalculateTotals()` al preparar el pedido para detalle, de forma que el servidor no depende de valores recibidos o almacenados previamente.
- Se anadieron pruebas para el calculo de dominio y para comprobar que el servicio recalcula importes antiguos.

## Verificacion

- `mvn -q -Dtest=OrderTest test`
- `mvn -q -Dtest=OrderTest,OrderServiceTest test`

## Resultado

El calculo queda centralizado en el modelo `Order` y se aplica desde el servicio, sin formularios nuevos, sin frontend y sin capas adicionales.
