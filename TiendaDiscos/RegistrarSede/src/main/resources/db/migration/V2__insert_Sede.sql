-- Insert SEDES primero (no tiene dependencias)
INSERT INTO SEDES (nombre, direccion) VALUES
    ('Music Store Puerto Montt', 'Local 23, Mall Paseo Costanera Mar, Puerto Montt'),
    ('Music Store Puerto Varas', 'Local 5, Mall Paseo Puerto Varas, Puerto Varas');

-- Insert DISCOS con su sede correspondiente
INSERT INTO DISCOS (nombre, artista, precio, sede_id) VALUES
    ('Thriller',                   'Michael Jackson', 40000, 1),
    ('The Dark Side of the Moon',  'Pink Floyd',      25000, 1),
    ('Back in Black',              'AC/DC',           30000, 2),
    ('Abbey Road',                 'The Beatles',     20000, 2);

-- Insert PRODUCTOS con su sede correspondiente
INSERT INTO PRODUCTOS (nombre, precio, sede_id) VALUES
    ('Tornamesa The Rolling Stones',            90000,  1),
    ('Tornamesa Pink Floyd',                    150000, 1),
    ('Tornamesa The Beatles Yellow Submarine',  160000, 2);