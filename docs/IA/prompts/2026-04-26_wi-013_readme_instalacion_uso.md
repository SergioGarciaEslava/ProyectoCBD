# Prompt - WI-013 README de instalacion, ejecucion y uso

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Redactar un README que permita a un tercero arrancar el proyecto y entender su uso minimo.

Debe incluir:

- requisitos previos
- como levantar RavenDB
- como arrancar la app Spring Boot
- como cargar datos semilla
- un recorrido minimo de uso
- una seccion de problemas comunes

Restricciones:

- no inventar funcionalidades que no existan en el repo
- mantener el README claro y orientado a instalacion y uso
- no convertirlo en un changelog o en un documento de sprint
- registrar la trazabilidad en `docs/IA/`

## Verificacion documentada

- Revision del estado real del repo: controladores, plantillas, configuracion y seed
- `mvn test`
