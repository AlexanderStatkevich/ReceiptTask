create database "receiptDB"
    with owner postgres;

CREATE TABLE products
(
    id         bigint,
    name       varchar(255),
    price      decimal,
    sale_types varchar(255)
);

CREATE TABLE discount_cards
(
    card_number varchar(255),
    discount decimal
);
