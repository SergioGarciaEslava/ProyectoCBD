# Sesion 2026-04-24 - OpenAPI Y Swagger Runtime

## Objetivo

Permitir ver OpenAPI y Swagger UI desde la aplicacion para probar endpoints con RavenDB local.

## Acciones realizadas

- Añadida la dependencia `springdoc-openapi-starter-webmvc-ui`.
- Configurados `springdoc.api-docs.*` y `springdoc.swagger-ui.*` en `application.properties`.
- Añadida prueba automatica para `/v3/api-docs` y `/swagger-ui.html`.
- Documentadas las URLs de Swagger UI, OpenAPI JSON y salud RavenDB en el README.

## Archivos tocados

- `pom.xml`
- `src/main/resources/application.properties`
- `src/test/java/com/gr21/ravenshop/controller/OpenApiDocumentationTest.java`
- `README.md`
- `docs/IA/PROMPTS_LOG.md`
- `docs/IA/DECISIONES_IA.md`
- `docs/IA/prompts/2026-04-24_openapi_swagger_runtime.md`
- `docs/IA/sesiones/2026-04-24_openapi_swagger_runtime.md`

## Verificacion

- TDD rojo inicial: `mvn -q -Dtest=OpenApiDocumentationTest test` fallo con 404 en `/v3/api-docs` y `/swagger-ui.html`.
- `mvn -q -Dtest=OpenApiDocumentationTest test`: 2 tests en verde.
- `mvn test`: 39 tests en verde.

## Observaciones

- Springdoc se añade porque Spring Boot no expone Swagger UI ni OpenAPI dinamico por defecto.
- La version usada es `2.8.17`, alineada con Spring Boot 3.x.
