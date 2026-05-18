-- Productos
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Tocadiscos Portátil', 89000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Audífonos Over-Ear', 120000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Limpiador de Vinilos', 25000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Aguja de Repuesto', 45000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES ('Amplificador de Escritorio', 75000);

-- Discos
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('The Dark Side of the Moon', 'Pink Floyd', 35000);
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('Thriller', 'Michael Jackson', 30000);
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('Back in Black', 'AC/DC', 32000);
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('Rumours', 'Fleetwood Mac', 28000);
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('Kind of Blue', 'Miles Davis', 33000);

-- Sedes
INSERT INTO SEDES (nombre, direccion, numero_sede) VALUES ('Sede Santiago Centro', 'Av. Libertador Bernardo O Higgins 1234', '+56221234567');
INSERT INTO SEDES (nombre, direccion, numero_sede) VALUES ('Sede Providencia', 'Av. Providencia 567', '+56229876543');
INSERT INTO SEDES (nombre, direccion, numero_sede) VALUES ('Sede Ñuñoa', 'Av. Irarrázaval 890', '+56222345678');

-- Relacion Sede - Productos
INSERT INTO SEDE_PRODUCTOS (sede_id, producto_id) VALUES (1, 1);
INSERT INTO SEDE_PRODUCTOS (sede_id, producto_id) VALUES (1, 2);
INSERT INTO SEDE_PRODUCTOS (sede_id, producto_id) VALUES (2, 3);
INSERT INTO SEDE_PRODUCTOS (sede_id, producto_id) VALUES (2, 4);
INSERT INTO SEDE_PRODUCTOS (sede_id, producto_id) VALUES (3, 5);

-- Relacion Sede - Discos
INSERT INTO SEDE_DISCOS (sede_id, disco_id) VALUES (1, 1);
INSERT INTO SEDE_DISCOS (sede_id, disco_id) VALUES (1, 2);
INSERT INTO SEDE_DISCOS (sede_id, disco_id) VALUES (2, 3);
INSERT INTO SEDE_DISCOS (sede_id, disco_id) VALUES (2, 4);
INSERT INTO SEDE_DISCOS (sede_id, disco_id) VALUES (3, 5);