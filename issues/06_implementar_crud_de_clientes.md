<!-- github_labels: work-item,backend,frontend,high -->
<!-- backlog_labels: type:backend, type:frontend, priority:high -->
<!-- milestone: M2 - CRUD base -->
# [WI-006] Implementar CRUD de clientes con Spring MVC y Thymeleaf

### Descripcion

Desarrollar las vistas y endpoints necesarios para listar, crear, editar y consultar clientes.

### Objetivo

Tener clientes disponibles para asociarlos a los pedidos.

### Plan de trabajo

- [ ] Crear controlador de clientes
- [ ] Crear servicio de clientes
- [ ] Crear vistas Thymeleaf para listado y formulario
- [ ] Validar email y dirección mínima
- [ ] Comprobar persistencia correcta en RavenDB

### Criterios de aceptacion

- [ ] Se pueden listar clientes
- [ ] Se pueden crear clientes válidos
- [ ] Se pueden editar clientes existentes
- [ ] Los datos se almacenan correctamente
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-006-implementar-crud-de-clientes`

### Planificacion

- Milestone: `M2 - CRUD base`
- Dependencias: Issue 4
- Estimacion: 6 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Capturas del CRUD funcionando
- Ejemplos de clientes en base de datos
