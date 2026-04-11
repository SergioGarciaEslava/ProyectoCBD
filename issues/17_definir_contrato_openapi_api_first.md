<!-- github_labels: work-item,backend,documentation,setup,high -->
<!-- backlog_labels: type:backend, type:docs, type:setup, priority:high -->
<!-- milestone: M1 - Base técnica -->
# [WI-017] Definir contrato OpenAPI para enfoque API-first

### Descripcion

Definir un contrato OpenAPI inicial para RavenShop antes de implementar el resto de funcionalidades backend. El contrato servira como referencia para diseñar endpoints, validar casos de uso, preparar pruebas y evitar que los controladores crezcan sin una especificacion comun.

### Objetivo

Tener una especificacion API-first minima y versionada que cubra los recursos principales del proyecto: healthcheck, productos, clientes y pedidos.

### Plan de trabajo

- [ ] Crear una especificacion OpenAPI en el repositorio
- [ ] Definir convenciones basicas de rutas, errores y formatos JSON
- [ ] Incluir endpoints previstos para productos, clientes y pedidos
- [ ] Incluir el endpoint de healthcheck existente
- [ ] Documentar como se usara el contrato para pruebas y desarrollo
- [ ] Revisar que el contrato no obliga a implementar funcionalidades fuera de alcance

### Criterios de aceptacion

- [ ] Existe un archivo OpenAPI versionado en el repositorio
- [ ] El contrato cubre healthcheck, productos, clientes y pedidos a nivel minimo
- [ ] Las rutas y modelos son coherentes con RavenShop y con el enfoque academico
- [ ] El contrato se puede usar como base para pruebas del backend
- [ ] Las issues backend posteriores declaran dependencia de este Work Item
- [ ] No se implementan CRUDs completos como parte de esta issue
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-017-definir-contrato-openapi-api-first`

### Planificacion

- Milestone: `M1 - Base técnica`
- Dependencias: Issue 1
- Estimacion: 3 h
- Clockify tag sugerida: `documentacion`

### Evidencia minima

- Archivo OpenAPI en el repositorio
- README o documentacion corta explicando como usar el contrato para pruebas
- Referencias de dependencia actualizadas en las issues backend
