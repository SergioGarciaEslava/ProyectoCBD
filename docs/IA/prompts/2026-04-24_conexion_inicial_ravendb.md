# Prompt De Sesion - Conexion Inicial RavenDB

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

Sobre el proyecto base ya creado, implementa solo la conexion inicial con RavenDB.
Quiero:
- clase RavenDbConfig
- bean DocumentStore
- propiedades externalizadas para url y database
- endpoint GET /health-db que abra una sesion y devuelva si la conexion funciona

Restricciones:
- no crear aun modelos
- no crear repositorios complejos
- codigo corto y facil de explicar

Done when:
- la aplicacion arranca
- GET /health-db comprueba conexion contra http://127.0.0.1:8085 y base RavenShop

## Interpretacion

Ajustar la conexion ya existente para dejarla mas simple y alineada con el requisito actual: propiedades en singular (`url`, `database`) y endpoint `GET /health-db` con respuesta corta y defendible.

## Alcance aplicado

- Simplificar `RavenDbProperties` a `url` y `database`.
- Ajustar `RavenDbConfig` para crear el `DocumentStore` con esas propiedades.
- Exponer `GET /health-db` en `RavenDbHealthController`.
- Actualizar prueba MVC, README y trazabilidad IA.
