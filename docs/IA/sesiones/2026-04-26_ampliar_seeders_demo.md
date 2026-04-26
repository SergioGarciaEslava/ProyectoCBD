# Sesion IA - Ampliar seeders para demo

## Objetivo

Ampliar exclusivamente los datos semilla del proyecto para disponer de mas ejemplos utiles en productos, clientes y pedidos sin cambiar la estructura del seeder ni el alcance funcional de la aplicacion.

## Cambios realizados

- `RavenDbSeedRunner` mantiene la misma estructura general, pero amplia el volumen de datos sembrados.
- Productos ampliados de 6 a 12, con categorias repetibles para RQL (`Bebidas`, `Snacks`, `Despensa`), varios prefijos reutilizables (`Cafe`) y tags variados para consultas futuras.
- Clientes ampliados de 3 a 8, con ciudades distintas y fechas de alta distribuidas.
- Pedidos ampliados de 4 a 12, con mas combinaciones de lineas, distintos importes totales y estados variados (`Pending`, `Paid`, `Processing`, `Shipped`, `Delivered`, `Cancelled`).
- Historiales de estado mas ricos para poder demostrar subdocumentos embebidos y recorridos temporales.
- `SEED_MARKER_ID` actualizado a `seed-data/ravenshop-wi021` para permitir resembrar este conjunto ampliado en bases que ya tenian el marker anterior.
- `RavenDbSeedRunnerTest` actualizado para reflejar los nuevos conteos esperados.

## Verificacion

- `mvn -q -Dtest=RavenDbSeedRunnerTest test`

## Observaciones

No se han tocado controladores, servicios, repositorios, vistas ni configuracion funcional. El cambio queda limitado al seed y a su trazabilidad obligatoria.
