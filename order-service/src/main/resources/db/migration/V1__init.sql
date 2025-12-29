CREATE TABLE t_order (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(255),
    sku_code VARCHAR(255),
    price DECIMAL(19,2),
    quantity INT
);
