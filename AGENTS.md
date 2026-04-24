# AGENTS.md

## Repository expectations

Este repositorio contiene un proyecto académico de CBD sobre RavenDB.
El objetivo es construir una aplicación mínima y defendible en Java + Spring Boot + Thymeleaf, con foco en modelado documental, consultas RQL y auto-indexes de RavenDB.

## Project scope

Aplicación mínima:
- Productos
- Clientes
- Pedidos

Objetivo académico:
- demostrar modelado documental en RavenDB
- demostrar consultas RQL
- demostrar auto-indexes
- dejar rastro claro y defendible del uso de IA

No es objetivo:
- autenticación
- pagos reales
- frontend complejo
- despliegue cloud
- arquitectura sobrediseñada

## Repository layout

- `src/main/java/com/gr21/ravenshop/`
  - `config/` configuración
  - `controller/` controladores MVC o endpoints
  - `service/` lógica de aplicación
  - `repository/` acceso a RavenDB
  - `model/` modelos de dominio
  - `dto/` objetos de transferencia
  - `seed/` datos semilla
- `src/main/resources/`
  - `templates/` vistas Thymeleaf
  - `static/` recursos estáticos
  - `application.yml` configuración
- `src/test/` pruebas
- `docs/IA/`
  - `PROMPTS_LOG.md`
  - `DECISIONES_IA.md`
  - `prompts/`
  - `sesiones/`
- `docs/spec/` documentos funcionales y técnicos
- `docs/demo/` material de demo
- `issues/` cuerpos markdown para GitHub Issues

## Working rules

- Antes de tocar varios archivos o ejecutar cambios con efectos, presenta primero un plan corto.
- Mantén los cambios pequeños, claros y revisables.
- No implementes funcionalidades fuera del alcance solicitado.
- No añadas dependencias nuevas sin explicar por qué son necesarias.
- No hagas commits, ramas, tags, push ni pull requests salvo instrucción explícita.
- No borres archivos existentes sin justificarlo en el resumen final.
- No ocultes errores: si algo falla, explícalo con claridad.

## IA traceability rules

Registra en `docs/IA/` solo sesiones que modifiquen la aplicación o sus artefactos del proyecto: código, configuración, documentación funcional/técnica, modelos, pruebas o decisiones de implementación.

No registres en `docs/IA/` prompts puramente operativos que no cambien la aplicación, por ejemplo reintentos de `gh auth status`, comprobaciones de login, preguntas de estado o coordinación.

Obligatorio en cada sesión que toque la aplicación:
1. Guardar el prompt usado o un resumen fiel en `docs/IA/prompts/`.
2. Añadir una entrada en `docs/IA/PROMPTS_LOG.md`.
3. Añadir un breve resumen de sesión en `docs/IA/sesiones/`.
4. Registrar en `docs/IA/DECISIONES_IA.md` las decisiones relevantes cuando aplique.

Cada entrada del log debe incluir:
- fecha
- herramienta
- objetivo
- prompt o resumen
- archivos tocados
- resultado
- verificación realizada
- observaciones

## Build, run and test

Usa Maven wrapper si existe:
- `./mvnw test`
- `./mvnw spring-boot:run`

Si no existe wrapper, usa Maven:
- `mvn test`
- `mvn spring-boot:run`

Antes de dar una tarea por terminada:
- intenta compilar o ejecutar pruebas si es razonable
- verifica que los archivos esperados existen
- resume qué validaste realmente

## GitHub rules

Para crear issues:
- comprobar primero `gh auth status`
- si hay autenticación válida, usar `gh issue create`
- si no la hay, dejar preparados los archivos en `issues/` y un bloque de comandos reutilizable

## Definition of done

Una tarea está terminada cuando:
- el cambio solicitado existe realmente en el repo
- el alcance sigue siendo el correcto
- la documentación asociada está actualizada
- el uso de IA ha quedado registrado en `docs/IA/` si la tarea tocó la aplicación o sus artefactos del proyecto
- se ha ejecutado al menos una verificación razonable
- el resumen final explica qué se hizo, qué no se hizo y qué queda pendiente
