# Sesion - WI-018 layout visual comun Thymeleaf

## Objetivo

Crear una base visual comun para RavenShop usando Thymeleaf y CSS propio, con una apariencia minimalista y consistente.

## Cambios realizados

- Se creo `src/main/resources/static/css/app.css` con variables de color, layout, navegacion, botones, tablas, formularios, mensajes y responsive basico.
- Se aplico una cabecera comun con marca y navegacion principal.
- Se actualizaron las vistas de home, productos, clientes y pedidos para usar clases compartidas.
- Se mantuvo el frontend sin JavaScript y sin dependencias nuevas.

## Decisiones

- La paleta se uso como sistema de acentos sobre una base neutra para evitar una interfaz saturada.
- Se priorizaron tablas, formularios y navegacion legibles frente a composiciones decorativas.
- No se crearon fragmentos Thymeleaf todavia para mantener el cambio pequeno y facil de revisar.
- Se aplico el estilo tambien a pedidos, aunque la issue menciona home/productos/clientes, porque WI-008 ya deja esa pantalla visible desde la navegacion.

## Verificacion

- `mvn -q -Dtest=HomeControllerTest,ProductControllerTest,CustomerControllerTest,OrderControllerTest test`
- `mvn test` paso 58 tests.
- Arranque local en puerto `18080`.
- `curl http://127.0.0.1:18080/` encontro el enlace a `/css/app.css`.
- `curl -I http://127.0.0.1:18080/css/app.css` devolvio `200` con `Content-Type: text/css`.
