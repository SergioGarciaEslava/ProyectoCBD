# Sesion 2026-04-19 - Ejecucion De WI-004

## Objetivo

Implementar la base del CRUD para Products y Customers con arquitectura por capas (model, dto, repository, service, controller) y pruebas automaticas minimas.

## Acciones realizadas

- Revisado alcance de `issues/04_modelar_documentos_products_y_customers.md` y contrato OpenAPI.
- Aplicado TDD para WI-004:
  - Creacion de tests RED para services y controllers.
  - Implementacion de clases de dominio, DTOs, repositorios, servicios y controladores.
  - Ajuste de tests de path variables para validar logica (no solo routing).
  - Ejecucion GREEN de tests especificos y suite completa.
- Implementadas operaciones basicas para `products` y `customers`:
  - `POST /api/v1/products`, `GET /api/v1/products/{productId}`, `GET /api/v1/products`
  - `POST /api/v1/customers`, `GET /api/v1/customers/{customerId}`, `GET /api/v1/customers`
- Repositorios RavenDB con operaciones basicas de persistencia/consulta (`save`, `findById`, `findAll`).

## Archivos creados o modificados

- `src/main/java/com/gr21/ravenshop/model/Product.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/model/Customer.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/ProductCreateRequest.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/ProductResponse.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/ProductPageResponse.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/CustomerCreateRequest.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/CustomerResponse.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/CustomerPageResponse.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/dto/PaginationResponse.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/repository/ProductRepository.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/repository/CustomerRepository.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/repository/RavenProductRepository.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/repository/RavenCustomerRepository.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/service/ProductService.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/service/CustomerService.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/controller/ProductController.java` (nuevo)
- `src/main/java/com/gr21/ravenshop/controller/CustomerController.java` (nuevo)
- `src/test/java/com/gr21/ravenshop/service/ProductServiceTest.java` (nuevo)
- `src/test/java/com/gr21/ravenshop/service/CustomerServiceTest.java` (nuevo)
- `src/test/java/com/gr21/ravenshop/controller/ProductControllerTest.java` (nuevo)
- `src/test/java/com/gr21/ravenshop/controller/CustomerControllerTest.java` (nuevo)
- `issues/04_modelar_documentos_products_y_customers.md`
- `README.md`
- `docs/IA/PROMPTS_LOG.md`
- `docs/IA/DECISIONES_IA.md`
- `docs/IA/prompts/2026-04-19_wi-004_modelos_product_customer.md` (nuevo)
- `docs/IA/sesiones/2026-04-19_wi-004_modelos_product_customer.md` (nuevo)

## Validaciones ejecutadas

- `mvn -q -Dtest=ProductServiceTest,CustomerServiceTest,ProductControllerTest,CustomerControllerTest test` -> OK
- `mvn test` -> OK (14 tests, 0 fallos)

## Pendientes

- Integracion en `trunk` via PR y merge.
- Validacion manual en RavenDB Studio con instancia local si se requiere evidencia visual.
