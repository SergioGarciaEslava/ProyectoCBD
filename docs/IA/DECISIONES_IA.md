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

## 2026-04-26 - Comentario opcional en cambio de estado de pedido

### Que produjo la IA

- Ampliacion de `POST /orders/{id}/status` para aceptar `comment` opcional.
- Ajuste de `OrderService.changeStatus(...)` para persistir ese comentario en la nueva entrada de `statusHistory`.
- Integracion minima en la vista de detalle con un `textarea` sencillo.
- Pruebas adicionales para comentario informado y comentario vacio.

### Que se acepto

- Mantener el flujo actual de detalle y cambio de estado, sin introducir pantallas nuevas.
- Guardar el comentario solo en la nueva entrada del historial.
- Normalizar comentario vacio a `null` para no romper documentos ni meter texto artificial.

### Que se descarto

- Validaciones complejas de longitud o contenido del comentario.
- Frontend con JavaScript o comportamiento dinamico.
- Reglas avanzadas de transicion entre estados.

### Por que

El objetivo era ampliar una operacion de negocio ya existente con el menor cambio posible y de forma facil de defender. El comentario opcional queda integrado en el propio documento `Order` como parte de `statusHistory`, reforzando el enfoque documental sin anadir complejidad innecesaria.

## 2026-04-26 - Ajuste del detalle tras cambio de estado

### Que produjo la IA

- Ordenacion visual de `statusHistory` por fecha descendente en el detalle.
- Ajuste menor de la plantilla para aclarar que la entrada mas reciente aparece primero.
- Pruebas nuevas para asegurar que el estado actual y la ultima entrada del historial quedan visibles tras el redirect.

### Que se acepto

- Mantener el flujo POST-redirect-GET actual para recargar el pedido desde RavenDB.
- Reordenar solo la presentacion del historial en detalle, sin reescribir ni borrar entradas.
- Dejar la vista sin JavaScript ni cambios estructurales grandes.

### Que se descarto

- Rehacer la pantalla de detalle.
- Introducir mensajes dinamicos o comportamiento en cliente.
- Cambiar la politica de persistencia del historial.

### Por que

El objetivo era hacer mas defendible la demo del cambio de estado: el estado actual debe verse de inmediato y la ultima entrada del historial no debe quedar escondida al final de la tabla. Reordenar la presentacion en el detalle resuelve eso con un cambio pequeno y claro.

## 2026-04-26 - Correccion del comentario con mismo estado

### Que produjo la IA

- Ajuste puntual de `OrderService.changeStatus(...)` para no descartar comentarios cuando el usuario reenvia el mismo estado.
- Prueba automatica del caso `mismo estado + comentario`.

### Que se acepto

- Mantener el no-op solo si el estado coincide y no hay comentario.
- Si el estado coincide pero hay comentario, anadir una nueva entrada en `statusHistory` con ese mismo estado.

### Que se descarto

- Rehacer el formulario o forzar al usuario a cambiar obligatoriamente el estado.
- Cambiar la vista o introducir mensajes de validacion en cliente.

### Por que

En uso real, el formulario puede enviarse con el mismo estado seleccionado y un comentario manual relevante. Tratar ese caso como no-op hacia que el comentario se perdiera, asi que se adopto una regla mas util y aun sencilla: conservar el estado actual y registrar igualmente la anotacion en el historial.

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

## 2026-04-25 - Calculo de totales de pedido en servidor

### Que produjo la IA

- Metodo `Order.recalculateTotals()` para recalcular importes del pedido.
- Calculo de `OrderLineItem.lineTotal` como `unitPrice * quantity`.
- Calculo de `Order.total` como suma de los importes de linea.
- Invocacion desde `OrderService` al preparar el detalle del pedido.
- Pruebas de modelo y servicio para cubrir el calculo.

### Que se acepto

- Centralizar el calculo en el modelo `Order`, porque es la forma mas pequena y facil de explicar.
- Llamar al recalculo desde el servicio para dejar claro que el servidor no depende de valores del frontend.
- Mantener `lineTotal` y `total` como datos almacenables del documento RavenDB, pero recalculables desde los datos base.

### Que se descarto

- Crear formulario de pedidos.
- Recalcular importes en Thymeleaf o JavaScript.
- Crear servicios auxiliares, validadores o nuevas capas.
- Anadir dependencias nuevas.

### Por que

El requisito es demostrar una regla de negocio sencilla y defendible: los importes salen de `quantity` y `unitPrice`. Un metodo de dominio invocado desde el servicio cubre el caso sin sobredisenar el proyecto.

## 2026-04-25 - Validaciones minimas de Order

### Que produjo la IA

- Sobrecarga `Order.createPending(List<OrderLineItem>)` para crear pedidos nuevos con lineas.
- Metodo `Order.validateForCreation()` para rechazar pedidos vacios.
- Validacion de `quantity > 0` en cada linea.
- Pruebas de modelo para pedido vacio, cantidad invalida, estado inicial `Pending` y recalculo de totales.
- Prueba de servicio para confirmar que una cantidad invalida se detecta al recalcular en servidor.

### Que se acepto

- Usar `IllegalArgumentException` con mensajes simples y explicables.
- Mantener las reglas en el agregado `Order`, porque pertenecen al documento de pedido y evitan capas innecesarias.
- Mantener `Order.createPending()` sin argumentos para no romper codigo existente, y usar la nueva sobrecarga para creaciones reales con lineas.
- Mantener Java 21 como version del proyecto por regla activa de `AGENTS.md`.

### Que se descarto

- Crear pantalla o formulario de creacion de pedidos.
- Anadir validaciones de producto, stock, cliente o transiciones de estado.
- Introducir frameworks o dependencias nuevas.
- Cambiar el proyecto a Java 17.

### Por que

Las reglas pedidas son invariantes basicas del documento `Order`: un pedido nuevo necesita lineas, cantidades validas, estado inicial y totales calculados por servidor. Resolverlo en el modelo mantiene el codigo corto y defendible para la explicacion oral.

## 2026-04-25 - Verificacion documental de WI-007

### Que produjo la IA

- Checklist manual para revisar el documento `Order`.
- Pedido JSON de ejemplo para insertar desde RavenDB Studio o como dato semilla.
- Texto breve para `docs/uso_ia.md`.
- Evidencia minima reutilizable en PR o Clockify.
- Sugerencia de prueba automatica pequena para el agregado `Order`.

### Que se acepto

- Ubicar la evidencia de demo en `docs/demo/wi-007_verificacion_order_ravendb.md`.
- Crear `docs/uso_ia.md` como resumen general y breve para defensa academica.
- Mantener el contenido como documentacion de cierre, sin cambiar codigo ni introducir nuevas funcionalidades.

### Que se descarto

- Crear formularios de pedidos.
- Insertar datos automaticamente en RavenDB en esta sesion.
- Anadir pruebas nuevas, porque la prueba recomendada ya queda cubierta por las pruebas del agregado existentes.

### Por que

La WI-007 ya cuenta con modelo, calculo de totales y validaciones. Para cerrarla de forma defendible, basta con dejar una evidencia manual clara y un ejemplo documental que explique el enfoque de RavenDB sin ampliar alcance.

## 2026-04-25 - Creacion de pedidos WI-008

### Que produjo la IA

- DTOs de formulario para crear pedidos desde Thymeleaf.
- Persistencia de `Order` en RavenDB desde `OrderRepository`.
- Metodo `OrderService.createOrder(...)` para construir el documento a partir de cliente, productos y cantidades.
- Rutas MVC `GET /orders/new` y `POST /orders`.
- Plantilla `orders/form.html` con tres lineas fijas de pedido.
- Enlace desde la portada a la creacion de pedidos.
- Documentacion de RavenDB con Docker en README.

### Que se acepto

- Mantener el flujo de pedido como MVC simple, sin JavaScript ni componentes frontend adicionales.
- Usar clientes y productos ya existentes en RavenDB en vez de crear datos nuevos dentro del formulario.
- Ignorar lineas sin producto y dejar que `Order.createPending(...)` rechace pedidos sin lineas validas.
- Copiar snapshot del cliente y datos historicos del producto en el documento `Order`.
- Recalcular `lineTotal` y `total` siempre en servidor.
- Hacer commits pequenos por parte funcional y no hacer push.

### Que se descarto

- Crear un checkout completo, pagos, autenticacion o gestion avanzada de estados.
- Anadir dependencias de frontend o validacion extra.
- Introducir una capa especifica de comandos o validadores.
- Modificar el seed, porque el seed existente ya aporta clientes y productos suficientes para probar la creacion.

### Por que

WI-008 necesita cerrar el caso de uso de creacion de pedidos de forma demostrable. La solucion mantiene el foco academico: un formulario sencillo produce un documento agregado en RavenDB con datos embebidos, snapshot historico y totales calculados por el servidor.

## 2026-04-25 - Layout visual comun WI-018

### Que produjo la IA

- CSS global en `src/main/resources/static/css/app.css`.
- Sistema de variables visuales basado en la paleta propuesta por el usuario.
- Cabecera y navegacion principal consistentes en las vistas MVC.
- Estilos comunes para botones, enlaces, tablas, formularios, mensajes y estados de foco.
- Adaptacion visual de home, productos, clientes y pedidos.

### Que se acepto

- Usar la paleta como acento sobre fondos neutros para conservar legibilidad.
- Mantener un estilo minimalista, con pocas superficies enmarcadas y sin decoracion excesiva.
- Usar CSS propio y Thymeleaf sin dependencias frontend nuevas.
- Aplicar el estilo tambien a pedidos para que la navegacion no lleve a una pantalla sin pulir.
- Evitar fragmentos Thymeleaf por ahora para que la WI se mantenga pequena y revisable.

### Que se descarto

- Introducir React, Vue, Angular, Tailwind o librerias de componentes.
- Crear una landing page de marketing.
- Usar degradados grandes o una interfaz dominada por la paleta completa.
- Refactorizar controladores o logica de negocio.

### Por que

WI-018 busca mejorar la percepcion visual de la demo sin complicar la arquitectura. Un CSS global con clases compartidas cubre el objetivo y mantiene el proyecto defendible como aplicacion academica Spring MVC + Thymeleaf.

## 2026-04-25 - Dashboard inicial WI-019

### Que produjo la IA

- Rediseño del `index.html` con kicker actualizado, descripcion del proposito academico y los conceptos demostrados.
- Lista `concept-chips` con cuatro píldoras informativas: Documentos, Subdoc. embebidos, RQL, Auto-Index.
- Descripciones tecnicas en las tarjetas principales que apuntan a lo que demuestra cada seccion (`Auto/Products/ByName`, snapshot embebido sin join, `lineItems`/`customerSnapshot`/`statusHistory`).
- Nueva seccion `tools-strip` con accesos a Swagger UI y al endpoint de salud `/health-db`.
- Reglas CSS `.concept-chips`, `.concept-chip` y `.tools-strip` añadidas al final de `app.css`, integradas visualmente con `.home-strip`.
- Documentacion del aplazamiento explicito de WI-018.

### Que se acepto

- Mantener Spring MVC + Thymeleaf y CSS propio sin dependencias nuevas.
- Reutilizar `.home-link` para las tarjetas secundarias por simplicidad visual y coherencia con la fila principal.
- Usar tecnicismos en las descripciones (`Auto/Products/ByName`, `lineItems`, `customerSnapshot`) porque el publico es academico y conoce el dominio.
- Enlazar Swagger UI y `/health-db` con `target="_blank"` y `rel="noopener"` para no perder la sesion de demo.
- Cerrar la issue #26 una vez verificado `mvn test` y arranque local.

### Que se descarto

- Bloque "recorrido sugerido" con pasos numerados: queda como mejora futura, la defensa oral cubre esa orientacion.
- Refactor a fragmentos Thymeleaf (WI-018): aplazado para no arriesgar regresion visual sobre el commit `b35608d` a horas de la entrega.
- Tocar codigo Java, formularios o controladores.
- Añadir JavaScript o librerias frontend.

### Por que

WI-019 pide una primera pantalla que comunique el proposito academico y oriente al tribunal hacia las secciones tecnicas relevantes. La solucion mantiene el alcance pequeño y reversible, prioriza el contenido sobre cambios estructurales y aprovecha la base visual ya estabilizada en WI-018. WI-018 se aplaza porque su valor para la defensa es nulo (refactor invisible) y su riesgo es alto (toca todas las plantillas justo despues de estabilizarlas).

## 2026-04-25 - Pedidos pulidos, busqueda con auto-index y paneles RQL WI-020

### Que produjo la IA

- Backend de busqueda por nombre: metodo `searchByNamePrefix` en `ProductRepository`, implementacion RQL en `RavenProductRepository`, paso por `ProductService` y aceptacion de parametro `q` en `ProductController.list`.
- Refuerzo del seed con `products/5-A` y `products/6-A`, ambos con prefijo `Cafe`, y cambio de `SEED_MARKER_ID` a `seed-data/ravenshop-wi020`.
- UI de busqueda en `/products`: formulario `GET`, resumen de resultados, boton de limpiar y panel `<aside class="rql-panel">` con la RQL ejecutada y nota sobre el auto-index generado.
- Detalle de pedido pulido: cabecera `doc-kicker` por seccion, clase `embedded-section` para los tres subdocumentos (`customerSnapshot`, `lineItems[]`, `statusHistory[]`), pills de estado con variantes de color y panel RQL con `from Orders where id() = '<id>'`.
- CSS: `.search-bar`, `.search-summary`, `.rql-panel`, `.rql-kicker`, `.doc-kicker`, `.rql-note`, `.embedded-section`, variantes `.status-pill--paid/--shipped/--cancelled/--pending/--processing/--created`, regla `code` general y `.visually-hidden`.
- Tests: actualizacion de `ProductControllerTest` (mockea `searchProductsByName`, nuevo test para `?q=...`) y de `RavenDbSeedRunnerTest` (constantes para producto/cliente/pedido y referencia al `SEED_MARKER_ID` real).

### Que se acepto

- **RQL `startsWith(Name, $q)`** como base de la busqueda, porque genera el auto-index estandar `Auto/Products/ByName`, facil de mostrar y explicar en RavenDB Studio.
- Cambiar `SEED_MARKER_ID` a `seed-data/ravenshop-wi020` para que el seed se aplique de nuevo en bases ya sembradas, manteniendo idempotencia: los IDs de documentos del seed se sobrescriben en lugar de duplicarse.
- Pills de estado con clase modificadora `status-pill--<lowercase>` calculada en plantilla, para cubrir los estados conocidos sin acoplar el modelo a una enum.
- Paneles RQL como texto estatico en plantilla, sin instrumentacion runtime, porque el objetivo es didactico y no de monitorizacion.
- Tests `MockMvc` como prueba de render de plantillas, dado que el entorno de CI no levanta RavenDB.

### Que se descarto

- **`search(Name, $q)`** (full-text): introduciria conversacion sobre analizadores que no aporta a la defensa academica.
- Endpoint nuevo `/orders` (WI-009) con filtros: queda fuera del sprint demo. Aportaria un segundo auto-index pero ~1h adicional.
- Refactor a fragmentos Thymeleaf (WI-018): sigue aplazado.
- Librerias de syntax highlighting para los paneles RQL: el contraste fondo-oscuro/codigo-claro basta visualmente.
- Inicializar la conexion RavenDB en los tests: `MockMvc` cubre el render sin necesidad de DB.

### Por que

WI-020 es la pieza con mejor relacion impacto/riesgo del sprint. El auto-index `Auto/Products/ByName` se genera con una RQL minima y es el momento "wow" de la defensa. Los paneles RQL convierten la interfaz en un material didactico sin coste de mantenimiento. La diferenciacion visual de los embebidos en el detalle de pedido hace tangible el modelado documental. El cambio del marker es la ruta mas pequeña para que el seed se actualice sin romper la convencion existente.
## 2026-04-26 - Listado base de pedidos

### Que produjo la IA

- Metodo `findAll()` en `OrderRepository` y `RavenOrderRepository` para recuperar pedidos desde RavenDB.
- Metodo `OrderService.listOrders()` para preparar los datos del listado MVC.
- Ruta `GET /orders` en `OrderController`.
- Nueva vista `templates/orders/list.html`.
- Enlace a pedidos desde la portada.
- Tests de servicio y controlador para el listado.

### Que se acepto

- Mantener el listado como una consulta simple de RavenDB, sin filtros ni paginacion.
- Reutilizar `OrderService` existente en lugar de crear una capa nueva especifica para lectura.
- Mostrar solo los campos pedidos: `id`, `orderedAt`, `status`, `customerSnapshot.fullName` y `total`.
- Intentar completar `customerSnapshot` y campos derivados si faltan en documentos antiguos, para que la demo sea mas robusta.
- Ordenar por `orderedAt desc` en repositorio porque hace el listado mas defendible en demo sin anadir complejidad.

### Que se descarto

- Implementar filtros, busqueda o detalle desde el listado.
- Anadir DTOs especificos de tabla o view models extra.
- Introducir nuevas dependencias o cambiar la configuracion de RavenDB.

### Por que

El objetivo de esta sesion es cubrir solo el caso de uso minimo de lectura de pedidos desde RavenDB y dejarlo facil de explicar oralmente. La solucion se apoya en las capas ya existentes, evita arquitectura adicional y mantiene un cambio pequeno, coherente con un commit medio.

## 2026-04-26 - Orden descendente en listado de pedidos

### Que produjo la IA

- Ordenacion final por `orderedAt desc` en `OrderService.listOrders()`.
- Prueba que verifica que el pedido mas reciente queda primero aunque otro pedido reciba `orderedAt` derivado desde `statusHistory`.

### Que se acepto

- Mantener la implementacion pequena y localizada en el servicio.
- Reforzar el orden despues del enriquecimiento para no depender solo del valor persistido en RavenDB.
- No tocar la vista ni anadir opciones nuevas de ordenacion.

### Que se descarto

- Crear selector de orden en la UI.
- Refactorizar el repositorio o la query mas alla de lo necesario.

### Por que

La query ya era coherente con la demo RQL, pero un pedido legacy puede completar `orderedAt` durante el enriquecimiento. Ordenar al final en el servicio garantiza que los pedidos mas recientes queden primero en el resultado que realmente se pinta.

## 2026-04-26 - Vista base de detalle de pedido

### Que produjo la IA

- Enlace desde el listado al detalle de cada pedido.
- Simplificacion de `orders/detail.html` para dejar solo la estructura base del documento.
- Ajuste de tests MVC del controlador de pedidos.

### Que se acepto

- Reutilizar `GET /orders/{id}` y `OrderService.findById(...)` ya existentes.
- Mostrar `customerSnapshot` como bloque embebido simple con sus campos basicos.
- Eliminar de esta iteracion la representacion detallada de `lineItems` y `statusHistory`.

### Que se descarto

- Cambiar el backend del detalle.
- Anadir tabs, acordeones o frontend adicional.
- Implementar navegacion o acciones extra dentro del detalle.

### Por que

El usuario pidio un commit pequeno y defendible. La forma mas coherente de cumplirlo era conservar el backend ya existente, abrir el acceso desde el listado y recortar la vista para que solo muestre los campos nucleares del pedido sin adelantar partes no pedidas todavia.

## 2026-04-26 - Detalle de pedido con lineItems y statusHistory

### Que produjo la IA

- Secciones de solo lectura para `lineItems[]` y `statusHistory[]` en `orders/detail.html`.
- Tabla de lineas con `productName`, `category`, `unitPrice`, `quantity` y `lineTotal`.
- Tabla de historial con `status`, `changedAt` y `comment`.
- Refuerzo de la prueba MVC del detalle.

### Que se acepto

- Mantener la pantalla como lectura pura, sin acciones.
- Mostrar las dos colecciones como subdocumentos embebidos separados del bloque principal.
- Anadir un panel RQL didactico para apoyar la defensa del enfoque documental.

### Que se descarto

- Edicion de estados.
- Botones de negocio adicionales.
- Refactor del servicio o del modelo.

### Por que

El objetivo de esta iteracion es hacer visible en la interfaz que un pedido RavenDB puede incluir snapshots, lineas e historial dentro del mismo documento. Mostrar esas dos colecciones embebidas en tablas simples ayuda a explicar el agregado sin complicar la aplicacion.

## 2026-04-26 - Accion base de cambio de estado de pedido

### Que produjo la IA

- Ruta `POST /orders/{id}/status`.
- Metodo `OrderService.changeStatus(...)` para cargar, actualizar y guardar el pedido.
- Formulario minimo en la vista de detalle para solicitar el cambio.
- Pruebas de servicio y controlador para exito y pedido inexistente.

### Que se acepto

- Mantener una sola operacion de negocio clara sobre pedidos: cambiar estado.
- Registrar el cambio en `statusHistory` en el mismo documento `Order`.
- Usar un comentario fijo del sistema (`"Estado actualizado"`) para no abrir aun el campo libre en UI.
- Responder con `404` si el pedido no existe.

### Que se descarto

- Comentario opcional en el formulario.
- Reglas complejas de transicion entre estados.
- Refactor amplio del detalle o del agregado.

### Por que

El objetivo del commit es dejar preparada la arquitectura documental para evolucionar el estado de un pedido sin anadir complejidad innecesaria. Una ruta POST sencilla, un metodo de servicio directo y una nueva entrada en `statusHistory` cubren el caso de uso y son faciles de defender oralmente.
