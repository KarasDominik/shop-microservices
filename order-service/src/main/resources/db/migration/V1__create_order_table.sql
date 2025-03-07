CREATE TABLE orders (
    id              bigint(20) NOT NULL AUTO_INCREMENT,
    order_number    text default null,
    sku_code        text,
    price           decimal(19, 2),
    quantity        int(11),
    PRIMARY KEY(id)
);