INSERT INTO Cliente (nombre, apellido) VALUES ('Diego', 'Quezada');
INSERT INTO Cliente (nombre, apellido) VALUES ('Maria', 'Ruiz');

INSERT INTO Orden (codigo, fecha, cliente_id) VALUES ('OC-000001', '2024-07-09 10:00:00', 1);
INSERT INTO Orden (codigo, fecha, cliente_id) VALUES ('OC-000002', '2024-07-09 10:00:00', 2);

INSERT INTO Articulo (codigo, nombre, precio_unitario, orden_id) VALUES ('A-001', 'Laptop', 1200.00, 1);
INSERT INTO Articulo (codigo, nombre, precio_unitario, orden_id) VALUES ('A-002', 'Mouse', 20.00, 2);
