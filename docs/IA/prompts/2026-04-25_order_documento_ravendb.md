# Prompt - Documento Order RavenDB

Fecha: 2026-04-25

Resumen fiel del prompt:

> Proyecto academico de CBD sobre RavenDB. Quiero codigo sencillo, defendible y facil de explicar oralmente. No metas capas innecesarias ni arquitectura compleja. No uses autenticacion. No uses JPA ni bases de datos relacionales. Usa Java 17 + Spring Boot + RavenDB Java Client + Thymeleaf/MVC simple. Mi RavenDB local esta en http://127.0.0.1:8085 y la base es RavenShop. Quiero cambios pequenos, alineados con trazabilidad real y pensados para un commit promedio. Devuelveme primero un plan breve de archivos a tocar y luego el codigo. No inventes funcionalidades fuera del alcance del proyecto.
>
> Implementa solo la clase Order como documento RavenDB.
> Campos: id, customerId, customerSnapshot, orderedAt, status, shippingAddress, lineItems, total, statusHistory.
> Restricciones: no usar anotaciones JPA, no crear controlador todavia, no crear vistas todavia, clase simple en package model, usa tipos claros y faciles de defender.
> Done when: Order compila y refleja el diseno documental aprobado.

Objetivo:

- Revisar y ajustar `Order` como documento RavenDB simple.
- Mantener el alcance limitado al modelo y la trazabilidad.
- Verificar compilacion.
