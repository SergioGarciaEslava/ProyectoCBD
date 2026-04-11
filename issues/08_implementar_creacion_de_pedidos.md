<!-- github_labels: work-item,backend,frontend,high -->
<!-- backlog_labels: type:backend, type:frontend, priority:high -->
<!-- milestone: M3 - Pedidos -->
# [WI-008] Implementar caso de uso Crear pedido

### Descripcion

Desarrollar el formulario y la lógica de negocio para crear pedidos con múltiples líneas usando productos y clientes existentes.

### Objetivo

Cubrir el caso de uso principal del trabajo.

### Plan de trabajo

- [ ] Crear formulario de pedido
- [ ] Permitir seleccionar cliente
- [ ] Permitir añadir varias líneas
- [ ] Duplicar nombre, categoría y precio en la línea
- [ ] Calcular total en servidor
- [ ] Generar `statusHistory` inicial
- [ ] Persistir pedido en RavenDB

### Criterios de aceptacion

- [ ] Se puede crear un pedido con varias líneas
- [ ] El total es correcto
- [ ] El pedido queda guardado correctamente
- [ ] El documento es legible y demostrable en Studio
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-008-implementar-creacion-de-pedidos`

### Planificacion

- Milestone: `M3 - Pedidos`
- Dependencias: Issues 5, 6, 7 y 17
- Estimacion: 8 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Capturas del formulario y detalle del pedido
- Documento creado en RavenDB Studio
