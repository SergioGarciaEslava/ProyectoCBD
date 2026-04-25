# Sesion 2026-04-25 - Validaciones minimas de Order

## Objetivo

Anadir reglas minimas y defendibles para la creacion de pedidos.

## Cambios realizados

- Se anadio `Order.createPending(List<OrderLineItem>)` para crear pedidos nuevos con lineas.
- Se anadio `Order.validateForCreation()` para rechazar pedidos sin lineas.
- Se valida que cada linea tenga `quantity > 0`.
- `createPending(List<OrderLineItem>)` inicializa el estado `Pending`, registra la primera entrada de historial y recalcula totales en servidor.
- Se mantuvo `Order.createPending()` sin argumentos para compatibilidad con pruebas y codigo existente.
- Se anadieron pruebas de modelo y servicio para pedidos vacios, cantidades invalidas, estado inicial y recalculo en servidor.

## Verificacion

- Rojo: `mvn -q -Dtest=OrderTest test` fallo porque no existia `createPending(List<OrderLineItem>)`.
- Verde: `mvn -q -Dtest=OrderTest test`.
- Enfocada: `mvn -q -Dtest=OrderTest,OrderServiceTest test`.

## Resultado

Las reglas quedan centralizadas en el agregado `Order`, sin formularios nuevos, sin dependencias y sin capas adicionales.
