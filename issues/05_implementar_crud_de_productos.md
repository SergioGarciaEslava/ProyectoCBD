<!-- github_labels: work-item,backend,frontend,high -->
<!-- backlog_labels: type:backend, type:frontend, priority:high -->
<!-- milestone: M2 - CRUD base -->
# [WI-005] Implementar CRUD de productos con Spring MVC y Thymeleaf

### Descripcion

Desarrollar las vistas y endpoints necesarios para listar, crear, editar y borrar lógicamente productos.

### Objetivo

Disponer del catálogo básico de productos para poder crear pedidos.

### Plan de trabajo

- [ ] Crear controlador de productos
- [ ] Crear servicio de productos
- [ ] Crear vistas Thymeleaf para listado y formulario
- [ ] Validar campos obligatorios
- [ ] Implementar borrado lógico o alternativa sencilla para la demo

### Criterios de aceptacion

- [ ] Se pueden listar productos
- [ ] Se pueden crear productos válidos
- [ ] Se pueden editar productos existentes
- [ ] La operación de borrado no rompe la demo
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-005-implementar-crud-de-productos`

### Planificacion

- Milestone: `M2 - CRUD base`
- Dependencias: Issue 4
- Estimacion: 6 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Capturas del CRUD funcionando
- Datos visibles en RavenDB Studio
