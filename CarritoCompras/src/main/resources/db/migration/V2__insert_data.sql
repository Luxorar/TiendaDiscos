INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Tocadiscos Portátil', 89000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Audífonos Over-Ear', 120000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Limpiador de Vinilos', 25000);

INSERT INTO DISCO (id, nombre, artista, precio) VALUES (1, 'Thriller', 'Michael Jackson', 40000);
INSERT INTO DISCO (id, nombre, artista, precio) VALUES (4, 'The Dark Side of the Moon', 'Pink Floyd', 30000);

INSERT INTO CARRITO (user_id, descuento) VALUES (1, 0);
INSERT INTO CARRITO (user_id, descuento) VALUES (2, 5.0);

INSERT INTO CARRITO_DISCOS (carrito_id, disco_id) VALUES (1, 1);
INSERT INTO CARRITO_DISCOS (carrito_id, disco_id) VALUES (1, 4);
INSERT INTO CARRITO_DISCOS (carrito_id, disco_id) VALUES (2, 4);

INSERT INTO CARRITO_PRODUCTOS (carrito_id, producto_id) VALUES (1, 1);
INSERT INTO CARRITO_PRODUCTOS (carrito_id, producto_id) VALUES (2, 2);
