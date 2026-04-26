# Sesion - vista base de detalle de pedido

## Objetivo

Dejar la pantalla base de detalle de pedido en formato MVC/Thymeleaf, accesible desde el listado y limitada a los campos principales del documento `Order`.

## Cambios realizados

- `src/main/resources/templates/orders/list.html`: el `id` del pedido pasa a ser enlace al detalle `GET /orders/{id}`.
- `src/main/resources/templates/orders/detail.html`: simplificacion de la pantalla para mostrar solo `id`, `customerSnapshot`, `orderedAt`, `status`, `shippingAddress` y `total`.
- `src/test/java/com/gr21/ravenshop/controller/OrderControllerTest.java`: ajuste de pruebas para validar el enlace desde el listado y el render base del detalle.

## Decisiones

- No se tocan `OrderController` ni `OrderService` porque `GET /orders/{id}` y `findById(...)` ya estaban implementados en este arbol.
- Se eliminan de la vista los bloques detallados de `lineItems` y `statusHistory` para que este commit quede alineado con el alcance pedido.
- `customerSnapshot` se mantiene como subbloque simple con sus campos basicos para poder explicar el embebido documental.

## Verificacion

- `mvn -q "-Dtest=OrderControllerTest" test`

## Pendiente

- Si mas adelante se pide, desarrollar `lineItems` y `statusHistory` en la vista sin cambiar el contrato base del detalle.
