<!-- github_labels: work-item,backend,database,high -->
<!-- backlog_labels: type:backend, type:data, priority:high -->
<!-- milestone: M1 - Base técnica -->
# [WI-002] Configurar conexión con RavenDB mediante RavenDB Java Client

### Descripcion

Configurar `DocumentStore` y dejar preparada la conexión con una base de datos RavenDB local para trabajar desde Spring Boot.

### Objetivo

Poder abrir sesiones contra RavenDB desde la aplicación.

### Plan de trabajo

- [x] Añadir dependencia del cliente Java de RavenDB
- [x] Crear clase de configuración para `DocumentStore`
- [x] Parametrizar URL y nombre de base de datos
- [x] Comprobar conexión real
- [x] Crear endpoint o servicio de salud para base de datos

### Criterios de aceptacion

- [ ] La aplicación se conecta a RavenDB
- [ ] Se puede abrir una sesión sin errores
- [x] Existe una comprobación simple de conectividad
- [x] La configuración queda centralizada y bien nombrada
- [x] Incluye pruebas automaticas si aplica
- [x] No rompe funcionalidades existentes
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

### Evidencia de ejecucion (2026-04-12)

- Rama creada: `featuretask/WI-002-configurar-conexion-con-ravendb`
- Configuracion `DocumentStore`: `src/main/java/com/gr21/ravenshop/config/RavenDbConfig.java`
- Propiedades centralizadas: `src/main/java/com/gr21/ravenshop/config/RavenDbProperties.java`
- Chequeo de conectividad: `GET /health/ravendb` en `src/main/java/com/gr21/ravenshop/controller/RavenDbHealthController.java`
- Servicio de salud RavenDB: `src/main/java/com/gr21/ravenshop/service/RavenDbHealthService.java`
- Test automatico endpoint: `src/test/java/com/gr21/ravenshop/controller/RavenDbHealthControllerTest.java`
- Verificacion build: `mvn test` (BUILD SUCCESS, 2 tests OK)
- Verificacion runtime:
  - `GET /health` -> `{"application":"RavenShop","status":"UP"}`
  - `GET /health/ravendb` -> `status: DOWN` con `Connection refused` a `http://localhost:8081`

### Bloqueo actual

- Los criterios de conexion efectiva (`UP`) dependen de tener RavenDB local activo en `localhost:8081` y base `RavenShop` disponible.
