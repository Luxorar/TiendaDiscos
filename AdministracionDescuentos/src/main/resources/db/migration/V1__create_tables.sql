CREATE TABLE DISCO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    artista VARCHAR(255) NOT NULL,
    precio INT NOT NULL
);

CREATE TABLE Producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL,
    precio INT NOT NULL
);

CREATE TABLE DESCUENTO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_descuento VARCHAR(255) NOT NULL,
    disco BIGINT,
    estado ENUM('ACTIVO', 'INACTIVO') NOT NULL,
    descuento DOUBLE,
    FOREIGN KEY (disco) REFERENCES DISCO(id)
);

CREATE TABLE DESCUENTOS_DISCO (
    descuento_id BIGINT NOT NULL,
    disco_id BIGINT NOT NULL,
    PRIMARY KEY (descuento_id, disco_id),
    FOREIGN KEY (descuento_id) REFERENCES DESCUENTO(id),
    FOREIGN KEY (disco_id) REFERENCES DISCO(id)
);

CREATE TABLE DESCUENTOS_PRODUCTOS (
    descuento_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    PRIMARY KEY (descuento_id, producto_id),
    FOREIGN KEY (descuento_id) REFERENCES DESCUENTO(id),
    FOREIGN KEY (producto_id) REFERENCES Producto(id)
);