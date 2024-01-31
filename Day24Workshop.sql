drop database if exists orders;

create database orders;
use orders;

create table orders(
    order_id int auto_increment not null,
    order_date timestamp default current_timestamp not null,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal (2,2) default '0.05',

    primary key(order_id)
);

create table order_details(
    id int auto_increment not null,
    product varchar(64) not null,
    unit_price decimal (3,2),
    discount decimal (3,2) default '1.0',
    quantity int not null,
    order_id int not null,

    primary key(id),
    constraint fk_order_id foreign key(order_id) references orders(order_id)
);