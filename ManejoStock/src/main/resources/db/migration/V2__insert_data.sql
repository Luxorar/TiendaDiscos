INSERT INTO SEDE (nombre_sede, direccion_sede) VALUES ('Sede Santiago Centro', 'Av. Libertador Bernardo O Higgins 1234');
INSERT INTO SEDE (nombre_sede, direccion_sede) VALUES ('Sede Providencia', 'Av. Providencia 567');
INSERT INTO SEDE (nombre_sede, direccion_sede) VALUES ('Sede Ñuñoa', 'Av. Irarrázaval 890');

INSERT INTO INFO_STOCK (nombre_producto, sede_id, stock_actual) VALUES ('Thriller - Michael Jackson', 1, 50);
INSERT INTO INFO_STOCK (nombre_producto, sede_id, stock_actual) VALUES ('The Dark Side of the Moon - Pink Floyd', 1, 30);
INSERT INTO INFO_STOCK (nombre_producto, sede_id, stock_actual) VALUES ('Tocadiscos Portátil', 1, 15);
INSERT INTO INFO_STOCK (nombre_producto, sede_id, stock_actual) VALUES ('Audífonos Over-Ear', 2, 20);
INSERT INTO INFO_STOCK (nombre_producto, sede_id, stock_actual) VALUES ('Limpiador de Vinilos', 2, 10);
INSERT INTO INFO_STOCK (nombre_producto, sede_id, stock_actual) VALUES ('Back in Black - AC/DC', 3, 25);

INSERT INTO DISCO_STOCK (id, artista) VALUES (1, 'Michael Jackson');
INSERT INTO DISCO_STOCK (id, artista) VALUES (2, 'Pink Floyd');
INSERT INTO DISCO_STOCK (id, artista) VALUES (6, 'AC/DC');

INSERT INTO PRODUCTO_STOCK (id, marca) VALUES (3, 'Crosley');
INSERT INTO PRODUCTO_STOCK (id, marca) VALUES (4, 'Audio-Technica');
INSERT INTO PRODUCTO_STOCK (id, marca) VALUES (5, 'Spin-Clean');
