# Prompt De Sesion - Base Spring Boot Home MVC

## Prompt recibido

Proyecto academico de CBD sobre RavenDB.
Quiero codigo sencillo, defendible y facil de explicar oralmente.
No metas capas innecesarias ni arquitectura compleja.
No uses autenticacion.
No uses JPA ni bases de datos relacionales.
Usa Java 17 + Spring Boot + RavenDB Java Client + Thymeleaf/MVC simple.
Mi RavenDB local esta en http://127.0.0.1:8085 y la base es RavenShop.
Quiero cambios pequenos, alineados con trazabilidad real y pensados para un commit promedio.
Devuelveme primero un plan breve de archivos a tocar y luego el codigo.
No inventes funcionalidades fuera del alcance del proyecto.

Necesito implementar el proyecto base Spring Boot de RavenShop.
Quiero:
- pom.xml minimo
- clase principal RavenshopApplication
- application.properties basico
- controlador HomeController con GET /
- plantilla index.html con navegacion simple a productos, clientes y pedidos

Restricciones:
- dependencias minimas
- sin seguridad
- sin acceso a base de datos todavia
- estructura de paquetes clara y facil de defender

Done when:
- mvn spring-boot:run arranca
- GET / muestra una pagina simple

## Interpretacion

Dejar el arranque base claramente visible en la raiz MVC de la aplicacion, ajustar el proyecto a Java 17 y mover la configuracion principal a `application.properties`, manteniendo los cambios pequenos y trazables.

## Alcance aplicado

- Ajustar `pom.xml` a Java 17.
- Sustituir `application.yml` por `application.properties`.
- Crear `HomeController` con `GET /`.
- Crear `templates/index.html` con navegacion simple.
- Anadir una prueba MVC minima para `GET /`.
- Actualizar trazabilidad en `docs/IA/` y README.
