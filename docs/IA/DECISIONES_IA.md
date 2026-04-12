# Decisiones Sobre Uso De IA

Este archivo registra como se usan, revisan y aceptan las salidas de IA en RavenShop.

## 2026-04-11 - Bootstrap inicial del repositorio

### Que produjo la IA

- Estructura base Maven/Spring Boot para RavenShop.
- Endpoint minimo `GET /health` y prueba asociada.
- Documentacion operativa del repositorio.
- Plantillas y registros iniciales en `docs/IA/`.
- Preparacion de issues desde el backlog del proyecto.

### Que se acepto

- Base Java + Spring Boot con Maven.
- Dependencias minimas: Web, Thymeleaf, Validation, RavenDB Java Client y Test.
- Paquetes base `config`, `controller`, `service`, `repository`, `model`, `dto` y `seed`.
- Trazabilidad obligatoria de IA en `docs/IA/`.
- Preparar issues en Markdown y script manual, porque GitHub CLI no esta instalado.

### Que se modifico manualmente

- Las decisiones finales deben revisarlas los estudiantes antes de defender el trabajo.
- Las issues reales de GitHub deben crearse manualmente si falta `gh` o autenticacion.

### Que se descarto

- CRUD completo de productos, clientes o pedidos.
- Autenticacion.
- Docker.
- Frontend complejo.
- Configuracion real de conexion a RavenDB en esta fase.
- Creacion remota de issues desde esta sesion.

### Por que

El objetivo de esta ejecucion es dejar una base simple y defendible para empezar el proyecto, no implementar la aplicacion completa.
La creacion remota de issues requiere GitHub CLI instalado y autenticado; el comando local disponible devolvio `gh: command not found`.

## 2026-04-12 - Ejecucion de WI-001 en rama dedicada

### Que produjo la IA

- Creacion de rama de trabajo `featuretask/WI-001-crear-proyecto-base-spring-boot`.
- Verificacion tecnica de la base Spring Boot con `mvn test`.
- Actualizacion del checklist y evidencia en `issues/01_crear_proyecto_base_spring_boot.md`.
- Registro completo de trazabilidad en `docs/IA/`.

### Que se acepto

- Mantener el alcance de WI-001 sin ampliar funcionalidad.
- Aprovechar la base ya existente en `main` y formalizar su estado en la issue.
- No introducir dependencias nuevas ni cambios funcionales adicionales.

### Que se descarto

- Cambios de negocio (CRUD de productos/clientes/pedidos).
- Refactorizaciones no requeridas por WI-001.

### Por que

La WI-001 es una tarea de base tecnica. El repositorio ya cumplia los criterios funcionales; esta sesion se enfoco en ejecutar la issue en su rama y dejar evidencia objetiva para continuar con commit/push y siguientes work-items.
