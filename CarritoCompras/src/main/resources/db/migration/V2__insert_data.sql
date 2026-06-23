INSERT INTO USUARIO_C (nombre, gmail, contraseña) VALUES ('Luchi', 'luchi@gmail.com', 'pass123');
INSERT INTO USUARIO_C (nombre, gmail, contraseña) VALUES ('Ana Garcia', 'ana.garcia@gmail.com', 'segura456');

INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Tocadiscos Portátil', 89000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Audífonos Over-Ear', 120000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Limpiador de Vinilos', 25000);

INSERT INTO DISCO (nombre, artista, precio) VALUES ('Thriller', 'Michael Jackson', 40000);
INSERT INTO DISCO (nombre, artista, precio) VALUES ('The Dark Side of the Moon', 'Pink Floyd', 30000);
INSERT INTO DISCO (nombre, artista, precio) VALUES ('Back in Black', 'AC/DC', 32000);

INSERT INTO CARRITO (usuario_id, descuento) VALUES (1, 0);
INSERT INTO CARRITO (usuario_id, descuento) VALUES (2, 5.0);

INSERT INTO CARRITO_DISCOS (carrito_id, disco_id) VALUES (1, 1);
INSERT INTO CARRITO_DISCOS (carrito_id, disco_id) VALUES (1, 2);
INSERT INTO CARRITO_DISCOS (carrito_id, disco_id) VALUES (2, 3);

INSERT INTO CARRITO_PRODUCTOS (carrito_id, producto_id) VALUES (1, 1);
INSERT INTO CARRITO_PRODUCTOS (carrito_id, producto_id) VALUES (2, 2);
