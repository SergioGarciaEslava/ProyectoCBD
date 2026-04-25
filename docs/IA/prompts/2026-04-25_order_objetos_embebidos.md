# Prompt - Objetos embebidos de Order

Fecha: 2026-04-25

Resumen fiel del prompt:

> Proyecto academico de CBD sobre RavenDB. Quiero codigo sencillo, defendible y facil de explicar oralmente. No metas capas innecesarias ni arquitectura compleja. No uses autenticacion. No uses JPA ni bases de datos relacionales. Usa Java 17 + Spring Boot + RavenDB Java Client + Thymeleaf/MVC simple. Mi RavenDB local esta en http://127.0.0.1:8085 y la base es RavenShop. Quiero cambios pequenos, alineados con trazabilidad real y pensados para un commit promedio. Devuelveme primero un plan breve de archivos a tocar y luego el codigo. No inventes funcionalidades fuera del alcance del proyecto.
>
> Implementa los objetos embebidos necesarios para el pedido: `OrderLineItem` y `CustomerSnapshot`.
>
> `OrderLineItem` debe incluir `productId`, `productName`, `category`, `unitPrice`, `quantity` y `lineTotal`.
>
> `CustomerSnapshot` debe incluir `fullName`, `email` y `city`.
>
> Restricciones: clases simples, sin logica compleja todavia, sin controladores ni vistas, orientado a mantener contexto historico del pedido.

Objetivo:

- Ajustar los objetos embebidos del documento `Order`.
- Mantener cambios pequenos y sin controladores ni vistas nuevas.
- Verificar compilacion y pruebas directas de pedidos.
