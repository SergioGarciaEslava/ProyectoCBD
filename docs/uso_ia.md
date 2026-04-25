# Uso de IA

## WI-007 - Modelado documental de Order

Se utilizo IA para apoyar el diseno e implementacion del documento `Order` en RavenDB. La salida fue revisada y ajustada para mantener una solucion academica sencilla: pedido como agregado documental, lineas embebidas, snapshot del cliente, historial de estados y calculo de totales en servidor.

Las decisiones finales fueron mantener el modelo sin JPA, sin autenticacion y sin formularios de creacion todavia. La validacion minima impide pedidos vacios y cantidades no positivas.
