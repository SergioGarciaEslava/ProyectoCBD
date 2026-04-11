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
