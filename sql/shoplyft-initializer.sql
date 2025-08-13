--Create tables

-- 1. Users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'CUSTOMER',
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Items table
CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    item VARCHAR(255) NOT NULL,
    item_category VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Cart items table (many-to-one: user → cart items, item → cart items)
CREATE TABLE cart_items (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    item_id INT REFERENCES items(id),
    quantity INT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Orders table
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE SET NULL,
    total_amount DECIMAL(10,2),
    status VARCHAR(30) DEFAULT 'PLACED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Order items table (many-to-one: order → order_items, book → order_items)
CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(id) ON DELETE CASCADE,
    item_id INT REFERENCES items(id),
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10,2) NOT NULL
);

--Sample data

-- 1. Users
INSERT INTO users (user_name, email, password, role, first_name, last_name)
VALUES 
  ('alice', 'alice@example.com', 'password123', 'CUSTOMER', 'Alice', 'Brown'),
  ('bob', 'bob@example.com', 'securepass', 'CUSTOMER', 'Bob', 'White'),
  ('charlie', 'charlie@example.com', 'hunter2', 'CUSTOMER', 'Charlie', 'Green'),
  ('diana', 'diana@example.com', 'qwerty', 'CUSTOMER', 'Diana', 'Black'),
  ('eve', 'eve@example.com', 'passw0rd', 'CUSTOMER', 'Eve', 'Johnson'),
  ('frank', 'frank@example.com', 'letmein', 'CUSTOMER', 'Frank', 'Smith'),
  ('grace', 'grace@example.com', 'sunshine', 'CUSTOMER', 'Grace', 'Taylor'),
  ('henry', 'henry@example.com', 'ilovecats', 'CUSTOMER', 'Henry', 'Wright'),
  ('isabel', 'isabel@example.com', 'welcome1', 'CUSTOMER', 'Isabel', 'Adams'),
  ('jack', 'jack@example.com', 'abc123', 'CUSTOMER', 'Jack', 'Davis'),
  ('admin', 'admin@shoplyft.com', 'adminpass', 'ADMIN', 'Admin', 'Admin'),
  ('manager', 'manager@shoplyft.com', 'manage123', 'MANAGER', 'Michael', 'Stone');

-- 2. items
INSERT INTO items (item, item_category, description, price, quantity)
VALUES
  -- Tech Items
  ('Wireless Mouse', 'Electronics', 'Ergonomic 2.4GHz wireless mouse with USB receiver', 24.99, 50),
  ('Mechanical Keyboard', 'Electronics', 'RGB backlit mechanical keyboard with blue switches', 79.99, 30),
  ('USB-C Charger', 'Electronics', 'Fast-charging 65W USB-C wall charger', 39.99, 100),
  ('Bluetooth Headphones', 'Electronics', 'Over-ear noise-cancelling Bluetooth headphones', 129.99, 25),
  ('Portable SSD 1TB', 'Electronics', 'High-speed external solid-state drive with USB 3.2', 149.99, 40),

  -- Grocery Items
  ('Organic Bananas', 'Grocery', 'Fresh organic bananas, sold per bunch', 2.49, 100),
  ('Whole Milk 2L', 'Grocery', 'Pasteurized whole milk, 2 liters', 3.99, 80),
  ('Brown Eggs (12 pack)', 'Grocery', 'Free-range brown eggs, carton of 12', 4.79, 60),
  ('Almond Butter 500g', 'Grocery', 'Natural almond butter with no added sugar', 8.99, 40),
  ('Basmati Rice 5kg', 'Grocery', 'Premium long-grain basmati rice', 19.99, 25);


-- 3. Cart items for Alice (user_id = 1): adds mouse (id=1) x1, keyboard (id=2) x2
INSERT INTO cart_items (user_id, item_id, quantity) VALUES
  (1, 1, 1),
  (1, 2, 2);

-- 4. Alice places an order; total = sum(price_at_purchase * qty) from order_items = 24.99*1 + 79.99*2 = 184.97
INSERT INTO orders (user_id, total_amount, status) VALUES
  (1, 184.97, 'PLACED');

-- 5. Order items (snapshot prices at time of purchase)
INSERT INTO order_items (order_id, item_id, quantity, price_at_purchase) VALUES
  (1, 1, 1, 24.99),   -- Wireless Mouse
  (1, 2, 2, 79.99);   -- Mechanical Keyboard
