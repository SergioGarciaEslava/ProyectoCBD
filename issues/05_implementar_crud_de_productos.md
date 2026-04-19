<!-- github_labels: work-item,backend,frontend,high -->
<!-- backlog_labels: type:backend, type:frontend, priority:high -->
<!-- milestone: M2 - CRUD base -->
# [WI-005] Implementar CRUD de productos con Spring MVC y Thymeleaf

### Descripcion

Desarrollar las vistas y endpoints necesarios para listar, crear, editar y borrar lógicamente productos.

### Objetivo

Disponer del catálogo básico de productos para poder crear pedidos.

### Plan de trabajo

- [x] Crear controlador de productos
- [x] Crear servicio de productos
- [x] Crear vistas Thymeleaf para listado y formulario
- [x] Validar campos obligatorios
- [x] Implementar borrado lógico o alternativa sencilla para la demo

### Criterios de aceptacion

- [x] Se pueden listar productos
- [x] Se pueden crear productos válidos
- [x] Se pueden editar productos existentes
- [x] La operación de borrado no rompe la demo
- [x] Incluye pruebas automaticas si aplica
- [x] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-005-implementar-crud-de-productos`

### Planificacion

- Milestone: `M2 - CRUD base`
- Dependencias: Issues 4 y 17
- Estimacion: 6 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Capturas del CRUD funcionando
- Datos visibles en RavenDB Studio

### Evidencia de ejecucion (2026-04-20)

- API REST productos ampliada con `PUT /api/v1/products/{id}` y `DELETE /api/v1/products/{id}`.
- MVC + Thymeleaf implementado para listado, alta, edicion y borrado logico en `/products`.
- Plantillas creadas: `templates/products/list.html` y `templates/products/form.html`.
- Verificaciones ejecutadas:
  - `mvn -q -Dtest=ProductServiceTest,ProductControllerTest,ProductViewControllerTest test`
  - `mvn test` (20 tests en verde).
