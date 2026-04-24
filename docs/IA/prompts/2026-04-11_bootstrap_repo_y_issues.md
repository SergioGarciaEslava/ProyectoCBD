# Prompt Maestro De Sesion - 2026-04-11

## Objetivo recibido

Preparar y organizar este repositorio local como base del proyecto academico de CBD sobre RavenDB, llamado RavenShop.

## Stack fijado

- Backend: Java + Spring Boot.
- Frontend: Spring MVC + Thymeleaf.
- Base de datos: RavenDB.
- Build tool: Maven.

## Alcance solicitado

- Auditar el repositorio antes de modificar archivos.
- Crear o normalizar una base Spring Boot minima si el proyecto no existe.
- Anadir dependencias razonables para Web, Thymeleaf, Validation, RavenDB Java Client y Test.
- Crear healthcheck minimo para verificar arranque.
- Generar `README.md`, `Prompt.md`, `AGENTS.md` y estructura `docs/IA/`.
- Registrar trazabilidad de IA en logs, decisiones, prompts y sesiones.
- Leer `backlog_github_issues_ravendb_springboot.md`.
- Crear un archivo Markdown por issue en `issues/`.
- Crear labels e issues reales con `gh issue create` solo si `gh auth status` confirma autenticacion valida.
- Si GitHub CLI no esta disponible, dejar issues y comandos manuales preparados.
- Ejecutar validaciones razonables sin inventar resultados.

## Restricciones clave

- No implementar la aplicacion completa.
- No implementar CRUDs completos.
- No implementar el caso de uso Crear pedido.
- No anadir autenticacion.
- No anadir Docker.
- No crear frontend complejo.
- No cambiar la tecnologia elegida.
- No borrar archivos utiles existentes.
- No hacer commits, push, ramas ni PRs salvo instruccion expresa.
- Evitar Lombok.
- Mantener el resultado simple, defendible y facil de explicar por estudiantes.
