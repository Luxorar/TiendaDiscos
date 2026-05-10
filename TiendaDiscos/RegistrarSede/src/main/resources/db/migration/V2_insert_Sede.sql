--Insert Discos
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('Thriller', 'Michael Jackson', 40000);
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('The Dark Side of the Moon', 'Pink Floyd', 25000);
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('Back in Black', 'AC/DC', 30000);
INSERT INTO DISCOS (nombre, artista, precio) VALUES ('Abbey Road', 'The Beatles', 20000);

--Insert productos
INSERT INTO PRODUCTOS (nombre, precio) VALUES('Tornamesa the rolling stones', 90000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES('Tornamesa pink floyd', 150000);
INSERT INTO PRODUCTOS (nombre, precio) VALUES('Tornamesa the beatles, Yellow Submarine', 160000);

--Insert Sede

INSERT INTO SEDE (nombre, direccion, id_discos, id_productos)
VALUES('Music store puerto montt', 'Local 23, Mall paseo costanera mar, Puerto Montt', 1, 2);

INSERT INTO SEDE (nombre, direccion, id_discos, id_productos)
VALUES('Music store puerto montt', 'Local 23, Mall paseo costanera mar, Puerto Montt', 2, 1);

INSERT INTO SEDE (nombre, direccion, id_discos, id_productos)
VALUES('Music store puerto montt', 'Local 23, Mall paseo costanera mar, Puerto Montt', 3, 2);

INSERT INTO SEDE (nombre, direccion, id_discos, id_productos)
VALUES('Music store puerto montt', 'Local 5, Mall paseo puerto varas, Puerto varas', 4, 3);

