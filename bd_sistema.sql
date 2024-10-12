-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS tienda_virtual;
USE tienda_virtual;

-- Crear la tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Crear la tabla de productos
CREATE TABLE IF NOT EXISTS productos (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    categoria_id INT(11),
    PRIMARY KEY (id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- Crear la tabla de categorías
CREATE TABLE IF NOT EXISTS categorias (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- Crear la tabla de órdenes
CREATE TABLE IF NOT EXISTS ordenes (
    id INT(11) NOT NULL AUTO_INCREMENT,
    cliente_id INT(11) NOT NULL,
    fecha DATETIME NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Crear la tabla de detalles de orden
CREATE TABLE IF NOT EXISTS detalles_orden (
    id INT(11) NOT NULL AUTO_INCREMENT,
    orden_id INT(11) NOT NULL,
    producto_id INT(11) NOT NULL,
    cantidad INT(11) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (orden_id) REFERENCES ordenes(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Crear la tabla de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    direccion TEXT NOT NULL,
    telefono VARCHAR(15),
    PRIMARY KEY (id)
);

-- Indices de la tabla `usuarios`
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`email`);

-- AUTO_INCREMENT de las tablas volcadas
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `ordenes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `detalles_orden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- Insertar datos en la tabla de usuarios con contraseñas encriptadas
INSERT INTO usuarios (nombre, email, contraseña) VALUES 
('Juan Pérez', 'juan.perez@example.com', SHA2('password123', 256)),
('Ana Gómez', 'ana.gomez@example.com', SHA2('password456', 256));

-- Insertar datos en la tabla de categorías
INSERT INTO categorias (nombre) VALUES 
('Electrónica'),
('Ropa'),
('Hogar');

-- Insertar datos en la tabla de productos
INSERT INTO productos (nombre, descripcion, precio, categoria_id) VALUES 
('Televisor', 'Televisor de 50 pulgadas', 499.99, 1),
('Camiseta', 'Camiseta de algodón', 19.99, 2),
('Sofá', 'Sofá de 3 plazas', 299.99, 3);

-- Insertar datos en la tabla de clientes
INSERT INTO clientes (nombre, email, direccion, telefono) VALUES 
('Carlos López', 'carlos.lopez@example.com', 'Calle Falsa 123', '555-1234'),
('María Fernández', 'maria.fernandez@example.com', 'Avenida Siempre Viva 456', '555-5678');

-- Insertar datos en la tabla de órdenes
INSERT INTO ordenes (cliente_id, fecha, total) VALUES 
(1, '2023-10-01 10:00:00', 519.98),
(2, '2023-10-02 11:30:00', 319.98);

-- Insertar datos en la tabla de detalles de orden
INSERT INTO detalles_orden (orden_id, producto_id, cantidad, precio) VALUES 
(1, 1, 1, 499.99),
(1, 2, 1, 19.99),
(2, 3, 1, 299.99),
(2, 2, 1, 19.99);