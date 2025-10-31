-- Tabla Productos
CREATE TABLE Productos(
    Id INTEGER PRIMARY KEY AUTOINCREMENT,
    Tipo TEXT
);
INSERT INTO Productos VALUES
(1,"Televisores"),
(2,"Televisores"),
(3,"Televisores"),
(4,"Televisores"),
(5,"Televisores"),
(6,"Televisores"),
(7,"Tablets"),
(8,"Tablets"),
(9,"Tablets"),
(10,"Tablets"),
(11,"Tablets"),
(12,"Tablets"),
(13,"PCs"),
(14,"PCs"),
(15,"PCs"),
(16,"PCs"),
(17,"PCs"),
(18,"Notebooks"),
(19,"Notebooks"),
(20,"Notebooks"),
(21,"Notebooks"),
(22,"Notebooks"),
(23,"Celulares"),
(24,"Celulares"),
(25,"Celulares"),
(26,"Celulares"),
(27,"Celulares");

-- Tabla Televisores
CREATE TABLE Televisores(
    Id INTEGER,
    Product_Id INTEGER,
    Marca,
    Pantalla,
    Resolucion,
    TipoPantalla,
    Precio DECIMAL(12, 2),
    Stock INTEGER,
    FOREIGN KEY(Product_Id) REFERENCES Productos(Id)
);

INSERT INTO Televisores(Id, Product_Id, Marca, Pantalla, Resolucion, TipoPantalla, Precio, Stock) VALUES
(1,1, 'LG', '32"', '1440p', 'TFT', 1000000.00, 10),
(2,2, 'Samsung', '55"', '1080p', 'QLED', 19600000.00, 5),
(3,3, 'Philips', '50"', '1440p', 'LED', 650000.00, 8),
(4,4, 'RCA', '43"', '1080p', 'LED', 250000.00, 6),
(5,5, 'TCL', '32"', '720p', 'LCD', 150000.00, 12),
(6,6, 'Acer', '42"', '1080p', 'TFT', 200000.00, 9);

-- Tabla Tablets
CREATE TABLE Tablets (
    Id INTEGER PRIMARY KEY,
    Product_Id INTEGER,
    Marca,
    Pantalla,
    RAM INTEGER,
    ROM INTEGER,
    Camara,
    Precio DECIMAL(12, 2),
    Stock INTEGER,
    FOREIGN KEY(Product_Id) REFERENCES Productos(Id)
);

INSERT INTO Tablets (Id, Product_Id, Marca, Pantalla, RAM, ROM, Camara, Precio, Stock) VALUES
(1,7, 'Samsung', '11"', 8, 128, '8 MP', 450000.00, 5),
(2,8, 'Samsung', '8.7"', 4, 64, '0 MP', 210000.00, 3),
(3,9, 'Lenovo', '11"', 4, 128, '13 MP', 500000.00, 6),
(4,10, 'Lenovo', '12.4"', 4, 256, '13 MP', 1000000.00, 7),
(5,11, 'Samsung', '12.4"', 16, 512, '13 MP', 2000000.00, 9),
(6,12, 'Apple', '10"', 4, 256, '12 MP', 500000.00, 10);

-- Tabla PCs
CREATE TABLE PCs (
    Id INTEGER PRIMARY KEY,
    Product_Id INTEGER,
    Marca,
    Procesador,
    RAM INTEGER,
    Disco,
    TarjetaDeVideo,
    Fuente,
    Precio DECIMAL(12, 2),
    Stock INTEGER,
    FOREIGN KEY(Product_Id) REFERENCES Productos(Id)
);

INSERT INTO PCs (Id, Product_Id, Marca, Procesador, RAM, Disco, TarjetaDeVideo, Fuente, Precio, Stock) VALUES
(1,13, 'BGH', 'INTEL', 8, '256', 'Integrada', '500W', 500000.00, 5),
(2,14, 'HP', 'INTEL', 16, '512', 'Integrada', '600W', 800000.00, 3),
(3,15, 'HP', 'AMD', 16, '512', 'Integrada', '600W', 800000.00, 3),
(4,16, 'Acer', 'AMD', 32, '1000', 'RTX 3090', '850W', 1500000.00, 6),
(5,17, 'Razer', 'INTEL', 16, '1000', 'RTX 3080', '850W', 2000000.00, 9);

-- Tabla Notebooks
CREATE TABLE Notebooks (
    Id INTEGER PRIMARY KEY,
    Product_Id INTEGER,
    Marca,
    Pantalla,
    Procesador,
    RAM INTEGER,
    Disco,
    Precio DECIMAL(12, 2),
    Stock INTEGER,
    FOREIGN KEY(Product_Id) REFERENCES Productos(Id)
);

INSERT INTO Notebooks (Id, Product_Id, Marca, Pantalla, Procesador, RAM, Disco, Precio, Stock) VALUES
(1,18, 'Lenovo', '14"', 'AMD Ryzen-7', 16, '1024 SSD NVMe M2 PCIe Gen-3x4', 120900.00, 1),
(2,19, 'Lenovo', '15"', 'AMD Ryzen-7', 32, '10240 SSD NVMe M2 PCIe Gen-3x4', 107000.00, 1),
(3,20, 'Acer', '14"', 'INTEL Core i5', 8, '512 SSD NVMe M2 PCIe Gen-3', 500070.00, 1),
(4,21, 'Acer', '15"', 'INTEL Core i7', 16, '10240 SSD NVMe M2 PCIe Gen-3', 107000.00, 1),
(5,22, 'Asus', '14"', 'INTEL Core i9', 32, '20480 SSD NVMe M2 PCIe Gen-3', 1509000.00, 1);

-- Tabla Celulares
CREATE TABLE Celulares (
    Id INTEGER PRIMARY KEY,
    Product_Id INTEGER,
    Marca,
    Pantalla,
    RAM INTEGER,
    ROM INTEGER,
    Camara,
    Precio DECIMAL(12, 2),
    Stock INTEGER,
    FOREIGN KEY(Product_Id) REFERENCES Productos(Id)
);

INSERT INTO Celulares (Id, Product_Id, Marca, Pantalla, RAM, ROM, Camara, Precio, Stock) VALUES
(1,23, 'Samsung', '7"', 8, 128, '108MP', 220000.00, 220),
(2,24, 'Samsung', '6"', 4, 64, '48MP', 150000.00, 1),
(3,25, 'Motorola', '6"', 4, 64, '48MP', 150000.00, 1),
(4,26, 'Xiaomi', '6"', 4, 128, '50MP', 300000.00, 1),
(5,27, 'Apple', '8"', 4, 256, '60MP', 350000.00, 1);

-- Tabla Clientes
CREATE TABLE Clientes (
    Id INTEGER PRIMARY KEY,
    User_Id INTEGER,
    Nombre,
    Apellido,
    Email,
    Direccion,
    MetodoPago,
    DNI INTEGER,
    FOREIGN KEY(User_Id) REFERENCES Usuarios(Id)
);
-- Tabla Administradores
CREATE TABLE Administradores (
    Id INTEGER PRIMARY KEY,
    User_Id INTEGER,
    Nombre,
    Apellido,
    Email,
    Direccion,
    DNI INTEGER,
    FOREIGN KEY(User_Id) REFERENCES Usuarios(Id)
);

-- Tabla Usuarios
CREATE TABLE Usuarios(
  Id INTEGER PRIMARY KEY,
  UserName,
  Password,
  Tipo
);