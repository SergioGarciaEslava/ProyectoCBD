<!-- github_labels: work-item,database,high -->
<!-- backlog_labels: type:data, priority:high -->
<!-- milestone: M1 - Base técnica -->
# [WI-003] Crear carga inicial de datos semilla para Products, Customers y Orders

### Descripcion

Preparar un mecanismo sencillo para insertar datos de ejemplo realistas en RavenDB y facilitar la demo y las pruebas.

### Objetivo

Tener datos iniciales suficientes para probar consultas y pantallas.

### Plan de trabajo

- [x] Definir conjunto mínimo de productos
- [x] Definir conjunto mínimo de clientes
- [x] Definir conjunto mínimo de pedidos
- [x] Implementar clase o runner de seed
- [x] Evitar duplicados al relanzar la carga

### Criterios de aceptacion

- [x] La base queda poblada con datos de ejemplo
- [x] Existen al menos productos, clientes y pedidos
- [x] Los pedidos contienen líneas, total e historial de estados
- [ ] Los datos pueden visualizarse en RavenDB Studio
- [x] Incluye pruebas automaticas si aplica
- [x] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-003-preparar-datos-semilla`

### Planificacion

- Milestone: `M1 - Base técnica`
- Dependencias: Issues 2 y 17
- Estimacion: 4 h
- Clockify tag sugerida: `datos`

### Evidencia minima

- Capturas de documentos en RavenDB Studio
- Commit con el seed

### Evidencia de ejecucion (2026-04-19)

- Seeder implementado en `src/main/java/com/gr21/ravenshop/seed/RavenDbSeedRunner.java`.
- Test de idempotencia y carga inicial en `src/test/java/com/gr21/ravenshop/seed/RavenDbSeedRunnerTest.java`.
- Verificacion ejecutada: `mvn test` (suite completa en verde).
