# Demo RQL en RavenDB Studio

Documento de apoyo para la defensa tecnica de RavenShop en RavenDB Studio.

Fecha de verificacion local: `2026-04-26`

## Entorno validado

- RavenDB Studio: `http://127.0.0.1:8085`
- Base de datos: `RavenShop`
- Aplicacion Spring Boot esperada: `http://127.0.0.1:8081`

Estado de la base verificado el `2026-04-26`:

- `Products`: 3 documentos
- `Customers`: 2 documentos
- `Orders`: 3 documentos

Este documento asume la estructura documental actual del proyecto:

- `Orders` con `status`, `customerSnapshot`, `lineItems`, `total` y `statusHistory`
- `Products` con `category`, `price`, `stock` y `tags`

## Pre-demo recomendada

Antes de la defensa:

1. Arrancar RavenDB en `127.0.0.1:8085`.
2. Confirmar que la base activa es `RavenShop`.
3. Arrancar la app en `127.0.0.1:8081` si quieres crear un pedido de prueba desde la UI.
4. Crear un pedido nuevo y dejarlo sin cambio de estado si quieres que la consulta de pendientes devuelva al menos 1 resultado.
5. Si quieres ensenar auto-indexes "en limpio", borrar antes los auto-indexes de prueba desde Studio y volver a ejecutar las consultas.

## Consultas RQL para la defensa

## 1. Pedidos pendientes

Objetivo: ensenar una query simple de negocio sobre la coleccion `Orders`.

```rql
from Orders
where status = "Pending"
order by orderedAt desc
```

Que explica bien:

- filtro por estado actual del pedido
- ordenacion por fecha del pedido
- auto-index sobre campos de negocio frecuentes

Verificacion local del `2026-04-26`:

- Consulta valida en RavenDB.
- Resultado actual en esta base: `0` documentos.
- Motivo: en esta base local no habia pedidos con estado actual `Pending`; habia pedidos en `Paid`, `Processing` y `Shipped`.
- Indice usado en esta verificacion: `Auto/Orders/ByorderedAtAndstatus`.

Preparacion recomendada para la defensa:

- Crear un pedido nuevo desde `/orders/new`.
- No cambiar su estado antes de abrir Studio.
- Volver a ejecutar la query para obtener un resultado visible.

## 2. Pedidos por total minimo y ciudad

Objetivo: demostrar consulta sobre un campo agregado (`total`) y un subdocumento embebido (`customerSnapshot.city`).

```rql
from Orders
where total >= 5000000
  and customerSnapshot.city = "Ninguna"
order by total desc
```

Que explica bien:

- filtrado numerico
- filtrado por campo embebido sin joins
- ordenacion descendente por total

Verificacion local del `2026-04-26`:

- Consulta valida en RavenDB.
- Resultado actual en esta base: `3` documentos.
- Indice usado en esta verificacion: `Auto/Orders/BycustomerSnapshot.cityAndtotal`.

Resultado esperado facil de comentar:

- RavenDB resuelve la ciudad historica del cliente desde el propio pedido.
- No hace falta cargar `Customers` aparte para filtrar.

## 3. Productos por categoria o etiqueta

Objetivo: ensenar una query sobre `Products` con combinacion de campo simple y array.

```rql
from Products
where category = "Coches"
   or tags = "demo"
order by price desc
```

Que explica bien:

- filtro por categoria
- filtro alternativo por etiqueta
- soporte natural de arrays en documentos

Verificacion local del `2026-04-26`:

- Consulta valida en RavenDB.
- Resultado actual en esta base: `2` documentos.
- Productos visibles en la verificacion: `Ferrari F40` y `PruebaOK`.
- Indice usado en esta verificacion: `Auto/Products/BycategoryAndpriceAndstockAndtags`.

Nota:

- El nombre exacto del auto-index puede variar si la base ya tenia consultas previas y RavenDB fusiona campos en un indice automatico mas amplio.

## Orden sugerido de ejecucion en Studio

1. Abrir `http://127.0.0.1:8085`.
2. Entrar en `RavenShop`.
3. Ir a `Queries` -> `Query View`.
4. Confirmar que `Disable creating new Auto-Indexes` esta desactivado.
5. Ejecutar primero la consulta de `Products`, porque es la mas visual y devuelve resultados claros en esta base.
6. Ejecutar despues la consulta de `Orders` por `total` y `customerSnapshot.city`, para explicar embebidos sin joins.
7. Ejecutar al final la consulta de pendientes, idealmente despues de haber creado un pedido nuevo en estado `Pending`.

## Que mirar en Studio mientras hablas

- `Results`: numero de documentos devueltos y campos visibles.
- `IndexName`: coleccion o auto-index usado por RavenDB.
- `IsStale`: mejor si aparece `false`.
- `Indexes` -> `Indexes List View`: localizar el auto-index generado o reutilizado.

## Frases cortas defendibles

1. "Esta query filtra por un campo del propio documento y RavenDB decide si necesita un auto-index."
2. "Aqui el pedido ya lleva embebido `customerSnapshot`, asi que puedo filtrar por ciudad sin joins."
3. "Las etiquetas van dentro del documento `Product`, y RQL permite consultarlas directamente."
4. "El nombre del auto-index puede cambiar, pero la idea importante es que RavenDB lo genera o adapta solo."

## Evidencia minima a guardar

- Captura de la consulta de `Products` con resultados.
- Captura de la consulta de `Orders` por `total` y `customerSnapshot.city`.
- Captura del auto-index visible en `Indexes`.
- Si preparas un pedido pendiente, captura tambien la query de `Pending` con al menos 1 resultado.
