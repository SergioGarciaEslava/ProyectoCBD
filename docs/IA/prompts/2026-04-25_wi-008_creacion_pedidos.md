# Prompt - WI-008 creacion de pedidos

## Fecha

2026-04-25

## Herramienta

Codex

## Prompt o resumen fiel

Implementar la issue WI-008 en un proyecto academico RavenDB + Spring Boot + Thymeleaf.

El usuario pidio hacer cada parte con commits separados, sin push. Tambien pidio crear seed si fuese necesario y documentar en README como usar RavenDB mediante Docker. El alcance debia mantenerse simple y defendible: Java 21, Spring Boot, RavenDB Java Client, Thymeleaf/MVC, sin autenticacion, sin JPA y sin arquitectura compleja.

Objetivo funcional:

- Crear pedidos desde una pantalla MVC sencilla.
- Usar clientes y productos ya existentes.
- Guardar el pedido como documento RavenDB.
- Mantener el calculo de `lineTotal` y `total` en servidor.
- Mantener el estado inicial `Pending` y las validaciones minimas ya definidas en el agregado `Order`.

## Restricciones aplicadas

- No se anadieron dependencias.
- No se implementaron pagos, autenticacion ni flujo avanzado.
- No se recalcularon importes en frontend.
- No se hizo push.
