# create database project;
use project;

create table customers(
    customer_id int auto_increment primary key,
    name varchar(100) not null,
    phone varchar(20),
    email varchar(100),
    address varchar(255)
);

create table products(
    product_id int auto_increment primary key,
    name varchar(100) not null,
    brand varchar(50),
    price decimal(10, 2) not null,
    stock int default 0
);

create table invoices(
    invoice_id int auto_increment primary key,
    customer_id int,
    invoice_date date,
    total_amount decimal(12,2),
    foreign key (customer_id) references customers(customer_id)
);

create table invoice_items(
    item_id int auto_increment primary key,
    invoice_id int,
    product_id int,
    quantity int not null,
    unit_price decimal(10, 2) not null,
    foreign key (invoice_id) references invoices(invoice_id),
    foreign key (product_id) references products(product_id)
);

create table users(
    user_id int auto_increment primary key,
    user_account varchar(100) not null,
    user_password varchar(100) not null
);

insert into users(user_account, user_password)
values('admin', 'admin');

# tất cả phần liên quan product
delimiter //
create procedure display_product()
begin
    select * from products;
end;

create procedure add_product(name_in varchar(100), brand_in varchar(50), price_in decimal(10,2), stock_in int)
begin
    insert into products(name, brand, price, stock)
        values(name_in, brand_in, price_in, stock_in);
end;

create procedure update_product(id_in int, name_in varchar(100), brand_in varchar(50), price_in decimal(10,2), stock_in int)
begin
    update products
    set name = name_in,
        brand = brand_in,
        price = price_in,
        stock = stock_in
    where product_id = id_in;
end;

create procedure delete_product(id_in int)
begin
    delete from products where product_id = id_in;
end //

create procedure get_product_by_id(id_in int)
begin
    select * from products
        where product_id = id_in;
end //
delimiter //
# end-tất cả phần liên quan product

delimiter //
create procedure display_user()
begin
    select * from users;
end;
delimiter //
