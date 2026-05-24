CREATE TABLE USUARIO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    gmail VARCHAR(255) NOT NULL
);

CREATE TABLE PRODUCTO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio INT NOT NULL
);

CREATE TABLE VENTAS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_venta DATE,
    usuario_id BIGINT NOT NULL,
    puntos_usados INT,
    puntos_ganados INT NOT NULL,
    descuento INT,
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(id)
);

CREATE TABLE VENTA_PRODUCTOS (
    venta_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    PRIMARY KEY (venta_id, producto_id),
    FOREIGN KEY (venta_id) REFERENCES VENTAS(id),
    FOREIGN KEY (producto_id) REFERENCES PRODUCTO(id)
);