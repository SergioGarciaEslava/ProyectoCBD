# Sesion - WI-008 creacion de pedidos

## Objetivo

Implementar una creacion de pedidos minima y defendible para demo academica, usando documentos RavenDB existentes de clientes y productos.

## Cambios realizados

- Se anadieron DTOs de formulario para crear pedidos.
- Se amplio `OrderRepository` con persistencia de pedidos.
- Se implemento `OrderService.createOrder(...)` para cargar cliente/productos, crear snapshots y guardar el documento `Order`.
- Se anadio pantalla Thymeleaf `orders/form.html`.
- Se conectaron rutas MVC `GET /orders/new` y `POST /orders`.
- Se enlazo la portada hacia la creacion de pedidos.
- Se documento RavenDB con Docker en README.
- Se registro que el total se calcula siempre en servidor.

## Decisiones

- El formulario usa tres lineas fijas para mantener la pantalla simple y sin JavaScript.
- Las lineas sin producto se ignoran; si no queda ninguna, el agregado `Order` rechaza el pedido vacio.
- El servidor copia snapshot de cliente y datos de producto en el pedido para demostrar el enfoque documental.
- No se modifico el seed porque ya existen clientes y productos de ejemplo suficientes para probar la pantalla.

## Verificacion

- `mvn -q -DskipTests compile`
- `mvn -q -Dtest=OrderServiceTest test`
- `mvn -q -Dtest=OrderControllerTest test`
- `mvn -q -Dtest=HomeControllerTest test`
- `mvn test` paso 58 tests.
