# Plan de Implementación: Sprint Final de Demo RavenShop

## Contexto del Proyecto
**RavenShop** es un proyecto académico desarrollado en Java 21 y Spring Boot 3.5.13 cuyo objetivo principal es demostrar el modelado y manipulación de datos en una base de datos documental (**RavenDB**). El frontend (Thymeleaf MVC sin dependencias pesadas de JS) es puramente una herramienta para interactuar con la base de datos durante la defensa del proyecto.

La entrega es inminente (mañana), por lo que el objetivo de este sprint es realizar mejoras de alto impacto visual y didáctico con el menor riesgo posible sobre la lógica de negocio ya establecida.

## Objetivos del Sprint

Se abordarán las siguientes *issues* pendientes, combinadas con mejoras didácticas para la defensa. El orden de implementación es **WI-019 → WI-020 → (opcional) WI-018**, priorizando lo que más puntúa en la defensa.

### 1. WI-019: Mejorar dashboard inicial y navegación de demo *(prioridad alta)*
*   **Problema:** El `index.html` actual es funcional pero no transmite el carácter académico ni guía al tribunal por la demo.
*   **Acción:**
    *   Rediseñar la página principal como *dashboard* de entrada: tarjetas de acceso rápido a Productos, Clientes y Crear pedido (lo que ya hay, mejor presentado).
    *   Añadir un enlace visible a **Swagger UI** (`/swagger-ui.html`) y a **`/health-db`**.
    *   Añadir una breve descripción del propósito académico (uso de RavenDB como base documental) y mencionar los conceptos que se demostrarán: *documentos*, *embebidos*, *RQL*, *auto-indexes*.
*   **Riesgo:** Bajo. Solo HTML + CSS sobre `index.html` y `app.css`.

### 2. WI-020 + Mejoras didácticas (RQL y Auto-Index) *(prioridad alta)*
Cubre parcialmente **WI-011 (RQL)** y **WI-012 (auto-index)**. Se compone de cuatro sub-tareas:

#### 2.1 Pulido visual del detalle de pedido
*   Diferenciar visualmente el documento principal de los embebidos (`customerSnapshot`, `lineItems`, `statusHistory`).
*   Aplicar *badges* de color para los estados (`Pending`, etc.).
*   Solo HTML + CSS sobre `orders/detail.html` y `app.css`.

#### 2.2 Búsqueda de productos por nombre (genera el auto-index para la defensa)
*   Añadir un campo de búsqueda simple en `/products` (formulario `GET` con un único parámetro `q`).
*   **RQL exacta a ejecutar** (decidida para tener un nombre de auto-index limpio en Studio):
    ```rql
    from Products where startsWith(Name, $q)
    ```
    Esto provoca que RavenDB genere `Auto/Products/ByName`, que es trivial de mostrar y explicar en Studio.
*   Implementación Java: nuevo método en `ProductRepository`/`RavenProductRepository` + `ProductService` + parámetro opcional en `ProductController`. El listado existente sigue funcionando si `q` viene vacío.
*   **No** usar `search(Name, $q)` para evitar generar un full-text index y tener que justificar analizadores.

#### 2.3 Verificación / refuerzo del seed
*   Antes de la demo, comprobar que `SeedRunner` inserta varios productos cuyo nombre comparta prefijo (mínimo 3 con el mismo prefijo) para que la búsqueda devuelva más de un resultado y luzca el auto-index.
*   Si el seed actual no lo cubre, añadir 2-3 productos de ejemplo. Mantener idempotencia.

#### 2.4 Paneles RQL didácticos
*   Bajo el listado de productos y bajo el detalle del pedido, añadir un panel informativo (`<aside class="rql-panel">`) que muestre la RQL equivalente que se ha ejecutado por debajo. Texto estático en la plantilla, sin lógica.
*   Esto cubre la parte visible de **WI-011** sin necesidad de instrumentación.

### 3. WI-018: Layout visual común con Thymeleaf *(APLAZADO — no se implementa en este sprint)*
*   **Por qué se aplaza:** Es un refactor sin valor visible para el tribunal y, tras el commit `b35608d` que reescribió las 7 plantillas, hacerlo ahora introduce riesgo de regresión visual a horas de la entrega.
*   **Estado:** Issue `#27` queda abierta como mejora futura. Se retomará después de la defensa, cuando la base sea estable y el coste de equivocarse sea bajo.
*   **Si sobra tiempo tras WI-019 + WI-020:** se puede hacer al final, dejando el commit aislado para revertirlo fácilmente si algo se rompe.

## Reglas de Implementación
*   **No romper:** `mvn test` debe seguir pasando tras cada sub-tarea. La búsqueda de productos toca capa Java (repositorio/servicio/controlador), así que es la única zona con riesgo real de regresión.
*   **Cero JS complejo:** Frontend estrictamente SSR de Spring MVC + Thymeleaf y CSS plano (`app.css`).
*   **Sin nuevas dependencias:** No añadir librerías, interceptores globales, autenticación, ni nada que requiera tocar `pom.xml` a horas de la entrega.
*   **Trazabilidad IA:** Cada bloque de cambios debe quedar registrado en `docs/IA/PROMPTS_LOG.md` y, si aplica, en `docs/IA/DECISIONES_IA.md`.

## Checklist previo a la defensa (no es código, es operativa)
*   [ ] Borrar auto-indexes existentes en RavenDB Studio para que se generen en directo durante la demo.
*   [ ] Tener el seed cargado con productos suficientes para que la búsqueda devuelva varios resultados.
*   [ ] Verificar que `Auto/Products/ByName` aparece en Studio tras la primera búsqueda.
*   [ ] Tener Swagger UI accesible y al menos un endpoint probado en vivo.

## Ideas fuera de alcance (mencionadas pero no se hacen ahora)
*   **Listado `/orders` (WI-009):** Sería un segundo auto-index para enseñar (`from Orders where Status = 'Pending'`). Aporta valor pero ~1h de trabajo extra y queda fuera del sprint demo. Se valora solo si todo lo anterior está cerrado y queda margen.
*   **Bloque "Recorrido sugerido" en el dashboard:** Tras implementar WI-019 se detectó que el dashboard transmite *qué* es la app pero no *cómo* recorrerla. Añadir una sección con 4 pasos numerados orientaría al tribunal sin tener que decirlo de viva voz:
    1.  Ver productos → catálogo y búsqueda (auto-index)
    2.  Crear cliente → datos para el snapshot
    3.  Crear pedido → documento con embebidos
    4.  Abrir RavenDB Studio → ver el documento real y el auto-index
    Coste estimado: ~5-10 min (HTML + un poco de CSS). Se aplaza porque WI-020 ya cubre la parte donde "se ve" cómo funciona la app de verdad, y el guion de defensa puede suplir esta orientación verbalmente.
*   **Refactor a fragmentos Thymeleaf (WI-018):** Aplazado por riesgo/beneficio. Ver issue #27.
