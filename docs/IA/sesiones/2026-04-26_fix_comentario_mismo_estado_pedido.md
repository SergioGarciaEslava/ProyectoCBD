## Sesion 2026-04-26

### Objetivo

Corregir un fallo minimo en el cambio de estado: cuando el usuario envia comentario dejando el mismo estado, el comentario no aparecia en `statusHistory`.

### Cambios aplicados

- Se ajusto `OrderService.changeStatus(...)` para que el caso `mismo estado + comentario informado` anada una nueva entrada de historial y persista el pedido.
- Se mantuvo el no-op solo para `mismo estado + comentario vacio`.
- Se anadio una prueba de servicio para ese caso.

### Verificacion

- `mvn -q "-Dtest=OrderServiceTest,OrderControllerTest" test`
- Prueba HTTP real local sobre `/orders/1-A/status` con comentario y mismo estado.

### Observaciones

- El fallo no ocurria cuando el estado cambiaba de verdad; solo en el caso de reenviar el mismo estado con comentario.
