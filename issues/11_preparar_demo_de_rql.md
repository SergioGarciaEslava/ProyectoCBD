<!-- github_labels: work-item,demo,documentation,medium -->
<!-- backlog_labels: type:demo, type:docs, priority:medium -->
<!-- milestone: M4 - Demo RavenDB -->
# [WI-011] Preparar consultas RQL equivalentes para la defensa

### Descripcion

Definir y documentar varias consultas RQL que permitan enseñar el funcionamiento de RavenDB Studio durante la exposición.

### Objetivo

Tener una demo técnica clara y rápida de ejecutar.

### Plan de trabajo

- [ ] Preparar consulta de pedidos pendientes
- [ ] Preparar consulta de pedidos por total mínimo y ciudad
- [ ] Preparar consulta de productos por categoría o etiqueta
- [ ] Guardar las consultas en un documento del repo
- [ ] Verificarlas en RavenDB Studio

### Criterios de aceptacion

- [ ] Existen al menos 3 consultas RQL funcionales
- [ ] Las consultas están documentadas en el repositorio
- [ ] Se pueden ejecutar sin improvisación en la defensa
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-011-preparar-demo-de-rql`

### Planificacion

- Milestone: `M4 - Demo RavenDB`
- Dependencias: Issues 3, 5, 6, 8 y 9
- Estimacion: 3 h
- Clockify tag sugerida: `documentacion`

### Evidencia minima

- Archivo `docs/demo_rql.md`
- Capturas de ejecución en Studio
