# Sesion - detalle de pedido con lineItems y statusHistory

## Objetivo

Completar la pantalla de detalle de pedido para que muestre de forma clara los subdocumentos embebidos `lineItems` y `statusHistory`, manteniendola en modo solo lectura y util para la defensa del enfoque documental.

## Cambios realizados

- `src/main/resources/templates/orders/detail.html`: anadidas las secciones de `lineItems[]` y `statusHistory[]` con tablas de lectura.
- `src/test/java/com/gr21/ravenshop/controller/OrderControllerTest.java`: ampliada la prueba MVC del detalle para comprobar `productName`, `category`, `unitPrice`, `quantity`, `lineTotal`, `status`, `changedAt` y `comment`.

## Decisiones

- No se cambia backend ni modelo, porque la informacion ya estaba disponible en `Order`.
- `lineItems` y `statusHistory` se muestran como subdocumentos embebidos diferenciados visualmente para reforzar la explicacion oral.
- Se anade un panel RQL didactico de solo lectura para apoyar la defensa del documento agregado en RavenDB.

## Verificacion

- `mvn -q "-Dtest=OrderControllerTest" test`

## Pendiente

- No se anaden todavia acciones de cambio de estado ni edicion; la pantalla queda como lectura pura.
