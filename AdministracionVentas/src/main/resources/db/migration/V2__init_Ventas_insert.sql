INSERT INTO PRODUCTO (nombre, precio) VALUES ('Thriller - Michael Jackson', 40000);
INSERT INTO PRODUCTO (nombre, precio) VALUES ('The Dark Side of the Moon - Pink Floyd', 30000);
INSERT INTO PRODUCTO (nombre, precio) VALUES ('Tocadiscos Portátil', 89000);
INSERT INTO PRODUCTO (nombre, precio) VALUES ('Audífonos Over-Ear', 120000);

INSERT INTO VENTAS (fecha_venta, usuario_id, puntos_usados, puntos_ganados, descuento)
VALUES ('2026-06-15', 1, 100, 50, 10);

INSERT INTO VENTAS (fecha_venta, usuario_id, puntos_usados, puntos_ganados, descuento)
VALUES ('2026-06-20', 2, 0, 80, 0);

INSERT INTO VENTA_PRODUCTOS (venta_id, producto_id) VALUES (1, 1);
INSERT INTO VENTA_PRODUCTOS (venta_id, producto_id) VALUES (1, 3);
INSERT INTO VENTA_PRODUCTOS (venta_id, producto_id) VALUES (2, 2);
INSERT INTO VENTA_PRODUCTOS (venta_id, producto_id) VALUES (2, 4);
