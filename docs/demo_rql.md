# Demo RQL en RavenDB Studio

Contexto de esta demo:

- RavenDB Studio: `http://127.0.0.1:8085`
- Base de datos: `RavenShop`
- A fecha de `2026-04-24`, en la base local comprobada hay documentos en `Products`.

## Consulta RQL recomendada

```rql
from Products
where category = "Coches" and stock > 0
order by price desc
```

Por que sirve para la demo:

- Es facil de leer.
- Tiene un filtro real de negocio.
- Obliga a RavenDB a usar o crear un Auto-Index.
- El resultado es corto y facil de explicar.

## Pasos en RavenDB Studio

1. Abrir `http://127.0.0.1:8085`.
2. Entrar en la base `RavenShop`.
3. Ir a `Queries` -> `Query View`.
4. Verificar que la base activa es `RavenShop`.
5. En `Query Settings`, dejar desactivada la opcion `Disable creating new Auto-Indexes`.
6. Pegar la consulta RQL.
7. Pulsar `Run Query`.
8. En el panel de resultados, mirar `Index or Collection Used` o `Statistics` para ver el indice usado.

## Como localizar el Auto-Index

1. Ir a `Indexes` -> `Indexes List View`.
2. Buscar un indice cuyo nombre empiece por `Auto/Products/`.
3. Abrir ese indice y ensenar:
   - el nombre
   - el tipo `Auto Map`
   - la coleccion `Products`
4. Si quieres remarcar la relacion con la consulta, comenta que el nombre suele incluir los campos filtrados y ordenados.

## Frases orales faciles de defender

1. "Esta consulta filtra productos por categoria y stock, asi que RavenDB necesita un indice para responder rapido."
2. "Yo no he creado este indice a mano; RavenDB lo genera automaticamente como Auto-Index."
3. "La ventaja academica es que se ve muy bien la diferencia entre documentos y mecanismo de consulta."
4. "En Studio puedo ejecutar la RQL y justo despues ensenar el indice automatico que ha aparecido."
5. "Esto demuestra que RavenDB optimiza consultas frecuentes sin obligarme a definir todos los indices desde el principio."

## Nota practica

Si antes de la demo quieres ensenar tambien `Customers` o `Orders`, primero necesitas tener documentos reales en esas colecciones en tu base local.
