# Resumen De Issues RavenShop

## Estado

- Issues preparadas en Markdown: 17
- Issues creadas en GitHub: 17
- Repositorio remoto detectado: `git@github.com:SergioGarciaEslava/ProyectoCBD.git`
- Repositorio GitHub verificado: `SergioGarciaEslava/ProyectoCBD`

## Labels usadas para GitHub

- `work-item`
- `setup`
- `backend`
- `frontend`
- `database`
- `documentation`
- `demo`
- `high`
- `medium`
- `low`

## Issues creadas

- #1 [WI-001] Crear proyecto base Spring Boot con estructura inicial de RavenShop: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/1>
- #2 [WI-002] Configurar conexión con RavenDB mediante RavenDB Java Client: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/2>
- #3 [WI-003] Crear carga inicial de datos semilla para Products, Customers y Orders: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/3>
- #4 [WI-004] Implementar modelos de dominio y acceso a datos para Products y Customers: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/4>
- #5 [WI-005] Implementar CRUD de productos con Spring MVC y Thymeleaf: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/5>
- #6 [WI-006] Implementar CRUD de clientes con Spring MVC y Thymeleaf: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/6>
- #7 [WI-007] Implementar modelo Order con lineItems, customerSnapshot y statusHistory: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/7>
- #8 [WI-008] Implementar caso de uso Crear pedido: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/8>
- #9 [WI-009] Implementar vistas de detalle y listado filtrable de pedidos: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/9>
- #10 [WI-010] Implementar actualización de estado e historial del pedido: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/10>
- #11 [WI-011] Preparar consultas RQL equivalentes para la defensa: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/11>
- #12 [WI-012] Preparar demostración de auto-index generado por RavenDB: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/12>
- #13 [WI-013] Redactar README con instalación, ejecución y guía de uso: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/13>
- #14 [WI-014] Crear y mantener registro de uso de IA en `docs/uso_ia.md`: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/14>
- #15 [WI-015] Preparar capturas, figuras y contenido base para memoria y presentación: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/15>
- #16 [WI-016] Realizar ensayo técnico de defensa y demo de 15 minutos: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/16>
- #17 [WI-017] Definir contrato OpenAPI para enfoque API-first: <https://github.com/SergioGarciaEslava/ProyectoCBD/issues/17>

## Verificacion realizada

- `gh auth status`: autenticacion valida fuera del sandbox.
- `./issues/create_github_issues.sh`: creo issues `#1` a `#16`.
- `gh issue list --limit 30 --json number,title,url,labels --state open`: confirmo las 16 issues abiertas, sus labels y sus titulos `[WI-XXX]`.
- `gh issue view 1 --json number,title,body,labels,url`: confirmo que el cuerpo de la issue #1 sigue la plantilla Work Item.
- `gh issue view 17 --json number,title,body,labels,url`: confirmo que la issue OpenAPI existe como `#17`, con formato Work Item y labels `work-item`, `backend`, `documentation`, `setup`, `high`.
- `gh issue view 2 --json number,title,url,body`: confirmo que una issue backend declara dependencia de `Issues 1 y 17`.
