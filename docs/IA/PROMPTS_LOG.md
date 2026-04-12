# Prompts Log

Registro operativo de uso de IA para RavenShop. Cada fila debe permitir reconstruir que se pidio, que se obtuvo y como se verifico.

| Fecha | Herramienta | Objetivo | Prompt o resumen del prompt | Archivos tocados | Resultado | Verificacion realizada | Observaciones |
|---|---|---|---|---|---|---|---|
| 2026-04-11 | Codex | Bootstrap del repositorio, documentacion IA e issues | Preparar el repo RavenShop como base academica CBD con Spring Boot, Thymeleaf, Maven y RavenDB; no implementar CRUDs; crear docs de IA e issues desde backlog. | `README.md`, `AGENTS.md`, `Prompt.md`, `.gitignore`, `pom.xml`, `src/`, `docs/IA/`, `issues/` | Base Spring Boot creada; documentacion IA creada; 16 issues preparadas en Markdown; issues remotas no creadas porque falta `gh`. | TDD rojo/verde para `/health`; `mvn test`; arranque con `mvn -q -DskipTests spring-boot:run`; `curl http://127.0.0.1:8080/health`; comprobacion de estructura y docs. | Maven necesito acceso fuera del sandbox para resolver dependencias en `~/.m2`; `curl` al endpoint local tambien necesito ejecucion fuera del sandbox. |
| 2026-04-11 | Codex | Cambiar version Java del proyecto | "Quiero usar java 21 en vez de java 17." | `pom.xml`, `README.md`, `docs/IA/PROMPTS_LOG.md`, `docs/IA/prompts/2026-04-11_java_21.md`, `docs/IA/sesiones/2026-04-11_java_21.md` | Proyecto configurado para Java 21 y README actualizado. | `mvn test`; `mvn clean test` recompilo main y test con `javac ... release 21` y paso 1 test. | El JDK local activo es Java 25, compatible para compilar con release 21. |
| 2026-04-12 | Codex | Ejecutar WI-001 en rama dedicada | "Haz la primera issue, creando la rama necesaria..." | `issues/01_crear_proyecto_base_spring_boot.md`, `docs/IA/PROMPTS_LOG.md`, `docs/IA/DECISIONES_IA.md`, `docs/IA/prompts/2026-04-12_wi-001_primera_issue.md`, `docs/IA/sesiones/2026-04-12_wi-001_primera_issue.md` | Rama WI-001 creada, checklist de la issue actualizada y evidencia de ejecucion registrada. | `git switch -c featuretask/WI-001-crear-proyecto-base-spring-boot`; `mvn test` (BUILD SUCCESS, 1 test OK). | WI-001 ya estaba implementada en `main`; en esta sesion se formalizo su ejecucion en rama y su trazabilidad para revision y commit/push. |

## Plantilla para nuevas entradas

| Fecha | Herramienta | Objetivo | Prompt o resumen del prompt | Archivos tocados | Resultado | Verificacion realizada | Observaciones |
|---|---|---|---|---|---|---|---|
| YYYY-MM-DD |  |  |  |  |  |  |  |
