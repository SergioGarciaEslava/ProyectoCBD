# Prompt - Ampliar seeders masivos con bloque Madrid >= 100

## Prompt recibido

Ampliar todavia mas los seeders, meter en torno a unos mil seeders mas, respetar la estructura existente sin romper funcionalidades y asegurar que ciertos seeders den resultados para la query:

```rql
from Orders
where total >= 100
  and customerSnapshot.city = "Madrid"
order by total desc
```

## Objetivo

Escalar el seed manteniendo el mismo enfoque del runner actual y dejar un conjunto de pedidos util para demos de RQL orientadas a Madrid y totales altos.
