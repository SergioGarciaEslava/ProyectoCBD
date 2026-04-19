# Sesion 2026-04-19 - Ejecucion De WI-003

## Objetivo

Implementar la carga inicial de datos semilla para RavenDB con productos, clientes y pedidos, evitando duplicados y dejando evidencia verificable para la defensa.

## Acciones realizadas

- Revisado alcance de `issues/03_preparar_datos_semilla.md`.
- Aplicado TDD:
  - Test RED inicial en `RavenDbSeedRunnerTest` (fallo por clase inexistente).
  - Implementación mínima del seeder en `RavenDbSeedRunner`.
  - Test GREEN para carga inicial e idempotencia.
- Implementado `RavenDbSeedRunner` con activación condicional por propiedad.
- Añadido marcador `seed-data/ravenshop-wi003` para impedir duplicados en reinicios.
- Cargados documentos de ejemplo:
  - Products (`products/1-A` ... `products/4-A`)
  - Customers (`customers/1-A` ... `customers/3-A`)
  - Orders (`orders/1-A` ... `orders/4-A`) con `lines`, `total` y `statusHistory`.
- Actualizado `application.yml` con `ravenshop.seed.enabled: false` por defecto.
- Actualizado `README.md` con instrucciones de activación del seed.
- Actualizada la issue WI-003 con checklist y evidencia.

## Archivos creados o modificados

- `src/main/java/com/gr21/ravenshop/seed/RavenDbSeedRunner.java` (nuevo)
- `src/test/java/com/gr21/ravenshop/seed/RavenDbSeedRunnerTest.java` (nuevo)
- `src/main/resources/application.yml`
- `README.md`
- `issues/03_preparar_datos_semilla.md`
- `docs/IA/prompts/2026-04-19_wi-003_datos_semilla.md` (nuevo)
- `docs/IA/sesiones/2026-04-19_wi-003_datos_semilla.md` (nuevo)
- `docs/IA/PROMPTS_LOG.md`
- `docs/IA/DECISIONES_IA.md`

## Validaciones ejecutadas

- `mvn -q -Dtest=RavenDbSeedRunnerTest test` -> OK
- `mvn test` -> OK (suite completa)

## Pendientes

- Verificación visual en RavenDB Studio con instancia local levantada.
- Push de commit a GitHub remoto.
