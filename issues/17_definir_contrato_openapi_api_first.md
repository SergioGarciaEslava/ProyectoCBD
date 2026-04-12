<!-- github_labels: work-item,backend,documentation,setup,high -->
<!-- backlog_labels: type:backend, type:docs, type:setup, priority:high -->
<!-- milestone: M1 - Base técnica -->
# [WI-017] Definir contrato OpenAPI para enfoque API-first

### Descripcion

Definir un contrato OpenAPI inicial para RavenShop antes de implementar el resto de funcionalidades backend. El contrato servira como referencia para diseñar endpoints, validar casos de uso, preparar pruebas y evitar que los controladores crezcan sin una especificacion comun.

### Objetivo

Tener una especificacion API-first minima y versionada que cubra los recursos principales del proyecto: healthcheck, productos, clientes y pedidos.

### Plan de trabajo

- [x] Crear una especificacion OpenAPI en el repositorio
- [x] Definir convenciones basicas de rutas, errores y formatos JSON
- [x] Incluir endpoints previstos para productos, clientes y pedidos
- [x] Incluir el endpoint de healthcheck existente
- [x] Documentar como se usara el contrato para pruebas y desarrollo
- [x] Revisar que el contrato no obliga a implementar funcionalidades fuera de alcance

### Criterios de aceptacion

- [x] Existe un archivo OpenAPI versionado en el repositorio
- [x] El contrato cubre healthcheck, productos, clientes y pedidos a nivel minimo
- [x] Las rutas y modelos son coherentes con RavenShop y con el enfoque academico
- [x] El contrato se puede usar como base para pruebas del backend
- [x] Las issues backend posteriores declaran dependencia de este Work Item
- [x] No se implementan CRUDs completos como parte de esta issue
- [x] Incluye pruebas automaticas si aplica
- [x] No rompe funcionalidades existentes
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

### Evidencia de ejecucion (2026-04-12)

- Rama creada: `featuretask/WI-017-definir-contrato-openapi-api-first`
- Contrato OpenAPI versionado: `docs/spec/openapi/ravenshop.openapi.yaml`
- Guia de uso del contrato: `docs/spec/openapi/README.md`
- README actualizado con referencia al contrato OpenAPI
- Dependencias backend verificadas: issues backend (`02`, `04`, `05`, `06`, `07`, `08`, `09`, `10`) ya declaran dependencia de `17`
- Verificacion tecnica: `mvn test` (sin regresiones)
