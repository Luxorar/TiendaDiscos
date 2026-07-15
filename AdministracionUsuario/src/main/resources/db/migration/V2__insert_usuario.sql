INSERT INTO USUARIO (nombre, gmail, fecha_registro, puntos, contrasena, cuenta_activa, credito, modo_oscuro)
VALUES ('Luchi', 'luchi@gmail.com', '2026-01-15', 30000, '$2a$10$abc123', TRUE, 0, FALSE);

INSERT INTO USUARIO (nombre, gmail, fecha_registro, puntos, contrasena, cuenta_activa, credito, modo_oscuro)
VALUES ('Ana Garcia', 'ana.garcia@gmail.com', '2026-02-20', 200, '$2a$10$def456', TRUE, 0, FALSE);

INSERT INTO USUARIO (nombre, gmail, fecha_registro, puntos, contrasena, cuenta_activa, credito, modo_oscuro)
VALUES ('Pedro Pascal', 'pedrito@gmail.com', '2026-03-10', 0, '$2a$10$ghi789', FALSE, 0, FALSE);

INSERT INTO ADMINISTRADOR (nombre, gmail, fecha_registro, contrasena, cuenta_activa)
VALUES ('Admin Principal', 'admin@tiendadiscos.cl', '2025-01-01', '$2a$10$admin1', TRUE);

INSERT INTO ADMINISTRADOR (nombre, gmail, fecha_registro, contrasena, cuenta_activa)
VALUES ('Admin Secundario', 'admin2@tiendadiscos.cl', '2025-06-01', '$2a$10$admin2', TRUE);
