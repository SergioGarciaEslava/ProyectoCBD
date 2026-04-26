# Prompt - WI-011 preparar consultas RQL para la defensa

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Preparar y documentar varias consultas RQL equivalentes para la defensa en RavenDB Studio.

Quiero:

- una consulta de pedidos pendientes
- una consulta de pedidos por total minimo y ciudad
- una consulta de productos por categoria o etiqueta
- dejarlo documentado en `docs/demo_rql.md`
- verificar las consultas en la base local si RavenDB esta disponible

Restricciones:

- mantener el alcance en documentacion de demo
- no romper funcionalidad existente
- dejar instrucciones faciles de ejecutar en la exposicion
- registrar la trazabilidad de IA si se toca documentacion del proyecto

## Verificacion documentada

- Verificacion directa contra RavenDB local en `http://127.0.0.1:8085/databases/RavenShop/queries`
- `mvn test`
