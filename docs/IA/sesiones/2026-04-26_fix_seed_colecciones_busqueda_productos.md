# Sesion IA - Fix seed colecciones y busqueda productos

## Objetivo

Corregir dos fallos observados en ejecucion local con seed activado:

- Error de RavenDB por cambio de coleccion de `CustomerDocs` a `Customers`.
- Busqueda de productos por `Cafe` sin resultados.

## Diagnostico

- El seed guardaba clientes con el record interno `CustomerDoc`; RavenDB asignaba esos documentos a la coleccion `CustomerDocs`.
- El formulario de clientes trabaja con `com.gr21.ravenshop.model.Customer`, cuya coleccion RavenDB es `Customers`.
- RavenDB no permite cambiar la coleccion de un documento existente mediante una actualizacion; hay que borrar y recrear el documento.
- El seed tambien guardaba pedidos con `OrderDoc`, lo que podia provocar el mismo tipo de fallo al actualizar pedidos.
- La RQL de busqueda usaba `startsWith(Name, ...)`, pero Jackson serializa el POJO `Product` con el campo JSON `name`.

## Cambios realizados

- `RavenDbSeedRunner` ahora guarda clientes como `Customer` y pedidos como `Order`.
- Los pedidos seed incluyen `customerSnapshot`, `lineItems`, `statusHistory`, `orderedAt`, `status`, `shippingAddress` y total recalculado.
- `RavenProductRepository.searchByNamePrefix` usa `startsWith(name, $namePrefix)`.
- `RavenCustomerRepository.save` asigna IDs explicitos `customers/<uuid>` a clientes nuevos, evitando el generador secuencial cuando existen documentos antiguos `CustomerDocs` con IDs `customers/1-A`, `customers/2-A`, etc.
- `RavenOrderRepository.save` asigna IDs explicitos `orders/<uuid>` a pedidos nuevos, evitando el generador secuencial cuando existen documentos antiguos `OrderDocs` con IDs `orders/1-A`, `orders/2-A`, etc.
- La vista de productos y la portada muestran la RQL/campo real `Products.name`.
- README documenta como resolver una base local ya sembrada con colecciones antiguas.
- Tests reforzados para comprobar tipos del seed y RQL emitida.
- Test añadido para comprobar que las altas nuevas de clientes se guardan con ID explicito.
- Test añadido para comprobar que las altas nuevas de pedidos se guardan con ID explicito.

## Verificacion

- Rojo inicial: `mvn -q -Dtest=RavenDbSeedRunnerTest,RavenProductRepositoryTest test`.
- Verde enfocado: `mvn -q -Dtest=RavenDbSeedRunnerTest,RavenProductRepositoryTest,ProductControllerTest,HomeControllerTest test`.
- Rojo adicional: `mvn -q -Dtest=RavenCustomerRepositoryTest test` fallo porque el repositorio llamaba a `store(customer)` sin ID.
- Verde adicional: `mvn -q -Dtest=RavenCustomerRepositoryTest,CustomerServiceTest,CustomerControllerTest test`.
- Rojo adicional: `mvn -q -Dtest=RavenOrderRepositoryTest test` fallo porque el repositorio llamaba a `store(order)` sin ID.
- Verde adicional: `mvn -q -Dtest=RavenOrderRepositoryTest,OrderServiceTest,OrderControllerTest test`.
- `mvn test` fallo inicialmente por clases de test obsoletas en `target` con errores sobre `List.getFirst()`.
- `mvn clean test` recompilo main y test con `release 21` y paso 79 tests.

## Observaciones

Si una base local ya contiene `customers/1-A` en `CustomerDocs` u `orders/1-A` en `OrderDocs`, el arreglo de codigo evita que vuelva a pasar en bases nuevas, pero esa base debe limpiarse recreando `RavenShop` o borrando los documentos antiguos y el marcador de seed.
