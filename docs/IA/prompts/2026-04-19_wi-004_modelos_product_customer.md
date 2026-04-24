# Prompt De Sesion - WI-004 Modelos Product y Customer

## Prompt recibido

Haz la siguiente.

Luego se concretó:
- arquitectura por capas (controller, service, repository, model, dto)
- elegir opcion 1 (implementacion completa por capas)
- crear los test necesarios sin exceso

## Interpretacion

Implementar WI-004 con un alcance minimo y defendible: modelos de dominio, DTOs, repositorios RavenDB, servicios y controladores para Products y Customers, con operaciones basicas (`create`, `getById`, `list`) y pruebas automaticas ajustadas al alcance.

## Alcance aplicado

- Crear entidades de dominio `Product` y `Customer`.
- Crear DTOs request/response y paginacion para products/customers.
- Crear interfaces de repositorio e implementaciones RavenDB.
- Crear capa service para mapeo y reglas minimas de IDs/paginado.
- Crear controllers REST en `/api/v1/products` y `/api/v1/customers`.
- Añadir tests unitarios de service y MVC tests de controllers.
- Ejecutar `mvn test` para validar no regresiones.
