<!-- github_labels: work-item,backend,database,high -->
<!-- backlog_labels: type:backend, type:data, priority:high -->
<!-- milestone: M3 - Pedidos -->
# [WI-007] Implementar modelo Order con lineItems, customerSnapshot y statusHistory

### Descripcion

Crear el modelo de pedido como agregado principal del sistema, incluyendo líneas, total, dirección de envío, estado y trazabilidad.

### Objetivo

Reflejar correctamente el enfoque documental de RavenDB.

### Plan de trabajo

- [ ] Crear clase `Order`
- [ ] Crear clase `OrderLineItem`
- [ ] Crear clase `StatusHistoryEntry`
- [ ] Crear clase `CustomerSnapshot`
- [ ] Implementar lógica para calcular total en servidor
- [ ] Aplicar reglas mínimas de validación

### Criterios de aceptacion

- [ ] El modelo representa el diseño aprobado
- [ ] El total se calcula en el servidor
- [ ] Un pedido no puede crearse vacío
- [ ] El pedido nace en estado `Pending`
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-007-modelar-documento-order-y-reglas-de-negocio`

### Planificacion

- Milestone: `M3 - Pedidos`
- Dependencias: Issues 2 y 17
- Estimacion: 5 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Código del modelo
- Ejemplo de documento `Order` en RavenDB Studio
