# Sesion - WI-013 README de instalacion, ejecucion y uso

## Objetivo

Dejar un README util para profesores y companeros, centrado en reproducir el proyecto, cargar seed y recorrer las funcionalidades principales.

## Cambios realizados

- `README.md`: reescritura completa con foco en instalacion, arranque, seed, recorrido minimo, funcionalidades y problemas comunes.
- `docs/IA/prompts/2026-04-26_wi-013_readme_instalacion_uso.md`: registro del prompt/resumen.
- `docs/IA/sesiones/2026-04-26_wi-013_readme_instalacion_uso.md`: resumen de sesion.
- `docs/IA/PROMPTS_LOG.md`: nueva entrada de trazabilidad.
- `docs/IA/DECISIONES_IA.md`: decisiones relevantes de esta documentacion.

## Decisiones

- Reescribir el README completo en lugar de parchearlo, porque el contenido anterior mezclaba instalacion con contexto de sprint y partes ya desalineadas con el estado real del repo.
- Documentar solo funcionalidades comprobadas en controladores, vistas y configuracion actuales.
- Mantener comandos reproducibles con Maven normal, porque en este repositorio no existe Maven Wrapper.

## Verificacion

- Revision de `application.properties`, controladores MVC, plantillas de pedidos y seed para contrastar rutas y comportamiento reales.
- `mvn test`

## Pendiente

- Ninguno dentro del alcance de esta tarea documental.
