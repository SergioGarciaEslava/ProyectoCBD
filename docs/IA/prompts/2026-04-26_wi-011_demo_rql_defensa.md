# Prompt - WI-011 preparar consultas RQL para la defensa

## Fecha

2026-04-26

## Herramienta

Codex

## Prompt o resumen fiel

Preparar y documentar varias consultas RQL equivalentes para la defensa en RavenDB Studio. Despues, rehacer `docs/demo_rql.md` para dejarlo limpio y presentable, sin notas locales ni guion operativo interno.

Quiero:

- una consulta de pedidos pendientes
- una consulta de pedidos por total minimo y ciudad
- una consulta de productos por categoria o etiqueta
- dejarlo documentado en `docs/demo_rql.md`
- verificar las consultas en la base local si RavenDB esta disponible
- dejar finalmente el documento limpio para lectura del profesor

Restricciones:

- mantener el alcance en documentacion de demo
- no romper funcionalidad existente
- evitar referencias locales, resultados de una base concreta o notas internas de preparacion
- registrar la trazabilidad de IA si se toca documentacion del proyecto

## Verificacion documentada

- Verificacion previa directa contra RavenDB local en `http://127.0.0.1:8085/databases/RavenShop/queries`
- Revision final del contenido resultante en `docs/demo_rql.md`
