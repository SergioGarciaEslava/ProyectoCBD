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

## 2026-04-12 - Ejecucion de WI-002 (conexion RavenDB)

### Que produjo la IA

- Configuracion tipada de propiedades RavenDB (`ravendb.urls`, `ravendb.database`).
- Bean Spring `IDocumentStore` inicializado y centralizado en capa `config`.
- Servicio de salud de RavenDB que intenta abrir sesion y consultar estadisticas.
- Endpoint `GET /health/ravendb` para comprobacion simple de conectividad.
- Prueba automatica MVC para el endpoint de salud de RavenDB.
- Actualizacion de WI-002 con evidencia objetiva y bloqueo de entorno.

### Que se acepto

- Mantener alcance tecnico de infraestructura sin entrar en CRUD o logica de negocio.
- Exponer conectividad como endpoint de salud para facilitar defensa y demo.
- Reportar estado real de entorno local (`DOWN`) cuando RavenDB no esta disponible.

### Que se descarto

- Forzar mocks complejos del cliente RavenDB para simular conexion real en unit test.
- Marcar como cumplida la conexion efectiva (`UP`) sin tener servidor local disponible.

### Por que

WI-002 busca dejar preparada la conexion y una verificacion defendible. La validacion tecnica se completo en codigo y pruebas automatizadas; la conexion efectiva depende de levantar RavenDB local en `http://localhost:8081` con base `RavenShop`.

## 2026-04-12 - Ejecucion de WI-017 (contrato OpenAPI API-first)

### Que produjo la IA

- Contrato OpenAPI inicial versionado en `docs/spec/openapi/ravenshop.openapi.yaml`.
- Definicion de convenciones de rutas (`/api/v1`), payload JSON y errores `application/problem+json`.
- Cobertura minima de recursos: `health`, `products`, `customers` y `orders`.
- Guia de uso del contrato para desarrollo y pruebas en `docs/spec/openapi/README.md`.
- Actualizacion de issue WI-017 con evidencia de ejecucion.

### Que se acepto

- Enfoque API-first documental sin implementar nuevos endpoints backend en esta issue.
- Nivel de detalle minimo y defendible para alinear desarrollo de controladores futuros.
- Mantener dependencias backend ya declaradas hacia WI-017.

### Que se descarto

- Generacion automatica de servidor/cliente desde OpenAPI en esta fase.
- Cambios de logica de negocio o CRUD completos.

### Por que

WI-017 es una tarea de contrato y alineacion tecnica. El objetivo es fijar una referencia estable para pruebas y desarrollo incremental sin ampliar alcance funcional.

## 2026-04-19 - Ejecucion de WI-003 (datos semilla)

### Que produjo la IA

- Implementacion de `RavenDbSeedRunner` para cargar datos semilla en RavenDB al arranque.
- Insercion de documentos de ejemplo para `products`, `customers` y `orders`.
- Modelado de pedidos de seed con lineas, total e historial de estados.
- Mecanismo idempotente para evitar duplicados mediante documento marcador `seed-data/ravenshop-wi003`.
- Pruebas unitarias para los casos de primera carga y recarga sin duplicados.
- Actualizacion de configuracion y documentacion para activar/desactivar seed.

### Que se acepto

- Activacion condicional del seed por propiedad (`ravenshop.seed.enabled`) para no poblar entornos por defecto.
- Uso de documentos simples y realistas para demo academica sin introducir capas adicionales.
- Enfoque idempotente con marcador unico para evitar duplicados al reiniciar la app.

### Que se descarto

- Ejecutar seed siempre activo por defecto.
- Añadir dependencias nuevas para fixtures o migraciones.
- Forzar validacion en RavenDB Studio sin servidor local disponible en la sesion.

### Por que

WI-003 exige una carga inicial defendible y repetible para demo/pruebas. La opcion adoptada minimiza complejidad, evita duplicados y mantiene el alcance acotado a la tarea sin adelantar CRUDs ni funcionalidades fuera del objetivo.

## 2026-04-19 - Ejecucion de WI-004 (modelos y acceso a datos Product/Customer)

### Que produjo la IA

- Implementacion de entidades de dominio `Product` y `Customer`.
- Definicion de DTOs de entrada/salida y paginacion para products/customers.
- Creacion de interfaces de repositorio y sus implementaciones RavenDB.
- Creacion de servicios de aplicacion para operaciones basicas (`create`, `getById`, `list`).
- Exposicion de controladores REST para `/api/v1/products` y `/api/v1/customers`.
- Pruebas automaticas en capa service y controller para ambos recursos.

### Que se acepto

- Arquitectura por capas (`controller`, `service`, `repository`, `model`, `dto`) por claridad y defendibilidad academica.
- Alcance minimo para WI-004: operaciones base sin cubrir CRUD completo aun.
- Cobertura de test ajustada al alcance, evitando sobre-ingenieria.

### Que se descarto

- Implementar en esta WI operaciones `update/delete` o filtros avanzados.
- Añadir librerias de mapeo automatico (MapStruct u otras) para mantener simplicidad.
- Extender la WI hacia pedidos (`Order`), fuera del alcance acordado.

### Por que

WI-004 pide preparar la base del CRUD para productos y clientes. La solucion aplicada deja cimientos limpios para WI-005/WI-006, mantiene bajo acoplamiento entre capas y aporta pruebas suficientes para validar comportamiento sin ampliar alcance.
