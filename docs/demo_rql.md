# Consultas RQL de ejemplo para RavenDB Studio

Este documento recopila consultas RQL representativas para mostrar el uso de RavenDB Studio sobre las colecciones principales del proyecto `RavenShop`.

## 1. Pedidos pendientes

Consulta de pedidos cuyo estado actual es `Pending`, ordenados por fecha descendente.

```rql
from Orders
where status = "Pending"
order by orderedAt desc
```

Campos implicados:

- `status`
- `orderedAt`

## 2. Pedidos por total minimo y ciudad

Consulta de pedidos con un total igual o superior a una cantidad minima y pertenecientes a clientes de una ciudad concreta, utilizando el subdocumento embebido `customerSnapshot`.

```rql
from Orders
where total >= 100
  and customerSnapshot.city = "Madrid"
order by total desc
```

Campos implicados:

- `total`
- `customerSnapshot.city`

## 3. Productos por categoria o etiqueta

Consulta de productos filtrados por categoria o por etiqueta, ordenados por precio descendente.

```rql
from Products
where category = "Bebidas"
   or tags = "cafe"
order by price desc
```

Campos implicados:

- `category`
- `tags`
- `price`

## Uso en RavenDB Studio

1. Abrir la base de datos `RavenShop` en RavenDB Studio.
2. Ir a la vista de consultas.
3. Pegar la consulta RQL deseada.
4. Ejecutarla y revisar los resultados devueltos.
5. Consultar el indice utilizado si se quiere observar el comportamiento de los auto-indexes.
