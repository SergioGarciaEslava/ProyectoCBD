# Prompt - WI-020 pedidos pulidos, busqueda con auto-index y paneles RQL didacticos

## Fecha

2026-04-25

## Herramienta

Claude Code (Opus 4.7)

## Prompt o resumen fiel

Implementar WI-020 para cerrar el sprint final de demo. El plan acordado en `docs/IA/prompts/2026-04-25_plan_sprint_demo.md` contiene cinco sub-tareas:

1.  **Backend de busqueda por nombre con RQL real**: añadir metodo `searchByNamePrefix` en `ProductRepository` y `RavenProductRepository`, exponer `searchProductsByName` en `ProductService` y aceptar parametro `q` en `ProductController.list(...)`. La RQL elegida es `from Products where startsWith(Name, $namePrefix)`, que provoca la creacion del auto-index `Auto/Products/ByName` visible en RavenDB Studio.
2.  **Refuerzo del seed**: añadir `products/5-A` (Cafe instantaneo) y `products/6-A` (Cafe descafeinado) para que la busqueda por prefijo `Cafe` devuelva varios resultados durante la demo. Cambiar `SEED_MARKER_ID` a `seed-data/ravenshop-wi020` para que el seed se aplique de nuevo en bases ya sembradas.
3.  **UI de busqueda en `/products`**: formulario `GET` con un unico parametro `q`, resumen de resultados, boton para limpiar y panel `<aside class="rql-panel">` que muestra la RQL ejecutada en directo y explica el auto-index generado.
4.  **Pulido visual del detalle de pedido**: marcar las tres secciones embebidas (`customerSnapshot`, `lineItems`, `statusHistory`) con un estilo distinto del documento principal y aplicar pills de color por estado (`pending`, `paid`, `shipped`, `cancelled`, `processing`, `created`).
5.  **Paneles RQL didacticos**: aside con la RQL equivalente bajo el listado de productos y bajo el detalle de pedido. Texto estatico en plantilla, sin instrumentacion runtime.

## Restricciones aplicadas

- Sin nuevas dependencias frontend ni backend.
- Sin tocar `pom.xml` ni propiedades de la aplicacion.
- Sin JavaScript: el formulario de busqueda es un `GET` clasico de Spring MVC.
- La RQL elegida es `startsWith(Name, $q)` y no `search(Name, $q)`, para no abrir la conversacion sobre full-text indexes y analizadores en la defensa.
- El seed se mantiene idempotente con marcador unico; cambiar el marker es la forma documentada de re-aplicar datos sin romper la convencion.
- Tests existentes deben seguir pasando; los tests del listado de productos se actualizan para mockear `searchProductsByName` y se añade un test nuevo para la ruta con `?q=...`.
- No se implementa el listado `/orders` (queda fuera del sprint, ver `docs/IA/prompts/2026-04-25_plan_sprint_demo.md`).
- No se implementa el refactor a fragmentos Thymeleaf (WI-018 aplazado).

## Verificacion documentada

- `mvn test`: 59 tests OK (subio de 58 con el nuevo `listViewFiltersByNamePrefixWhenQueryProvided`).
- Arranque local en `:8081` con `mvn spring-boot:run`. Sin RavenDB local en `127.0.0.1:8085`, los endpoints que tocan la base devuelven 500 por conexion rehusada; las plantillas estan validadas via `MockMvc` en los tests.
- CSS servido correctamente: `curl /css/app.css` muestra `.search-bar`, `.rql-panel`, `.embedded-section`, `.status-pill--paid`, etc.
- Verificacion del auto-index en RavenDB Studio queda pendiente del arranque local con `--ravenshop.seed.enabled=true` y RavenDB levantado por Docker (instrucciones en README).
