# Prompt De Sesion - OpenAPI Y Swagger Runtime

## Prompt original

Añade al aplication properties para que se pueda ver el openap y swagger y lo pueda usar para hacer pruebas con ravendb.

## Objetivo interpretado

Exponer la documentacion OpenAPI y Swagger UI desde la aplicacion Spring Boot para poder probar endpoints contra RavenDB local.

## Alcance acordado

- Configurar Springdoc en `application.properties`.
- Añadir la dependencia necesaria para servir `/v3/api-docs` y Swagger UI.
- Documentar las URLs de uso en el README.
- Registrar la sesion en `docs/IA/`.
- Verificar con una prueba automatica y Maven.

