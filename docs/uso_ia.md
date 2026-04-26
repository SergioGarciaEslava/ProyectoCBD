# Uso de IA

Este apartado resume el uso de asistentes de IA durante el desarrollo de RavenShop. El registro detallado se conserva en [`docs/IA/PROMPTS_LOG.md`](IA/PROMPTS_LOG.md), con los prompts o resúmenes fieles en [`docs/IA/prompts/`](IA/prompts/) y las decisiones revisadas en [`docs/IA/DECISIONES_IA.md`](IA/DECISIONES_IA.md).

## Criterio de uso

El equipo ha usado GitHub Copilot, ChatGPT y Codex como herramientas de apoyo. Los estudiantes mantienen la autoría del trabajo, revisan el código antes de aceptarlo y deben poder explicar el modelo documental, las consultas RQL, los controladores, las vistas y las pruebas.

La IA se ha usado para:

- acelerar código repetitivo en Java, DTOs, controladores, servicios, repositorios y tests;
- consultar sintaxis concreta de RavenDB Java Client, RQL, Spring MVC, Thymeleaf y Maven;
- redactar borradores de documentación técnica y operativa;
- generar datos semilla de prueba para la demo;
- revisar problemas concretos detectados durante pruebas o ejecución local.

El equipo no ha delegado en la IA la defensa del trabajo, la aceptación de decisiones técnicas ni la explicación académica del proyecto.

## Partes del trabajo asistidas por IA

| Parte del trabajo | Herramienta principal | Propósito | Prompt o registro |
|---|---|---|---|
| Bootstrap del proyecto Spring Boot, estructura Maven y primera documentación | Copilot | Crear una base inicial revisable para RavenShop | [`2026-04-11_bootstrap_repo_y_issues.md`](IA/prompts/2026-04-11_bootstrap_repo_y_issues.md) |
| Actualización a Java 21 | Copilot | Alinear `pom.xml`, README y documentación operativa con Java 21 | [`2026-04-11_java_21.md`](IA/prompts/2026-04-11_java_21.md), [`2026-04-24_java_21.md`](IA/prompts/2026-04-24_java_21.md) |
| Configuración de RavenDB | Copilot | Preparar `IDocumentStore`, propiedades y endpoint de salud | [`2026-04-12_wi-002_conexion_ravendb.md`](IA/prompts/2026-04-12_wi-002_conexion_ravendb.md) |
| Datos semilla para productos, clientes y pedidos | Copilot | Generar datos de demo y pruebas de idempotencia | [`2026-04-19_wi-003_datos_semilla.md`](IA/prompts/2026-04-19_wi-003_datos_semilla.md), [`2026-04-26_ampliar_seeders_demo.md`](IA/prompts/2026-04-26_ampliar_seeders_demo.md) |
| Modelos, DTOs y acceso básico a datos | Copilot | Acelerar boilerplate de `Product`, `Customer`, repositorios, servicios y tests | [`2026-04-19_wi-004_modelos_product_customer.md`](IA/prompts/2026-04-19_wi-004_modelos_product_customer.md) |
| CRUD MVC de productos y clientes | Copilot | Generar borradores de controladores, formularios Thymeleaf y pruebas MVC | [`2026-04-20_wi-005_crud_productos_mvc_thymeleaf.md`](IA/prompts/2026-04-20_wi-005_crud_productos_mvc_thymeleaf.md) |
| Modelo documental de pedidos | Copilot | Implementar `Order` como agregado documental con líneas embebidas, snapshot de cliente, total e historial | [`2026-04-25_order_documento_ravendb.md`](IA/prompts/2026-04-25_order_documento_ravendb.md), [`2026-04-25_order_objetos_embebidos.md`](IA/prompts/2026-04-25_order_objetos_embebidos.md), [`2026-04-25_order_calculo_totales_servidor.md`](IA/prompts/2026-04-25_order_calculo_totales_servidor.md) |
| Creación, listado, detalle y cambio de estado de pedidos | Copilot | Construir flujo MVC simple para la demo y cubrirlo con tests | [`2026-04-25_wi-008_creacion_pedidos.md`](IA/prompts/2026-04-25_wi-008_creacion_pedidos.md), [`2026-04-26_listado_base_pedidos.md`](IA/prompts/2026-04-26_listado_base_pedidos.md), [`2026-04-26_detalle_pedido_lineitems_statushistory.md`](IA/prompts/2026-04-26_detalle_pedido_lineitems_statushistory.md), [`2026-04-26_cambio_estado_pedido_comentario_opcional.md`](IA/prompts/2026-04-26_cambio_estado_pedido_comentario_opcional.md) |
| Consultas RQL y auto-indexes para la defensa | ChatGPT y Copilot | Preparar ejemplos de consulta en RavenDB Studio y explicar campos usados | [`2026-04-25_wi-020_pedidos_busqueda_rql.md`](IA/prompts/2026-04-25_wi-020_pedidos_busqueda_rql.md), [`2026-04-26_wi-011_demo_rql_defensa.md`](IA/prompts/2026-04-26_wi-011_demo_rql_defensa.md) |
| Interfaz Thymeleaf y dashboard de demo | Copilot | Mejorar la presentación visual sin introducir frameworks frontend complejos | [`2026-04-25_wi-018_layout_visual_thymeleaf.md`](IA/prompts/2026-04-25_wi-018_layout_visual_thymeleaf.md), [`2026-04-25_wi-019_dashboard_inicial.md`](IA/prompts/2026-04-25_wi-019_dashboard_inicial.md) |
| Documentación de instalación, ejecución y uso | Codex | Redactar un README reproducible y contrastado con el estado real del repositorio | [`2026-04-26_wi-013_readme_instalacion_uso.md`](IA/prompts/2026-04-26_wi-013_readme_instalacion_uso.md) |
| Correcciones detectadas durante pruebas | Copilot | Corregir fallos acotados en seed, búsquedas y cambio de estado | [`2026-04-26_fix_seed_colecciones_busqueda_productos.md`](IA/prompts/2026-04-26_fix_seed_colecciones_busqueda_productos.md), [`2026-04-26_fix_comentario_mismo_estado_pedido.md`](IA/prompts/2026-04-26_fix_comentario_mismo_estado_pedido.md) |

## Revisión humana y comprensión

El equipo ha revisado los cambios antes de incorporarlos al proyecto. En las sesiones registradas se documentan los archivos tocados, el resultado y la verificación realizada. Las decisiones con impacto técnico se resumen en [`docs/IA/DECISIONES_IA.md`](IA/DECISIONES_IA.md), incluyendo alternativas descartadas como autenticación, pagos, frontend complejo, JPA o reglas avanzadas fuera del alcance académico.

El proyecto entregado no consiste en código o texto generado íntegramente por IA sin análisis propio. Cada bloque incorporado se ha adaptado al alcance del trabajo: Java 21, Spring Boot, Thymeleaf, RavenDB, modelado documental, consultas RQL y demo local.

## Evidencias de verificación

Las verificaciones documentadas incluyen ejecuciones de `mvn test`, pruebas enfocadas con `-Dtest=...`, arranque local con `mvn spring-boot:run`, comprobaciones HTTP con `curl` y revisión manual de consultas RQL en RavenDB Studio cuando el entorno local lo permitió. El detalle por sesión aparece en [`docs/IA/PROMPTS_LOG.md`](IA/PROMPTS_LOG.md).
