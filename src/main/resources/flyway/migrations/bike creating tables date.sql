DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS buyer;
DROP TABLE IF EXISTS bikes;

-- Create tables
CREATE TABLE bikes (
  bike_id int not null auto_increment,
  bike_model varchar(30) not null,
  brand varchar(30) not null,
  bike_price decimal(7,2) not null,
  primary key (bike_id)
);

CREATE TABLE buyer (
  customer_id int not null auto_increment,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  phone varchar(15) not null,
  primary key (customer_id)
);

CREATE TABLE orders (
  order_id int not null auto_increment,
  order_total decimal(9,2) not null,
  customer_id int not null,
  primary key (order_id),
  foreign key (customer_id) references buyer(customer_id)
);

CREATE TABLE order_items (
  order_id int not null,
  bike_id int not null,
  primary key (order_id, bike_id),
  foreign key (order_id) references orders(order_id),
  foreign key (bike_id) references bikes(bike_id));