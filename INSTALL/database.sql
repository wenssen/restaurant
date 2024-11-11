CREATE DATABASE reservas_db;

USE reservas_db;

-- Tabla para almacenar las mesas
CREATE TABLE mesas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    capacidad INT NOT NULL,           -- Capacidad máxima de personas para la mesa
    disponible BOOLEAN DEFAULT TRUE    -- Estado de disponibilidad de la mesa
);

-- Tabla para almacenar los clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,      -- Nombre del cliente
    contacto VARCHAR(100) NOT NULL     -- Información de contacto (teléfono, email, etc.)
);

-- Tabla para almacenar las reservas
CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mesa_id INT NOT NULL,               -- ID de la mesa reservada (foreign key)
    cliente_id INT NOT NULL,            -- ID del cliente que realiza la reserva (foreign key)
    fecha_hora DATETIME NOT NULL,       -- Fecha y hora de la reserva
    estado ENUM('Confirmada', 'Cancelada') DEFAULT 'Confirmada', -- Estado de la reserva
    FOREIGN KEY (mesa_id) REFERENCES mesas(id) ON DELETE CASCADE,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);
