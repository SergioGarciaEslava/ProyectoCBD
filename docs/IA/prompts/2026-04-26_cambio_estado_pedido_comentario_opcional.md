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

Amplia el cambio de estado de pedido para aceptar un comentario opcional.
Quiero:
- un campo de comentario en el formulario o accion desde la vista de detalle
- pasar ese comentario al servicio
- guardar el comentario dentro de la nueva entrada de statusHistory

Restricciones:
- el comentario debe ser opcional
- si viene vacio, no debe romper nada
- implementacion simple y coherente con la vista actual
- no introducir frontend complejo
