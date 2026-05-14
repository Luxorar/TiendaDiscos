CREATE TABLE ENVIO(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venta_id BIGINT NOT NULL,
    direccion_destino VARCHAR(255) NOT NULL,
    tipo_despacho VARCHAR(50) NOT NULL,
    empresa_reparto VARCHAR(50) NOT NULL,
    estado_envio VARCHAR(50) NOT NULL,
    fecha_entrega DATE NOT NULL
);
