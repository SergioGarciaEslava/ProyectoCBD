# Prompt De Sesion - WI-002 Conexion RavenDB

## Prompt recibido

Ya he hecho pull request a main y merge. Ahora sigue tu, con la siguiente issue, recuerda hacerte pull request de todo antes de continuar.

## Interpretacion

Continuar con la siguiente issue (WI-002), crear y trabajar en su rama, completar implementacion y verificacion, y abrir PR antes de pasar a otra issue.

## Alcance aplicado

- Sincronizar `main` y crear rama `featuretask/WI-002-configurar-conexion-con-ravendb`.
- Configurar `DocumentStore` de RavenDB en Spring Boot con propiedades externas.
- Crear chequeo de conectividad RavenDB y endpoint `GET /health/ravendb`.
- Añadir prueba automatica del endpoint.
- Ejecutar verificaciones (`mvn test` y prueba runtime con `spring-boot:run` + `curl`).
- Actualizar trazabilidad IA y estado de la issue.
