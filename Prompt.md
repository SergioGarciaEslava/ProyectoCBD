# Prompt Maestro - RavenShop

## Goals

- Preparar un proyecto academico de CBD centrado en RavenDB.
- Demostrar modelado y consulta de documentos complejos con `Products`, `Customers`, `Orders` y `Reviews` opcional.
- Usar Java, Spring Boot, Spring MVC, Thymeleaf, RavenDB Java Client y Maven.
- Mantener el proyecto simple, defendible y facil de explicar por estudiantes.
- Documentar el uso de IA de forma trazable en `docs/IA/`.

## Non-goals

- No construir una tienda completa.
- No implementar autenticacion.
- No introducir Docker en esta fase.
- No crear un frontend complejo.
- No implementar CRUDs completos durante el bootstrap.
- No implementar todavia el caso de uso Crear pedido.
- No cambiar la tecnologia decidida.

## Hard constraints

- Trabajar siempre sobre este repositorio local.
- No abrir pull requests.
- No hacer commits salvo instruccion expresa.
- No borrar archivos utiles existentes sin explicarlo.
- No introducir dependencias nuevas sin justificarlo.
- Evitar Lombok salvo necesidad clara.
- Mantener nombres claros y estilo academico/profesional.
- Registrar cada sesion de IA en `docs/IA/`.

## Deliverables

- Proyecto Spring Boot minimo con Maven.
- Estructura base de paquetes para configuracion, controladores, servicios, repositorios, modelos, DTOs y seed.
- Endpoint minimo de salud para verificar arranque.
- Documentacion base: `README.md`, `AGENTS.md`, `Prompt.md`.
- Trazabilidad IA: `PROMPTS_LOG.md`, `DECISIONES_IA.md`, prompt de sesion y resumen de sesion.
- Issues preparados en Markdown desde el backlog y creados en GitHub si el entorno lo permite.

## Done when

- La estructura de carpetas existe y es coherente con el proyecto.
- `mvn test` compila y ejecuta las pruebas disponibles.
- La aplicacion puede arrancar con `mvn spring-boot:run`.
- `GET /health` responde correctamente.
- Los documentos de `docs/IA/` estan creados y actualizados.
- Las issues estan preparadas en `issues/` y, si `gh` esta autenticado, creadas en GitHub.

## Demo flow esperado

1. Arrancar RavenDB local cuando se implemente la conexion real.
2. Arrancar RavenShop con Spring Boot.
3. Cargar datos semilla de productos, clientes y pedidos.
4. Mostrar documentos en RavenDB Studio.
5. Ejecutar operaciones minimas desde la aplicacion.
6. Mostrar consultas RQL y auto-indexes generados por RavenDB.
7. Explicar decisiones de modelado documental y trazabilidad del uso de IA.
