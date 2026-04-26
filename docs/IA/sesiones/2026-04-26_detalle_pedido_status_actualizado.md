## Sesion 2026-04-26

### Objetivo

Ajustar la pantalla de detalle de pedido para que, tras el cambio de estado, muestre de forma clara el estado actual y la entrada mas reciente del historial sin perder las anteriores.

### Cambios aplicados

- Se ordena `statusHistory` por `changedAt` descendente al preparar el detalle del pedido.
- Se aclaro en la vista que la entrada mas reciente se muestra primero.
- Se ampliaron pruebas de servicio y controlador para comprobar que el detalle mantiene el estado actual y muestra antes la entrada nueva.

### Verificacion

- `mvn -q "-Dtest=OrderServiceTest,OrderControllerTest" test`

### Observaciones

- No se cambio el flujo POST-redirect-GET existente; se mantiene la redireccion limpia a `/orders/{id}`.
- Las entradas previas del historial siguen visibles en la tabla, debajo de la mas reciente.
