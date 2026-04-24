# Sesion 2026-04-20 - Ejecucion De WI-005

## Objetivo

Implementar CRUD de productos con soporte REST y MVC/Thymeleaf para demo academica, incluyendo update, listado, alta, edicion y borrado logico.

## Acciones realizadas

- Aplicado TDD:
  - Tests RED para `ProductService`, `ProductController` y nuevo `ProductViewController`.
  - Implementacion minima para pasar tests.
  - Ejecucion GREEN de tests objetivo y suite completa.
- Extendido `ProductService` con:
  - `update(productId, request)`
  - `deactivate(productId)` para borrado logico.
  - filtrado de productos activos en `list` para evitar mostrar borrados.
- Extendido `ProductController` REST con:
  - `PUT /api/v1/products/{productId}`
  - `DELETE /api/v1/products/{productId}` (204, 404 si no existe).
- Implementado `ProductViewController` para Thymeleaf:
  - `GET /products`
  - `GET /products/new`
  - `POST /products`
  - `GET /products/{id}/edit`
  - `POST /products/{id}`
  - `POST /products/{id}/delete`
- Añadidas plantillas:
  - `templates/products/list.html`
  - `templates/products/form.html`

## Archivos creados o modificados

- `src/main/java/com/gr21/ravenshop/service/ProductService.java`
- `src/main/java/com/gr21/ravenshop/controller/ProductController.java`
- `src/main/java/com/gr21/ravenshop/controller/ProductViewController.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/ProductForm.java` (nuevo)
- `src/main/resources/templates/products/list.html` (nuevo)
- `src/main/resources/templates/products/form.html` (nuevo)
- `src/test/java/com/gr21/ravenshop/service/ProductServiceTest.java`
- `src/test/java/com/gr21/ravenshop/controller/ProductControllerTest.java`
- `src/test/java/com/gr21/ravenshop/controller/ProductViewControllerTest.java` (nuevo)
- `issues/05_implementar_crud_de_productos.md`
- `README.md`
- `docs/IA/PROMPTS_LOG.md`
- `docs/IA/DECISIONES_IA.md`
- `docs/IA/prompts/2026-04-20_wi-005_crud_productos_mvc_thymeleaf.md` (nuevo)
- `docs/IA/sesiones/2026-04-20_wi-005_crud_productos_mvc_thymeleaf.md` (nuevo)

## Validaciones ejecutadas

- `mvn -q -Dtest=ProductServiceTest,ProductControllerTest,ProductViewControllerTest test` -> OK
- `mvn test` -> OK (20 tests, 0 fallos)

## Pendientes

- Integracion en `trunk` via PR + merge.
- Evidencia visual (capturas) del flujo MVC con RavenDB local levantado.
