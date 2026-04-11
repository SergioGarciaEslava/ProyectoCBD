<!-- github_labels: work-item,database,high -->
<!-- backlog_labels: type:data, priority:high -->
<!-- milestone: M1 - Base técnica -->
# [WI-003] Crear carga inicial de datos semilla para Products, Customers y Orders

### Descripcion

Preparar un mecanismo sencillo para insertar datos de ejemplo realistas en RavenDB y facilitar la demo y las pruebas.

### Objetivo

Tener datos iniciales suficientes para probar consultas y pantallas.

### Plan de trabajo

- [ ] Definir conjunto mínimo de productos
- [ ] Definir conjunto mínimo de clientes
- [ ] Definir conjunto mínimo de pedidos
- [ ] Implementar clase o runner de seed
- [ ] Evitar duplicados al relanzar la carga

### Criterios de aceptacion

- [ ] La base queda poblada con datos de ejemplo
- [ ] Existen al menos productos, clientes y pedidos
- [ ] Los pedidos contienen líneas, total e historial de estados
- [ ] Los datos pueden visualizarse en RavenDB Studio
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-003-preparar-datos-semilla`

### Planificacion

- Milestone: `M1 - Base técnica`
- Dependencias: Issue 2
- Estimacion: 4 h
- Clockify tag sugerida: `datos`

### Evidencia minima

- Capturas de documentos en RavenDB Studio
- Commit con el seed
