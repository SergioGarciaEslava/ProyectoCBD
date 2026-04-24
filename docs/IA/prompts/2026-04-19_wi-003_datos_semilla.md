# Prompt De Sesion - WI-003 Datos Semilla

## Prompt recibido

Haz el WI-003 y sube el código a github con un commit.

## Interpretacion

Completar el work item WI-003 implementando un mecanismo de carga inicial de datos semilla para RavenDB (productos, clientes y pedidos), con control para evitar duplicados, pruebas automáticas y trazabilidad en documentación del repositorio.

## Alcance aplicado

- Implementar `RavenDbSeedRunner` para insertar datos de ejemplo.
- Incluir pedidos con líneas, total e historial de estados.
- Evitar recarga duplicada con un documento marcador de seed.
- Añadir pruebas unitarias del flujo de primera carga e idempotencia.
- Actualizar documentación operativa (`README`) y estado de issue (`issues/03...`).
- Registrar trazabilidad de uso de IA en `docs/IA/`.
