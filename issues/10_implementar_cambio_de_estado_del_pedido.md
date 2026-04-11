<!-- github_labels: work-item,backend,high -->
<!-- backlog_labels: type:backend, priority:high -->
<!-- milestone: M3 - Pedidos -->
# [WI-010] Implementar actualización de estado e historial del pedido

### Descripcion

Permitir cambiar el estado de un pedido y registrar el cambio dentro de `statusHistory`.

### Objetivo

Añadir trazabilidad y una operación de negocio clara sobre el pedido.

### Plan de trabajo

- [ ] Crear acción de cambio de estado
- [ ] Actualizar campo `status`
- [ ] Añadir entrada en `statusHistory`
- [ ] Permitir comentario opcional
- [ ] Mostrar el historial actualizado en la vista

### Criterios de aceptacion

- [ ] El estado se actualiza correctamente
- [ ] El historial conserva los cambios anteriores
- [ ] La vista refleja el nuevo estado
- [ ] La información queda persistida en RavenDB
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-010-implementar-cambio-de-estado-del-pedido`

### Planificacion

- Milestone: `M3 - Pedidos`
- Dependencias: Issue 8
- Estimacion: 4 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Captura antes y después del cambio de estado
- Documento actualizado en Studio
