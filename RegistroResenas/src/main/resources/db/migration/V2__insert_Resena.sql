INSERT INTO DISCO_B (nombre, artista) VALUES ('Thriller', 'Michael Jackson');
INSERT INTO DISCO_B (nombre, artista) VALUES ('The Dark Side of the Moon', 'Pink Floyd');
INSERT INTO DISCO_B (nombre, artista) VALUES ('Back in Black', 'AC/DC');
INSERT INTO DISCO_B (nombre, artista) VALUES ('Abbey Road', 'The Beatles');

INSERT INTO USUARIO_D (nombre, gmail) VALUES ('Luchi', 'luchi@gmail.com');
INSERT INTO USUARIO_D (nombre, gmail) VALUES ('Ana Garcia', 'ana.garcia@gmail.com');
INSERT INTO USUARIO_D (nombre, gmail) VALUES ('Pedro Pascal', 'pedrito@gmail.com');

-- Luchi (ID 1) comenta sobre Thriller (ID 1)
INSERT INTO RESENA (mensaje, user_id, disco_id)
VALUES ('Un clásico inmortal, mi favorito.', 1, 1);

-- Ana (ID 2) comenta sobre Abbey Road (ID 4)
INSERT INTO RESENA (mensaje, user_id, disco_id)
VALUES ('La mejor portada de la historia y gran sonido.', 2, 4);

-- Pedro (ID 3) comenta sobre Back in Black (ID 3)
INSERT INTO RESENA (mensaje, user_id, disco_id)
VALUES ('Puro rock n roll del bueno.', 3, 3);

-- Luchi (ID 1) comenta sobre Pink Floyd (ID 2)
INSERT INTO RESENA (mensaje, user_id, disco_id)
VALUES ('Una experiencia psicodélica increíble.', 1, 2);