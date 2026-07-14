-- Ensure CARRITO_DISCOS has an auto-increment id column as primary key
SET @has_id = (SELECT COUNT(*) FROM information_schema.COLUMNS
               WHERE TABLE_SCHEMA = DATABASE()
               AND TABLE_NAME = 'CARRITO_DISCOS'
               AND COLUMN_NAME = 'id');

SET @sql = IF(@has_id = 0,
    'ALTER TABLE CARRITO_DISCOS ADD COLUMN id BIGINT AUTO_INCREMENT PRIMARY KEY FIRST',
    'SELECT 1');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
