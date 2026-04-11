# Backlog GitHub Issues · RavenShop (Java + Spring Boot + RavenDB)

Este documento está preparado para copiar y pegar cada bloque como una issue en GitHub.

## Recomendación de etiquetas
- `type:setup`
- `type:backend`
- `type:frontend`
- `type:data`
- `type:docs`
- `type:demo`
- `priority:high`
- `priority:medium`
- `priority:low`
- `status:blocked`
- `good first issue`

## Recomendación de milestones
- `M1 - Base técnica`
- `M2 - CRUD base`
- `M3 - Pedidos`
- `M4 - Demo RavenDB`
- `M5 - Entrega final`

---

## ISSUE 1 — Crear proyecto base Spring Boot
**Título**
Crear proyecto base Spring Boot con estructura inicial de RavenShop

**Labels**
`type:setup`, `priority:high`

**Milestone**
`M1 - Base técnica`

**Descripción**
Crear el proyecto base de RavenShop con Java y Spring Boot, dejando preparada una estructura limpia y fácil de defender.

**Objetivo**
Tener una aplicación arrancable sobre la que poder construir el resto del trabajo.

**Tareas**
- Crear proyecto con Spring Boot
- Añadir dependencias mínimas necesarias
- Definir estructura de paquetes
- Configurar puerto y nombre de la aplicación
- Crear endpoint simple de prueba
- Verificar que la app arranca sin errores

**Criterios de aceptación**
- La aplicación arranca correctamente
- Existe una estructura base clara (`config`, `controller`, `service`, `repository`, `model`)
- Existe al menos un endpoint o vista de prueba
- El proyecto compila sin errores

**Dependencias**
Ninguna

**Estimación**
3 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Captura de la app arrancando
- Primer commit del proyecto

---

## ISSUE 2 — Configurar conexión con RavenDB
**Título**
Configurar conexión con RavenDB mediante RavenDB Java Client

**Labels**
`type:backend`, `type:data`, `priority:high`

**Milestone**
`M1 - Base técnica`

**Descripción**
Configurar `DocumentStore` y dejar preparada la conexión con una base de datos RavenDB local para trabajar desde Spring Boot.

**Objetivo**
Poder abrir sesiones contra RavenDB desde la aplicación.

**Tareas**
- Añadir dependencia del cliente Java de RavenDB
- Crear clase de configuración para `DocumentStore`
- Parametrizar URL y nombre de base de datos
- Comprobar conexión real
- Crear endpoint o servicio de salud para base de datos

**Criterios de aceptación**
- La aplicación se conecta a RavenDB
- Se puede abrir una sesión sin errores
- Existe una comprobación simple de conectividad
- La configuración queda centralizada y bien nombrada

**Dependencias**
Issue 1

**Estimación**
4 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Captura de RavenDB Studio con la base creada
- Código de configuración subido al repo

---

## ISSUE 3 — Preparar datos semilla
**Título**
Crear carga inicial de datos semilla para Products, Customers y Orders

**Labels**
`type:data`, `priority:high`

**Milestone**
`M1 - Base técnica`

**Descripción**
Preparar un mecanismo sencillo para insertar datos de ejemplo realistas en RavenDB y facilitar la demo y las pruebas.

**Objetivo**
Tener datos iniciales suficientes para probar consultas y pantallas.

**Tareas**
- Definir conjunto mínimo de productos
- Definir conjunto mínimo de clientes
- Definir conjunto mínimo de pedidos
- Implementar clase o runner de seed
- Evitar duplicados al relanzar la carga

**Criterios de aceptación**
- La base queda poblada con datos de ejemplo
- Existen al menos productos, clientes y pedidos
- Los pedidos contienen líneas, total e historial de estados
- Los datos pueden visualizarse en RavenDB Studio

**Dependencias**
Issue 2

**Estimación**
4 h

**Clockify tag**
`datos`

**Evidencia mínima**
- Capturas de documentos en RavenDB Studio
- Commit con el seed

---

## ISSUE 4 — Modelar documentos Products y Customers
**Título**
Implementar modelos de dominio y acceso a datos para Products y Customers

**Labels**
`type:backend`, `priority:high`

**Milestone**
`M2 - CRUD base`

**Descripción**
Crear los modelos Java y la capa de acceso necesaria para trabajar con productos y clientes en RavenDB.

**Objetivo**
Dejar preparada la base del CRUD de productos y clientes.

**Tareas**
- Crear clase `Product`
- Crear clase `Customer`
- Crear clases auxiliares necesarias (`Address`, etc.)
- Implementar repositorio o servicio de acceso a RavenDB
- Probar operaciones básicas de guardar y consultar

**Criterios de aceptación**
- Se pueden persistir productos y clientes
- Los modelos representan correctamente la estructura definida en el diseño
- El código está organizado y es fácil de explicar

**Dependencias**
Issue 2

**Estimación**
4 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Código de modelos y acceso a datos
- Captura de documentos persistidos

---

## ISSUE 5 — Implementar CRUD de productos
**Título**
Implementar CRUD de productos con Spring MVC y Thymeleaf

**Labels**
`type:backend`, `type:frontend`, `priority:high`

**Milestone**
`M2 - CRUD base`

**Descripción**
Desarrollar las vistas y endpoints necesarios para listar, crear, editar y borrar lógicamente productos.

**Objetivo**
Disponer del catálogo básico de productos para poder crear pedidos.

**Tareas**
- Crear controlador de productos
- Crear servicio de productos
- Crear vistas Thymeleaf para listado y formulario
- Validar campos obligatorios
- Implementar borrado lógico o alternativa sencilla para la demo

**Criterios de aceptación**
- Se pueden listar productos
- Se pueden crear productos válidos
- Se pueden editar productos existentes
- La operación de borrado no rompe la demo

**Dependencias**
Issue 4

**Estimación**
6 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Capturas del CRUD funcionando
- Datos visibles en RavenDB Studio

---

## ISSUE 6 — Implementar CRUD de clientes
**Título**
Implementar CRUD de clientes con Spring MVC y Thymeleaf

**Labels**
`type:backend`, `type:frontend`, `priority:high`

**Milestone**
`M2 - CRUD base`

**Descripción**
Desarrollar las vistas y endpoints necesarios para listar, crear, editar y consultar clientes.

**Objetivo**
Tener clientes disponibles para asociarlos a los pedidos.

**Tareas**
- Crear controlador de clientes
- Crear servicio de clientes
- Crear vistas Thymeleaf para listado y formulario
- Validar email y dirección mínima
- Comprobar persistencia correcta en RavenDB

**Criterios de aceptación**
- Se pueden listar clientes
- Se pueden crear clientes válidos
- Se pueden editar clientes existentes
- Los datos se almacenan correctamente

**Dependencias**
Issue 4

**Estimación**
6 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Capturas del CRUD funcionando
- Ejemplos de clientes en base de datos

---

## ISSUE 7 — Modelar documento Order y reglas de negocio
**Título**
Implementar modelo Order con lineItems, customerSnapshot y statusHistory

**Labels**
`type:backend`, `type:data`, `priority:high`

**Milestone**
`M3 - Pedidos`

**Descripción**
Crear el modelo de pedido como agregado principal del sistema, incluyendo líneas, total, dirección de envío, estado y trazabilidad.

**Objetivo**
Reflejar correctamente el enfoque documental de RavenDB.

**Tareas**
- Crear clase `Order`
- Crear clase `OrderLineItem`
- Crear clase `StatusHistoryEntry`
- Crear clase `CustomerSnapshot`
- Implementar lógica para calcular total en servidor
- Aplicar reglas mínimas de validación

**Criterios de aceptación**
- El modelo representa el diseño aprobado
- El total se calcula en el servidor
- Un pedido no puede crearse vacío
- El pedido nace en estado `Pending`

**Dependencias**
Issue 2

**Estimación**
5 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Código del modelo
- Ejemplo de documento `Order` en RavenDB Studio

---

## ISSUE 8 — Implementar creación de pedidos
**Título**
Implementar caso de uso Crear pedido

**Labels**
`type:backend`, `type:frontend`, `priority:high`

**Milestone**
`M3 - Pedidos`

**Descripción**
Desarrollar el formulario y la lógica de negocio para crear pedidos con múltiples líneas usando productos y clientes existentes.

**Objetivo**
Cubrir el caso de uso principal del trabajo.

**Tareas**
- Crear formulario de pedido
- Permitir seleccionar cliente
- Permitir añadir varias líneas
- Duplicar nombre, categoría y precio en la línea
- Calcular total en servidor
- Generar `statusHistory` inicial
- Persistir pedido en RavenDB

**Criterios de aceptación**
- Se puede crear un pedido con varias líneas
- El total es correcto
- El pedido queda guardado correctamente
- El documento es legible y demostrable en Studio

**Dependencias**
Issues 5, 6 y 7

**Estimación**
8 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Capturas del formulario y detalle del pedido
- Documento creado en RavenDB Studio

---

## ISSUE 9 — Implementar detalle y listado filtrable de pedidos
**Título**
Implementar vistas de detalle y listado filtrable de pedidos

**Labels**
`type:backend`, `type:frontend`, `priority:high`

**Milestone**
`M3 - Pedidos`

**Descripción**
Crear las pantallas para consultar pedidos y filtrarlos por estado, cliente o total mínimo.

**Objetivo**
Demostrar consultas típicas sobre el agregado principal.

**Tareas**
- Crear listado de pedidos
- Añadir filtros por estado, cliente y total mínimo
- Ordenar por fecha descendente
- Crear vista de detalle de pedido
- Mostrar líneas e historial de estados

**Criterios de aceptación**
- El listado se carga correctamente
- Los filtros devuelven resultados correctos
- El detalle muestra toda la información relevante
- La consulta es coherente con la demo en RavenDB

**Dependencias**
Issue 8

**Estimación**
7 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Capturas del listado y el detalle
- Prueba manual de varios filtros

---

## ISSUE 10 — Implementar cambio de estado del pedido
**Título**
Implementar actualización de estado e historial del pedido

**Labels**
`type:backend`, `priority:high`

**Milestone**
`M3 - Pedidos`

**Descripción**
Permitir cambiar el estado de un pedido y registrar el cambio dentro de `statusHistory`.

**Objetivo**
Añadir trazabilidad y una operación de negocio clara sobre el pedido.

**Tareas**
- Crear acción de cambio de estado
- Actualizar campo `status`
- Añadir entrada en `statusHistory`
- Permitir comentario opcional
- Mostrar el historial actualizado en la vista

**Criterios de aceptación**
- El estado se actualiza correctamente
- El historial conserva los cambios anteriores
- La vista refleja el nuevo estado
- La información queda persistida en RavenDB

**Dependencias**
Issue 8

**Estimación**
4 h

**Clockify tag**
`implementacion`

**Evidencia mínima**
- Captura antes y después del cambio de estado
- Documento actualizado en Studio

---

## ISSUE 11 — Preparar demo de RQL
**Título**
Preparar consultas RQL equivalentes para la defensa

**Labels**
`type:demo`, `type:docs`, `priority:medium`

**Milestone**
`M4 - Demo RavenDB`

**Descripción**
Definir y documentar varias consultas RQL que permitan enseñar el funcionamiento de RavenDB Studio durante la exposición.

**Objetivo**
Tener una demo técnica clara y rápida de ejecutar.

**Tareas**
- Preparar consulta de pedidos pendientes
- Preparar consulta de pedidos por total mínimo y ciudad
- Preparar consulta de productos por categoría o etiqueta
- Guardar las consultas en un documento del repo
- Verificarlas en RavenDB Studio

**Criterios de aceptación**
- Existen al menos 3 consultas RQL funcionales
- Las consultas están documentadas en el repositorio
- Se pueden ejecutar sin improvisación en la defensa

**Dependencias**
Issues 3, 5, 6, 8 y 9

**Estimación**
3 h

**Clockify tag**
`documentacion`

**Evidencia mínima**
- Archivo `docs/demo_rql.md`
- Capturas de ejecución en Studio

---

## ISSUE 12 — Demostrar auto-index en RavenDB Studio
**Título**
Preparar demostración de auto-index generado por RavenDB

**Labels**
`type:demo`, `type:data`, `priority:medium`

**Milestone**
`M4 - Demo RavenDB`

**Descripción**
Identificar una consulta dinámica que genere un auto-index y documentar cómo enseñarlo durante la presentación.

**Objetivo**
Mostrar una característica diferencial de RavenDB.

**Tareas**
- Ejecutar consulta dinámica apropiada
- Localizar el auto-index generado en Studio
- Preparar explicación breve de qué ha ocurrido
- Guardar capturas para la memoria y presentación

**Criterios de aceptación**
- Se puede enseñar un auto-index real
- Existe explicación breve y entendible
- Hay material gráfico listo para la memoria/presentación

**Dependencias**
Issue 11

**Estimación**
2 h

**Clockify tag**
`documentacion`

**Evidencia mínima**
- Captura del auto-index en Studio
- Nota explicativa para la defensa

---

## ISSUE 13 — Redactar README de instalación y uso
**Título**
Redactar README con instalación, ejecución y guía de uso

**Labels**
`type:docs`, `priority:high`

**Milestone**
`M5 - Entrega final`

**Descripción**
Documentar cómo arrancar la aplicación, configurar RavenDB y probar las funcionalidades principales.

**Objetivo**
Hacer reproducible el proyecto para profesores y compañeros.

**Tareas**
- Explicar requisitos previos
- Explicar cómo levantar RavenDB
- Explicar cómo arrancar la app Spring Boot
- Explicar cómo cargar datos semilla
- Explicar recorrido mínimo de uso
- Añadir sección de problemas comunes

**Criterios de aceptación**
- Un tercero puede arrancar el proyecto con el README
- La documentación es clara y suficiente
- Se incluyen pasos de seed y demo mínima

**Dependencias**
Issues 1 a 12

**Estimación**
3 h

**Clockify tag**
`documentacion`

**Evidencia mínima**
- README subido al repositorio

---

## ISSUE 14 — Registrar uso de IA en el repositorio
**Título**
Crear y mantener registro de uso de IA en `docs/uso_ia.md`

**Labels**
`type:docs`, `priority:high`

**Milestone**
`M5 - Entrega final`

**Descripción**
Crear un registro trazable del uso de ChatGPT, Codex u otras herramientas, indicando objetivo, prompt, resultado y revisión realizada por el equipo.

**Objetivo**
Facilitar el cumplimiento de los requisitos de autoría y defensa del trabajo.

**Tareas**
- Crear plantilla de registro
- Añadir entradas por cada uso relevante de IA
- Indicar qué parte se aprovechó realmente
- Indicar qué se revisó o corrigió manualmente
- Mantener el documento actualizado durante el desarrollo

**Criterios de aceptación**
- Existe archivo `docs/uso_ia.md`
- El registro es entendible y no está vacío
- Permite rastrear decisiones relevantes del proyecto

**Dependencias**
Ninguna

**Estimación**
2 h iniciales + mantenimiento

**Clockify tag**
`ia-apoyo`

**Evidencia mínima**
- Archivo en repositorio con varias entradas reales

---

## ISSUE 15 — Preparar material para memoria y presentación
**Título**
Preparar capturas, figuras y contenido base para memoria y presentación

**Labels**
`type:docs`, `type:demo`, `priority:medium`

**Milestone**
`M5 - Entrega final`

**Descripción**
Reunir el material visual y técnico necesario para redactar la memoria y construir la presentación final.

**Objetivo**
Evitar dejar la documentación para el último día.

**Tareas**
- Capturar vistas principales de la aplicación
- Capturar documentos en RavenDB Studio
- Capturar consultas RQL
- Capturar auto-index
- Guardar material en carpeta `docs/assets`
- Anotar pie y contexto de cada figura

**Criterios de aceptación**
- Existe una carpeta con material reutilizable
- El equipo dispone de imágenes para memoria y presentación
- No hace falta rehacer la demo solo para sacar capturas

**Dependencias**
Issues 8 a 12

**Estimación**
3 h

**Clockify tag**
`presentacion`

**Evidencia mínima**
- Carpeta `docs/assets`
- Capturas nombradas correctamente

---

## ISSUE 16 — Ensayo técnico de la defensa
**Título**
Realizar ensayo técnico de defensa y demo de 15 minutos

**Labels**
`type:demo`, `priority:medium`

**Milestone**
`M5 - Entrega final`

**Descripción**
Ensayar la secuencia de exposición, incluyendo explicación del modelo documental, demo en la app, consulta RQL y auto-index.

**Objetivo**
Asegurar que ambos miembros pueden defender el trabajo sin depender del guion.

**Tareas**
- Preparar orden de exposición
- Repartir bloques entre ambos
- Cronometrar demo y explicación
- Revisar posibles preguntas del profesor
- Detectar puntos débiles y corregirlos

**Criterios de aceptación**
- La exposición cabe en 15 minutos
- Ambos miembros entienden toda la demo
- El discurso técnico es claro y consistente

**Dependencias**
Issues 11 a 15

**Estimación**
2 h

**Clockify tag**
`presentacion`

**Evidencia mínima**
- Guion final de defensa
- Lista de preguntas probables

---

## Orden recomendado de creación en GitHub
1. Issue 1
2. Issue 2
3. Issue 3
4. Issue 4
5. Issue 5
6. Issue 6
7. Issue 7
8. Issue 8
9. Issue 9
10. Issue 10
11. Issue 11
12. Issue 12
13. Issue 13
14. Issue 14
15. Issue 15
16. Issue 16

## Reparto recomendado
- **Sergio**: Issues 1, 2, 3, 7, 8, 10, 12
- **Javier**: Issues 4, 5, 6, 9, 11, 13
- **Compartido**: Issues 14, 15, 16
