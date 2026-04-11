<!-- github_labels: work-item,backend,frontend,high -->
<!-- backlog_labels: type:backend, type:frontend, priority:high -->
<!-- milestone: M3 - Pedidos -->
# [WI-009] Implementar vistas de detalle y listado filtrable de pedidos

### Descripcion

Crear las pantallas para consultar pedidos y filtrarlos por estado, cliente o total mínimo.

### Objetivo

Demostrar consultas típicas sobre el agregado principal.

### Plan de trabajo

- [ ] Crear listado de pedidos
- [ ] Añadir filtros por estado, cliente y total mínimo
- [ ] Ordenar por fecha descendente
- [ ] Crear vista de detalle de pedido
- [ ] Mostrar líneas e historial de estados

### Criterios de aceptacion

- [ ] El listado se carga correctamente
- [ ] Los filtros devuelven resultados correctos
- [ ] El detalle muestra toda la información relevante
- [ ] La consulta es coherente con la demo en RavenDB
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-009-implementar-detalle-y-listado-filtrable-de-pedidos`

### Planificacion

- Milestone: `M3 - Pedidos`
- Dependencias: Issues 8 y 17
- Estimacion: 7 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Capturas del listado y el detalle
- Prueba manual de varios filtros
