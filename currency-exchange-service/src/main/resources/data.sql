create table exchange_value(
    id bigint not null,
    currency_from varchar(4),
    currency_to varchar(4),
    conversion_multiple bigint,
    port bigint
);

insert into exchange_value(id,currency_from,currency_to,conversion_multiple,port)
    values(1001,'USD','INR',65,0);
insert into exchange_value(id,currency_from,currency_to,conversion_multiple,port)
    values(1002,'AUD','INR',25,0);
insert into exchange_value(id,currency_from,currency_to,conversion_multiple,port)
    values(1003,'EUR','INR',75,0);