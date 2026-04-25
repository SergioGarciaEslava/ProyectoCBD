# Prompt - Estado inicial Pending en Order

Fecha: 2026-04-25

Resumen fiel del prompt:

> Proyecto academico de CBD sobre RavenDB. Quiero codigo sencillo, defendible y facil de explicar oralmente. No metas capas innecesarias ni arquitectura compleja. No uses autenticacion. No uses JPA ni bases de datos relacionales. Usa Java 17 + Spring Boot + RavenDB Java Client + Thymeleaf/MVC simple. Mi RavenDB local esta en http://127.0.0.1:8085 y la base es RavenShop. Quiero cambios pequenos, alineados con trazabilidad real y pensados para un commit promedio. Devuelveme primero un plan breve de archivos a tocar y luego el codigo. No inventes funcionalidades fuera del alcance del proyecto.
>
> Implementa la clase `StatusHistoryEntry` y anade soporte en `Order` para el estado inicial. `StatusHistoryEntry` debe incluir `status`, `changedAt` y `comment`. Ademas, define una forma simple de que un pedido nazca en estado Pending y prepara `statusHistory` para guardar la primera entrada al crear el pedido.
>
> Restricciones: sin formularios todavia, sin controlador todavia, codigo facil de explicar, si usas constantes o enum para estados, que sea simple y defendible.

Objetivo:

- Completar `StatusHistoryEntry` con comentario.
- Preparar `Order` para crear pedidos nuevos en estado `Pending` con primera entrada de historial.
- Mantener el cambio acotado al modelo y pruebas.
