<!-- github_labels: work-item,setup,high -->
<!-- backlog_labels: type:setup, priority:high -->
<!-- milestone: M1 - Base técnica -->
# [WI-001] Crear proyecto base Spring Boot con estructura inicial de RavenShop

### Descripcion

Crear el proyecto base de RavenShop con Java y Spring Boot, dejando preparada una estructura limpia y fácil de defender.

### Objetivo

Tener una aplicación arrancable sobre la que poder construir el resto del trabajo.

### Plan de trabajo

- [x] Crear proyecto con Spring Boot
- [x] Añadir dependencias mínimas necesarias
- [x] Definir estructura de paquetes
- [x] Configurar puerto y nombre de la aplicación
- [x] Crear endpoint simple de prueba
- [x] Verificar que la app arranca sin errores

### Criterios de aceptacion

- [x] La aplicación arranca correctamente
- [x] Existe una estructura base clara (`config`, `controller`, `service`, `repository`, `model`)
- [x] Existe al menos un endpoint o vista de prueba
- [x] El proyecto compila sin errores
- [x] Incluye pruebas automaticas si aplica
- [x] No rompe funcionalidades existentes
- [x] Integrado correctamente en `trunk`

### Responsable

Por asignar

### Rama

`featuretask/WI-001-crear-proyecto-base-spring-boot`

### Planificacion

- Milestone: `M1 - Base técnica`
- Dependencias: Ninguna
- Estimacion: 3 h
- Clockify tag sugerida: `implementacion`

### Evidencia minima

- Captura de la app arrancando
- Primer commit del proyecto

### Evidencia de ejecucion (2026-04-12)

- Rama creada: `featuretask/WI-001-crear-proyecto-base-spring-boot`
- Endpoint de prueba: `GET /health` en `src/main/java/com/gr21/ravenshop/controller/HealthController.java`
- Estructura validada: `config`, `controller`, `service`, `repository`, `model`, `dto`, `seed`
- Verificacion ejecutada: `mvn test` (BUILD SUCCESS, 1 test OK)
