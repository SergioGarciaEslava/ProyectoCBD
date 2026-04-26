# Sesion - orden descendente en listado de pedidos

## Objetivo

Reforzar que el listado de pedidos quede ordenado por `orderedAt` descendente, incluso cuando la fecha se completa durante el enriquecimiento del servicio.

## Cambios realizados

- `src/main/java/com/gr21/ravenshop/service/OrderService.java`: ordenacion final por `orderedAt desc` despues de `enrichForListView(...)`.
- `src/test/java/com/gr21/ravenshop/service/OrderServiceTest.java`: prueba de orden descendente con un pedido que recibe `orderedAt` derivado desde `statusHistory`.

## Decisiones

- Se mantiene la query RQL coherente con la demo, pero se refuerza el orden en el servicio para cubrir documentos antiguos sin `orderedAt` persistido.
- No se anaden opciones de ordenacion nuevas ni cambios en la vista.

## Verificacion

- `mvn -q "-Dtest=OrderServiceTest" test`

## Pendiente

- En este arbol local no aparece implementada la variante con filtros en `GET /orders`; por tanto aqui solo se ha ajustado el camino de listado existente.
