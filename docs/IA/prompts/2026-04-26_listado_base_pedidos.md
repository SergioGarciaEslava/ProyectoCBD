# Prompt - listado base de pedidos

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Proyecto academico de CBD sobre RavenDB.
Quiero codigo sencillo, defendible y facil de explicar oralmente.
No metas capas innecesarias ni arquitectura compleja.
No uses autenticacion.
No uses JPA ni bases de datos relacionales.
Usa Java 17 + Spring Boot + RavenDB Java Client + Thymeleaf/MVC simple.
Mi RavenDB local esta en `http://127.0.0.1:8085` y la base es `RavenShop`.
Quiero cambios pequenos, alineados con trazabilidad real y pensados para un commit promedio.
Devuelveme primero un plan breve de archivos a tocar y luego el codigo.
No inventes funcionalidades fuera del alcance del proyecto.

Implementar solo el listado base de pedidos.

Quiero:

- `OrderService` con metodo para listar pedidos desde RavenDB.
- `OrderController` con `GET /orders`.
- plantilla `templates/orders/list.html`.
- enlace desde `index` a `/orders`.

Mostrar en el listado:

- `id`
- `orderedAt`
- `status`
- `customerSnapshot.fullName`
- `total`

Restricciones:

- no implementar aun filtros
- no implementar aun detalle
- vista simple y clara
- si no hay pedidos, mostrar mensaje amigable

Done when:

- `/orders` carga correctamente
- la lista se obtiene desde RavenDB

## Verificacion documentada

- `mvn -q "-Dtest=OrderServiceTest,OrderControllerTest,HomeControllerTest" test`
- La comprobacion HTTP real contra `/orders` queda supeditada a tener RavenDB local levantado en `127.0.0.1:8085` con base `RavenShop`.
