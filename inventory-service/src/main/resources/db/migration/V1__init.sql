CREATE TABLE inventory (
    id          BIGINT(20) NOT NULL AUTO_INCREMENT,
    sku_code    TEXT DEFAULT NULL,
    quantity    INT(11) DEFAULT NULL,
    PRIMARY KEY (id)
);