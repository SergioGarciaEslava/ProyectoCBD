## Prompt

Proyecto academico de CBD sobre RavenDB.
Quiero codigo sencillo, defendible y facil de explicar oralmente.
No metas capas innecesarias ni arquitectura compleja.
No uses autenticacion.
No uses JPA ni bases de datos relacionales.
Usa Java 17 + Spring Boot + RavenDB Java Client + Thymeleaf/MVC simple.
Mi RavenDB local esta en http://127.0.0.1:8085 y la base es RavenShop.
Quiero cambios pequenos, alineados con trazabilidad real y pensados para un commit promedio.
Devuelveme primero un plan breve de archivos a tocar y luego el codigo.
No inventes funcionalidades fuera del alcance del proyecto.

Ajusta la vista de detalle de pedido para que refleje correctamente el nuevo estado y el historial actualizado tras cambiar el estado.
Quiero:
- mostrar el status actual actualizado
- mostrar la nueva entrada en statusHistory
- mantener visibles las entradas previas
- redirigir de forma limpia al detalle del pedido tras el cambio

Restricciones:
- no rehacer la pantalla completa
- no introducir JavaScript innecesario
- solucion simple y clara
