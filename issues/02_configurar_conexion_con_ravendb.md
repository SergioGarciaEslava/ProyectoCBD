<!-- github_labels: work-item,backend,database,high -->
<!-- backlog_labels: type:backend, type:data, priority:high -->
<!-- milestone: M1 - Base técnica -->
# [WI-002] Configurar conexión con RavenDB mediante RavenDB Java Client

### Descripcion

Configurar `DocumentStore` y dejar preparada la conexión con una base de datos RavenDB local para trabajar desde Spring Boot.

### Objetivo

Poder abrir sesiones contra RavenDB desde la aplicación.

### Plan de trabajo

- [ ] Añadir dependencia del cliente Java de RavenDB
- [ ] Crear clase de configuración para `DocumentStore`
- [ ] Parametrizar URL y nombre de base de datos
- [ ] Comprobar conexión real
- [ ] Crear endpoint o servicio de salud para base de datos

### Criterios de aceptacion

- [ ] La aplicación se conecta a RavenDB
- [ ] Se puede abrir una sesión sin errores
- [ ] Existe una comprobación simple de conectividad
- [ ] La configuración queda centralizada y bien nombrada
- [ ] Incluye pruebas automaticas si aplica
- [ ] No rompe funcionalidades existentes
- [ ] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-002-configurar-conexion-con-ravendb`

### Planificacion

- Milestone: `M1 - Base técnica`
- Dependencias: Issues 1 y 17
- Estimacion: 4 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Captura de RavenDB Studio con la base creada
- Código de configuración subido al repo
