CREATE TABLE TITULO(
                       id BIGSERIAL PRIMARY KEY,
                       titulo VARCHAR(50) NOT NULL
);

CREATE TABLE DISCOS(
                       id BIGSERIAL PRIMARY KEY,
                       nombre VARCHAR(50) NOT NULL,
                       artista VARCHAR(50) NOT NULL,
                       precio BIGINT NOT NULL,
                       titulo_id BIGINT NOT NULL,
                       FOREIGN KEY (titulo_id) REFERENCES TITULO(ID)
);