-- Restructure: DISCO = album, TITULO = song with FK to album
-- Add disco_id to TITULO
ALTER TABLE TITULO ADD COLUMN disco_id BIGINT;

-- Map each song to the album (keeping the MIN discos.id per unique nombre+artista+precio)
UPDATE TITULO t
INNER JOIN DISCOS d ON d.titulo_id = t.id
INNER JOIN (
    SELECT MIN(id) AS album_id, nombre, artista, precio
    FROM DISCOS
    GROUP BY nombre, artista, precio
) album ON d.nombre = album.nombre AND d.artista = album.artista AND d.precio = album.precio
SET t.disco_id = album.album_id;

-- Delete duplicate discos rows (keep MIN id per album)
DELETE d FROM DISCOS d
INNER JOIN (
    SELECT MIN(id) AS keep_id, nombre, artista, precio
    FROM DISCOS
    GROUP BY nombre, artista, precio
    HAVING COUNT(*) > 1
) dups ON d.nombre = dups.nombre AND d.artista = dups.artista AND d.precio = dups.precio AND d.id != dups.keep_id;

-- Drop old FK and column from DISCOS
ALTER TABLE DISCOS DROP FOREIGN KEY DISCOS_ibfk_1;
ALTER TABLE DISCOS DROP COLUMN titulo_id;

-- Make disco_id NOT NULL and add FK
ALTER TABLE TITULO MODIFY COLUMN disco_id BIGINT NOT NULL;
ALTER TABLE TITULO ADD CONSTRAINT FK_TITULO_DISCO FOREIGN KEY (disco_id) REFERENCES DISCOS(id);
