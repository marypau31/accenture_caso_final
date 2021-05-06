INSERT INTO cliente(id, nombre, tipo_documento, numero_documento, direccion) VALUES (1, 'prueba', 'CC', '12345', 'carrera 11# 14-08');
INSERT INTO cliente(id, nombre, tipo_documento, numero_documento, direccion) VALUES (2, 'Mary Pau', 'CC', '1070621174', 'Calle 19 #20-05 Girardot, Cundinamarca');
INSERT INTO cliente(id, nombre, tipo_documento, numero_documento, direccion) VALUES (3, 'Harold', 'CC', '1070613632', 'Calle 32A #20-05 Girardot, Cundinamarca');

INSERT INTO  producto (id, nombre, precio) VALUES (1, 'salchichas', 10000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (2, 'jugo del valle', 5000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (3, 'leche deslactosada cajax12', 26000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (4, 'jabon liquido para lavadora', 18000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (5, 'aceite cocina 1000ml', 22000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (6, 'celular iphone 12', 4500000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (7, 'bafle mediano', 50000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (8, 'bicicleta para niño', 300000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (9, 'bicicleta todoterreno', 2500000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (10, 'audifonos simples', 15000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (11, 'audifonos con bluetooth', 10000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (12, 'zapatos', 30000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (13, 'cargador para celular', 60000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (14, 'maletas rodachines para viaje', 300000.0);
INSERT INTO  producto (id, nombre, precio) VALUES (15, 'xbox 360', 250000.0);

INSERT INTO pedido (id, cliente_id, estado, tiempo, subtotal, iva, domicilio, total) VALUES (1, 2, 'FACTURADO', now(), 68000, 12920, 3000, 83920);

INSERT INTO producto_por_pedido (id, pedido_id, producto_id, cantidad) VALUES (1, 1, 1, 2);
INSERT INTO producto_por_pedido (id, pedido_id, producto_id, cantidad) VALUES (2, 1, 3, 1);
INSERT INTO producto_por_pedido (id, pedido_id, producto_id, cantidad) VALUES (3, 1, 5, 1);

INSERT INTO pedido (id, cliente_id, estado, tiempo, subtotal, iva, domicilio, total) VALUES (2, 3, 'FACTURADO', '2021-05-04 12:12:12', 68000, 12920, 3000, 83920);

INSERT INTO producto_por_pedido (id, pedido_id, producto_id, cantidad) VALUES (4, 2, 1, 2);
INSERT INTO producto_por_pedido (id, pedido_id, producto_id, cantidad) VALUES (5, 2, 3, 1);
INSERT INTO producto_por_pedido (id, pedido_id, producto_id, cantidad) VALUES (6, 2, 5, 1);