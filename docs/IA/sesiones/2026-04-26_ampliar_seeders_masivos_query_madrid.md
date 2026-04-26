# Sesion IA - Ampliar seeders masivos con bloque Madrid >= 100

## Objetivo

Escalar fuertemente los datos semilla sin cambiar la estructura funcional del proyecto y garantizar un conjunto amplio de pedidos compatibles con la consulta RQL de Madrid con total minimo de 100.

## Cambios realizados

- `RavenDbSeedRunner` mantiene la misma organizacion general (`storeProducts`, `storeCustomers`, `storeOrders`) y los helpers existentes.
- Se anade generacion determinista adicional de 60 productos, 180 clientes y 800 pedidos.
- El seed total queda en 78 productos, 192 clientes y 820 pedidos.
- Se introduce un bloque recurrente de pedidos generados de clientes de Madrid con importes altos para cumplir la query:
  `from Orders where total >= 100 and customerSnapshot.city = "Madrid" order by total desc`
- Los pedidos generados reparten estados (`Pending`, `Paid`, `Processing`, `Shipped`, `Delivered`, `Cancelled`) y mantienen `statusHistory`.
- El marker cambia a `seed-data/ravenshop-wi023` para permitir aplicar este seed ampliado en bases con el marker anterior.
- `RavenDbSeedRunnerTest` se actualiza a los nuevos conteos y comprueba que existe al menos un pedido compatible con la query de Madrid.
- `README.md` actualiza el marker y documenta el bloque de pedidos de Madrid con total alto.

## Verificacion

- `mvn -q -Dtest=RavenDbSeedRunnerTest test`

## Observaciones

No se tocaron controladores, servicios, repositorios ni vistas. La ampliacion se limito al seed, su prueba y la documentacion operativa/trazabilidad necesaria.
