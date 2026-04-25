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

## 2026-04-20 - Ejecucion de WI-005 (CRUD de productos MVC + Thymeleaf)

### Que produjo la IA

- Ampliacion de `ProductService` con `update` y `deactivate` (borrado logico).
- Ampliacion de `ProductController` REST con `PUT` y `DELETE`.
- Implementacion de `ProductViewController` para flujo MVC de demo.
- Creacion de `ProductForm` para binding y validacion en formularios Thymeleaf.
- Creacion de plantillas `products/list.html` y `products/form.html`.
- Pruebas automaticas minimas en service, controller REST y controller MVC.

### Que se acepto

- Mantener doble interfaz para productos: API REST (`/api/v1/products`) y MVC (`/products`).
- Borrado logico simple con `active=false` para no romper la demo.
- Cobertura de test ajustada al alcance acordado (sin sobrecargar con casos secundarios).

### Que se descarto

- Añadir JavaScript frontend o estilos avanzados para esta WI.
- Implementar filtros complejos o paginacion UI avanzada en listado MVC.
- Reestructurar todo el modulo productos antes de WI-006.

### Por que

WI-005 requiere un CRUD usable para demo con Spring MVC y Thymeleaf. La solucion mantiene simplicidad, coherencia con OpenAPI ya existente y base estable para extender comportamiento en siguientes issues.

## 2026-04-23 - Ajuste base Spring Boot MVC

### Que produjo la IA

- Ajuste de compilacion del proyecto a Java 17.
- Migracion de la configuracion principal desde `application.yml` a `application.properties`.
- Implementacion de `HomeController` con `GET /`.
- Creacion de `index.html` como portada simple con navegacion a productos, clientes y pedidos.
- Prueba MVC minima para la pagina inicial.
- Actualizacion de README y trazabilidad IA.

### Que se acepto

- Mantener el cambio pequeno y centrado en el arranque base solicitado.
- Conservar las dependencias actuales porque el codigo existente ya usa validacion Jakarta.
- Sustituir YAML por properties para alinear el repo con el requisito explicitado por el usuario.
- Configurar RavenDB hacia `http://127.0.0.1:8085` y base `RavenShop`, sin introducir todavia nuevos casos de uso de base de datos.

### Que se descarto

- Rehacer o eliminar las capas y funcionalidades ya existentes en el repositorio.
- Anadir seguridad, persistencia relacional o nuevas dependencias.
- Implementar vistas completas de clientes o pedidos en esta sesion.

### Por que

El objetivo de esta sesion es dejar una base de entrada simple y defendible sin ampliar el alcance. La portada MVC y la configuracion en properties cubren el arranque pedido, mientras que conservar el resto del codigo evita refactorizaciones grandes no solicitadas.

## 2026-04-24 - Restauracion de trazabilidad IA

### Que produjo la IA

- Restauracion de la carpeta `docs/IA/` desde el historial del repositorio.
- Reintroduccion de las reglas generales de trazabilidad IA en `AGENTS.md`.
- Reintroduccion de referencias a `docs/IA/` en `README.md` y `Prompt.md`.
- Registro de la propia sesion de restauracion.

### Que se acepto

- Recuperar la trazabilidad general del repositorio.
- Mantener fuera del repositorio el archivo de la issue WI-014, por instruccion explicita del usuario.
- Actualizar la documentacion actual sin revertir los avances recientes de `main`.

### Que se descarto

- Restaurar `issues/14_registrar_uso_de_ia_en_el_repositorio.md`.
- Revertir commits recientes o reescribir la historia de `main`.

### Por que

El usuario pidio volver a incluir la trazabilidad eliminada, pero aclaro que no queria restaurar la issue 14. La decision conserva los registros y reglas de IA necesarios sin reintroducir ese work item.

## 2026-04-24 - Cambio a Java 21

### Que produjo la IA

- Cambio de la propiedad Maven `java.version` de 17 a 21.
- Actualizacion del README para exigir Java 21 o superior.
- Actualizacion de `AGENTS.md` para fijar Java 21 como version de referencia del proyecto.
- Registro de la sesion en la trazabilidad IA.

### Que se acepto

- Mantener Spring Boot 3.5.13 y las dependencias actuales.
- No introducir nuevas dependencias ni cambios funcionales.

### Que se descarto

- Cambiar codigo de aplicacion sin necesidad.
- Anadir toolchains Maven o scripts extra en esta sesion.

### Por que

El cambio solicitado es de version de compilacion y requisitos locales. Java 21 es una version LTS y Spring Boot 3.5.x la soporta, por lo que basta con ajustar la propiedad Maven y la documentacion activa.

## 2026-04-24 - OpenAPI y Swagger UI en runtime

### Que produjo la IA

- Dependencia `springdoc-openapi-starter-webmvc-ui` para exponer OpenAPI dinamico y Swagger UI.
- Propiedades `springdoc.api-docs.*` y `springdoc.swagger-ui.*` en `application.properties`.
- Prueba automatica de disponibilidad de `/v3/api-docs` y `/swagger-ui.html`.
- Documentacion de URLs de prueba en README.

### Que se acepto

- Añadir una dependencia nueva acotada a documentacion interactiva de API.
- Mantener el contrato versionado en `docs/spec/openapi/` como artefacto de referencia academica.
- Usar Springdoc `2.8.17`, alineado con Spring Boot 3.x.

### Que se descarto

- Implementar autenticacion, perfiles separados o seguridad alrededor de Swagger.
- Cambiar la configuracion RavenDB existente.
- Sustituir el contrato OpenAPI versionado por generacion runtime.

### Por que

Swagger UI requiere un componente que sirva la interfaz y el documento OpenAPI en runtime. Springdoc cubre ese uso de forma directa en Spring MVC, manteniendo el alcance centrado en pruebas locales y demo con RavenDB.

## 2026-04-25 - Documento Order RavenDB

### Que produjo la IA

- Revision de `Order` como documento RavenDB en `src/main/java/com/gr21/ravenshop/model/`.
- Ajuste minimo para eliminar un alias JSON redundante en el campo `total`.
- Registro de trazabilidad de la sesion.

### Que se acepto

- Mantener `Order` como POJO simple, sin anotaciones JPA.
- Mantener los campos documentales solicitados: `id`, `customerId`, `customerSnapshot`, `orderedAt`, `status`, `shippingAddress`, `lineItems`, `total` y `statusHistory`.
- Mantener `status` como `String`, porque facilita RQL directo sobre estados textuales y evita introducir conversiones adicionales.
- Mantener `shippingAddress` como `String`, porque el alcance actual solo requiere una direccion de envio defendible y no pide normalizarla como objeto.
- Conservar `@JsonAlias("lines")` para que documentos semilla anteriores con `lines` puedan seguir cargando en `lineItems`.

### Que se descarto

- Crear controlador, vista, servicio o repositorio nuevo para pedidos.
- Introducir autenticacion, JPA, base relacional o nuevas dependencias.
- Refactorizar el seeder o el modulo de pedidos existente fuera del alcance pedido.

### Por que

La peticion era acotada: dejar `Order` como documento RavenDB compilable y facil de explicar. El cambio evita ampliar arquitectura y mantiene compatibilidad con datos ya presentes en el proyecto.

## 2026-04-25 - Objetos embebidos de Order

### Que produjo la IA

- Ajuste de `OrderLineItem` dentro de `Order` para incluir `category`.
- Simplificacion de `lineTotal` como campo persistido, sin calculo automatico en el getter.
- Ajuste minimo de servicio y pruebas existentes para compilar con el modelo.
- Registro de trazabilidad de la sesion.

### Que se acepto

- Mantener `OrderLineItem` y `CustomerSnapshot` como clases simples embebidas en `Order`.
- Guardar en cada linea datos historicos del producto: identificador, nombre, categoria, precio unitario, cantidad e importe de linea.
- Guardar en el snapshot datos historicos del cliente: nombre, email y ciudad.
- Conservar `customerId` en `CustomerSnapshot` por compatibilidad con codigo y vista ya existentes, manteniendo tambien `customerId` como campo principal de `Order`.

### Que se descarto

- Crear controladores o vistas nuevas.
- Introducir logica de calculo de totales en el modelo.
- Crear nuevas capas o dependencias.

### Por que

El pedido debe conservar el contexto historico tal como existia al comprar. Por eso la linea almacena nombre, categoria, precio e importe en lugar de depender siempre del documento actual de producto.

## 2026-04-25 - Estado inicial Pending en Order

### Que produjo la IA

- Campo `comment` en `Order.StatusHistoryEntry`.
- Constantes simples para el estado inicial `Pending` y el comentario inicial.
- Metodo de fabrica `Order.createPending()` para preparar estado actual e historial inicial.
- Prueba de modelo para verificar el comportamiento.

### Que se acepto

- Usar `String` constante para el estado inicial en vez de enum, porque el proyecto aun no necesita transiciones ni validaciones complejas.
- Usar `Order.createPending()` como punto explicito de creacion futura de pedidos.
- Mantener el constructor vacio de `Order` sin inicializacion automatica para no alterar la carga de documentos existentes desde RavenDB.

### Que se descarto

- Crear formularios, controladores o vistas.
- Implementar transiciones de estado.
- Anadir validaciones o reglas de negocio avanzadas.

### Por que

El objetivo actual es representar estado actual y trazabilidad sin cerrar todavia el flujo de alta de pedidos. La fabrica permite explicar claramente como nace un pedido nuevo y deja preparado el historial sin sobredisenar.
