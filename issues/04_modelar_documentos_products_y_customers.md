<!-- github_labels: work-item,backend,high -->
<!-- backlog_labels: type:backend, priority:high -->
<!-- milestone: M2 - CRUD base -->
# [WI-004] Implementar modelos de dominio y acceso a datos para Products y Customers

### Descripcion

Crear los modelos Java y la capa de acceso necesaria para trabajar con productos y clientes en RavenDB.

### Objetivo

Dejar preparada la base del CRUD de productos y clientes.

### Plan de trabajo

- [ ] Crear clase `Product`
- [ ] Crear clase `Customer`
- [ ] Crear clases auxiliares necesarias (`Address`, etc.)
- [ ] Implementar repositorio o servicio de acceso a RavenDB
- [ ] Probar operaciones básicas de guardar y consultar

### Criterios de aceptacion

- [ ] Se pueden persistir productos y clientes
- [ ] Los modelos representan correctamente la estructura definida en el diseño
- [ ] El código está organizado y es fácil de explicar
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-004-modelar-documentos-products-y-customers`

### Planificacion

- Milestone: `M2 - CRUD base`
- Dependencias: Issues 2 y 17
- Estimacion: 4 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Código de modelos y acceso a datos
- Captura de documentos persistidos
