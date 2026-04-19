<!-- github_labels: work-item,backend,high -->
<!-- backlog_labels: type:backend, priority:high -->
<!-- milestone: M2 - CRUD base -->
# [WI-004] Implementar modelos de dominio y acceso a datos para Products y Customers

### Descripcion

Crear los modelos Java y la capa de acceso necesaria para trabajar con productos y clientes en RavenDB.

### Objetivo

Dejar preparada la base del CRUD de productos y clientes.

### Plan de trabajo

- [x] Crear clase `Product`
- [x] Crear clase `Customer`
- [x] Evaluar clases auxiliares necesarias (`Address`, etc.) y mantener alcance minimo
- [x] Implementar repositorio o servicio de acceso a RavenDB
- [x] Probar operaciones básicas de guardar y consultar

### Criterios de aceptacion

- [x] Se pueden persistir productos y clientes
- [x] Los modelos representan correctamente la estructura definida en el diseño
- [x] El código está organizado y es fácil de explicar
- [x] Incluye pruebas automaticas si aplica
- [x] No rompe funcionalidades existentes
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

### Evidencia de ejecucion (2026-04-19)

- Capas implementadas para `Product` y `Customer`: `model`, `dto`, `repository`, `service`, `controller`.
- Para `Customer.address` se mantiene `String` en esta WI (sin clase `Address`) para no sobredimensionar el alcance.
- Endpoints base disponibles para ambos recursos: `create`, `getById`, `list`.
- Verificacion automatica:
  - `mvn -q -Dtest=ProductServiceTest,CustomerServiceTest,ProductControllerTest,CustomerControllerTest test`
  - `mvn test` (suite completa en verde).
