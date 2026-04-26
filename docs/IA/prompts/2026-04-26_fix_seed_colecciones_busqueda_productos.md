# Prompt - Fix seed colecciones y busqueda productos

## Prompt recibido

Se reporta una ejecucion local de:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--ravenshop.seed.enabled=true"
```

con dos problemas:

- `DocumentCollectionMismatchException` al crear clientes: RavenDB intenta cambiar `customers/1-A` de `CustomerDocs` a `Customers`.
- La busqueda de productos por `Cafe` no devuelve resultados aunque el texto aparece en la pagina.

Se solicita depurar sistematicamente a que se debe.

## Objetivo

Identificar la causa raiz y corregir el seed y la busqueda sin ampliar el alcance del proyecto.
