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

create table invoice_details(
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
    user_password varchar(100) not null,
    status bit
);

# insert into users(user_account, user_password, status)
# values('admin', 'admin', 1);

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

delimiter //
create procedure search_by_brand(brand_in varchar(50))
begin
    select * from products
        where brand like concat('%', lower(brand_in), '%');
end;

create procedure search_by_price(start decimal(10,2), end decimal(10,2))
begin
    select * from products
    where price >= start and price <= end;
end;

create procedure search_by_stock(start int, end int)
begin
    select * from products
    where stock >= start and stock <= end;
end;
delimiter //
# end-tất cả phần liên quan product

delimiter //
create procedure display_user()
begin
    select * from users;
end;
delimiter //

delimiter //
create procedure update_status_user(id_in int, account_in varchar(100), password_in varchar(100), status_in bit)
begin
    update users
        set user_account = account_in,
            user_password = password_in,
            status=status_in
    where user_id=id_in;
end;
delimiter //

delimiter //
create procedure get_user_by_id(id_in int)
begin
    select * from users
    where user_id = id_in;
end //
delimiter //


# tất cả phần liên quan customer
delimiter //
create procedure display_customer()
begin
    select * from customers;
end;

create procedure add_customer(name_in varchar(100), phone_in varchar(20), email_in varchar(100), address_in varchar(255))
begin
    insert into customers(name, phone, email, address)
    values(name_in, phone_in, email_in, address_in);
end;

create procedure update_customer(id_in int, name_in varchar(100), phone_in varchar(20), email_in varchar(100), address_in varchar(255))
begin
    update customers
    set name = name_in,
        phone = phone_in,
        email = email_in,
        address = address_in
    where customer_id = id_in;
end;

create procedure delete_customer(id_in int)
begin
    delete from customers where customer_id = id_in;
end //

create procedure get_customer_by_id(id_in int)
begin
    select * from customers
    where customer_id = id_in;
end //
delimiter //
# end-tất cả phần liên quan customer

delimiter //
create procedure get_invoice_by_id(id_in int)
begin
    select * from invoices
    where invoice_id = id_in;
end //
delimiter //

delimiter //
create procedure display_invoice()
begin
    select * from invoices;
end;

create procedure add_invoice(customer_id_in int, invoice_date_in date, total_amount_in decimal(12,2))
begin
    insert into invoices(customer_id, invoice_date, total_amount)
    values(customer_id_in, invoice_date_in, total_amount_in);
end;
delimiter //

delimiter //
create procedure display_invoice_detail()
begin
    select * from invoice_details;
end;

create procedure add_invoice_detail(invoice_id_in int, product_id_in int, quantity_in int, unit_price_in decimal(10, 2))
begin
    insert into invoice_details(invoice_id, product_id, quantity, unit_price)
    values(invoice_id_in, product_id_in, quantity_in, unit_price_in);
end;
delimiter //
