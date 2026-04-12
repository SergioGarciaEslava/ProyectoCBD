# Contrato OpenAPI De RavenShop

Este directorio contiene el contrato API-first de RavenShop:

- `ravenshop.openapi.yaml`

## Alcance del contrato

El contrato define una base minima para:

- `GET /health`
- recursos de `products`
- recursos de `customers`
- recursos de `orders`

El objetivo es alinear diseño y pruebas de backend. Este contrato no implica que todos los endpoints esten implementados en esta fase.

## Convenciones definidas

- prefijo de dominio API: `/api/v1`
- formato JSON para requests/responses
- errores con `application/problem+json` usando esquema `Problem`
- endpoints documentados con `operationId` para facilitar trazabilidad y pruebas

## Uso en desarrollo y pruebas

1. Diseñar controladores y servicios nuevos contra el contrato, no al reves.
2. Reutilizar `operationId` y esquemas para nombrar casos de prueba backend.
3. Verificar ejemplos de payload del contrato antes de implementar validaciones.
4. Mantener compatibilidad hacia atras: cualquier cambio rompe contrato debe discutirse en issue.

## Revision rapida del contrato

- abrir `ravenshop.openapi.yaml` en Swagger Editor (online o local)
- comprobar que no hay errores de validacion
- revisar que rutas/modelos siguen el alcance academico del proyecto
