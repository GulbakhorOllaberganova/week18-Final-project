INSERT INTO bikes (bike_model, bike_price, brand) VALUES
  ('Mountain Bike', 799.99, 'Brand1'),
  ('Road Bike', 1299.99, 'Brand2'),
  ('Electric Bike', 1899.99, 'Brand3');

INSERT INTO buyer (first_name, last_name, phone) VALUES
  ('John', 'Doe', '555-1234'),
  ('Jane', 'Smith', '555-5678'),
  ('Bob', 'Johnson', '555-9012');

INSERT INTO orders (order_total, customer_id) VALUES
  (1399.98, 1),
  (2299.98, 2),
  (1899.99, 3);

INSERT INTO order_items (order_id, bike_id) VALUES
  (1, 1),
  (1, 2),
  (2, 3);