# WI-007 - Verificacion manual del documento Order en RavenDB

## Checklist manual

- Verificar que `Order` tiene `lineItems`, `customerSnapshot`, `statusHistory`, `status`, `total` y `orderedAt`.
- Comprobar que un pedido nuevo nace con `status = Pending`.
- Comprobar que cada linea tiene `quantity`, `unitPrice` y `lineTotal`.
- Verificar que `lineTotal = quantity * unitPrice`.
- Verificar que `total = suma de lineTotal`.
- Probar que no se acepta un pedido sin lineas.
- Probar que no se acepta una linea con `quantity <= 0`.

## Pedido de ejemplo

Se puede insertar como dato semilla o desde RavenDB Studio, sin crear todavia formulario de pedidos.

```json
{
  "customerId": "customers/1-A",
  "customerSnapshot": {
    "customerId": "customers/1-A",
    "fullName": "Ana Lopez",
    "email": "ana.lopez@example.com",
    "city": "Madrid"
  },
  "orderedAt": "2026-04-25T10:00:00Z",
  "status": "Pending",
  "shippingAddress": "Calle Mayor 1, Madrid",
  "lineItems": [
    {
      "productId": "products/1-A",
      "productName": "Cafe Colombia",
      "category": "Bebidas",
      "quantity": 2,
      "unitPrice": 10.50,
      "lineTotal": 21.00
    }
  ],
  "total": 21.00,
  "statusHistory": [
    {
      "status": "Pending",
      "changedAt": "2026-04-25T10:00:00Z",
      "comment": "Pedido creado"
    }
  ]
}
```

## Defensa oral

`Order` se modela como agregado documental. El pedido guarda lineas embebidas, snapshot historico del cliente e historial de estados para que el documento conserve el contexto de la compra aunque cambien despues los documentos de cliente o producto.

## Evidencia minima para PR o Clockify

```text
WI-007 - Modelado documental de pedidos en RavenDB

- Implementado documento Order con lineItems, customerSnapshot y statusHistory.
- Añadido estado inicial Pending.
- Añadido calculo de lineTotal y total en servidor.
- Añadidas reglas minimas: pedido no vacio y quantity > 0.
- Añadidas pruebas automaticas del modelo y servicio.
- Verificacion: mvn test en verde.
```

## Prueba automatica recomendada

La prueba mas defendible es de modelo:

```text
Order.createPending(lineItems) crea un pedido Pending, rechaza lineas vacias o quantity <= 0, y recalcula total.
```

No requiere RavenDB real y cubre directamente la regla de negocio del agregado.
