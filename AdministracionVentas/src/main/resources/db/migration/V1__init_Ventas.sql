CREATE TABLE PRODUCTO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio INT NOT NULL,
    id_producto BIGINT,
    tipo TINYINT
);

CREATE TABLE VENTAS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_venta DATE,
    usuario_id BIGINT NOT NULL,
    puntos_usados INT,
    puntos_ganados INT NOT NULL,
    descuento INT
);

CREATE TABLE VENTA_PRODUCTOS (
    venta_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    PRIMARY KEY (venta_id, producto_id),
    FOREIGN KEY (venta_id) REFERENCES VENTAS(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES PRODUCTO(id)
);
