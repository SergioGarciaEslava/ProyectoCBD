# Sesion 2026-04-23 - Base Spring Boot Home MVC

## Objetivo

Implementar el arranque base MVC de RavenShop con Java 17, configuracion en `application.properties` y una portada simple accesible desde `GET /`.

## Acciones realizadas

- Ajustado `pom.xml` para compilar con Java 17.
- Sustituido `src/main/resources/application.yml` por `src/main/resources/application.properties`.
- Configurado `ravendb.urls[0]=http://127.0.0.1:8085`, `ravendb.database=RavenShop` y `ravenshop.seed.enabled=false`.
- Creado `HomeController` con `GET /` devolviendo la vista `index`.
- Creada la plantilla `templates/index.html` con enlaces simples a `products`, `customers` y `orders`.
- Anadida una prueba MVC minima para validar la portada.
- Actualizado `README.md` para reflejar Java 17 y `application.properties`.

## Archivos creados o modificados

- `pom.xml`
- `README.md`
- `src/main/resources/application.properties` (nuevo)
- `src/main/resources/application.yml` (eliminado)
- `src/main/java/com/gr21/ravenshop/controller/HomeController.java` (nuevo)
- `src/main/resources/templates/index.html` (nuevo)
- `src/test/java/com/gr21/ravenshop/controller/HomeControllerTest.java` (nuevo)
- `docs/IA/PROMPTS_LOG.md`
- `docs/IA/DECISIONES_IA.md`
- `docs/IA/prompts/2026-04-23_base_spring_boot_home_mvc.md` (nuevo)
- `docs/IA/sesiones/2026-04-23_base_spring_boot_home_mvc.md` (nuevo)

## Validaciones ejecutadas

- `mvn test`
- `mvn spring-boot:run` con override temporal `--server.port=18080` porque `8080` estaba ocupado en el entorno
- `GET /` sobre `http://127.0.0.1:18080/` con respuesta `200`

## Pendientes

- Ninguno dentro del alcance de esta sesion base.
