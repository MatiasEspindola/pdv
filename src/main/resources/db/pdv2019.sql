-- 20/06/2019 --

DROP DATABASE pdv2019;
CREATE DATABASE pdv2019;
USE pdv2019;

CREATE TABLE roles(
     pk_id_rol int not null primary key auto_increment,
     rol varchar(20) not null,
     autorithy varchar(20) not null
);

CREATE TABLE usuarios(
    pk_id_us int not null primary key auto_increment,
    us varchar(25) not null,
    ps varchar(25) not null,
    hab varchar(25) not null
); 

CREATE TABLE productos(
	pk_id_prod int not null primary key auto_increment,
    nombre varchar(25) not null,
	precio double not null,
    foto varchar(255),
  --  fecha_envasado date not null,
  --  fecha_vencimiento date not null,
    stock int not null
);

CREATE TABLE metodos_de_pagos(
	pk_id_mdp int not null primary key auto_increment,
	metodo varchar(65) not null,
    hab boolean
);

CREATE TABLE compras(
	pk_id_com int not null primary key auto_increment,
	subtotal double not null,
    total double not null,
    cantidad int not null,
    descripcion varchar(50) not null,
	fecha date not null,
    monto_envio double not null,
    en_camino boolean,
    demorado boolean,
    entregado boolean,
    perdida boolean
);

/*
CREATE TABLE HistorialCompra(
    pk_id_hc int not null primary key auto_increment,
    fecha date not null
);
*/

CREATE TABLE proveedores(
	pk_id_prov int not null primary key auto_increment,
    nombre varchar(55) not null,
	direccion varchar(55) not null,
    tel varchar(20) not null,
    cel varchar(20) not null,
    email varchar(55) not null,
    foto varchar(255)
);

CREATE TABLE ciudades(
	pk_id_ciu int not null primary key auto_increment,
    ciudad varchar(60) not null,
    cp int
);

CREATE TABLE provincias(
	pk_id_prov int not null primary key auto_increment,
    provincia varchar(25) not null
);

CREATE TABLE ventas(
	pk_id_ven int not null primary key auto_increment,
    descripcion varchar(50),
    descu double not null,
	monto double not null,
    cantidad int not null,
    fecha date not null,
    hora datetime not null,
    en_camino boolean,
    demorado boolean,
    entregado boolean,
    perdida boolean
);

CREATE TABLE tickets(
	pk_id_tic int not null primary key auto_increment
);

/*
CREATE TABLE HistorialVenta(
    pk_id_hv int not null primary key auto_increment,
    fecha date not null
);
*/

CREATE TABLE tipos_documentos(
	pk_id_tipo int not null primary key auto_increment,
    tipo varchar(65)
);

CREATE TABLE clientes(
	pk_id_cli int not null primary key auto_increment,
	nombre varchar(55) not null,
    apellido varchar(40) not null,
    doc varchar(10) not null,
    direccion varchar(55) not null,
    tel varchar(20) not null,
    cel varchar(20) not null,
    email varchar(55) not null,
    obs varchar(255) not null
);

CREATE TABLE categorias(
	pk_id_cat int not null primary key auto_increment,
    categoria varchar(100)
);

CREATE TABLE marcas(
	pk_id_mar int not null primary key auto_increment,
    marca varchar(50)
);

-- Agregar Columnas Modificado MatiasEspindola 20/06/2019 --
ALTER TABLE ciudades ADD COLUMN fk_id_provincia INT NULL;
ALTER TABLE proveedores ADD COLUMN fk_id_ciu INT NULL;
ALTER TABLE productos ADD COLUMN fk_id_prov INT NULL;
ALTER TABLE productos ADD COLUMN fk_id_cat INT NULL;
ALTER TABLE productos ADD COLUMN fk_id_mar INT NULL;
ALTER TABLE compras ADD COLUMN fk_id_metododepago INT NULL;
ALTER TABLE compras ADD COLUMN fk_id_prod INT NULL;
ALTER TABLE ventas ADD COLUMN fk_id_producto INT NULL;
ALTER TABLE ventas ADD COLUMN fk_id_us INT NULL;
ALTER TABLE compras ADD COLUMN fk_id_usuario INT NULL;
ALTER TABLE ventas ADD COLUMN fk_id_cli INT NULL;
ALTER TABLE ventas ADD COLUMN fk_id_mdp INT NULL;
ALTER TABLE clientes ADD COLUMN fk_id_ciudad INT NULL;
ALTER TABLE clientes ADD COLUMN fk_id_tipo INT NULL;
ALTER TABLE tickets ADD COLUMN fk_id_vent INT NULL;

-- MatiasEspindola 20/06/2019 --

ALTER TABLE usuarios ADD COLUMN fk_id_rol INT NULL;

-- Agregar Relaciones

ALTER TABLE productos ADD CONSTRAINT fk_id_prov
FOREIGN KEY (fk_id_prov) REFERENCES proveedores(pk_id_prov);

ALTER TABLE productos ADD CONSTRAINT fk_id_cat
FOREIGN KEY (fk_id_cat) REFERENCES categorias(pk_id_cat);

ALTER TABLE productos ADD CONSTRAINT fk_id_mar
FOREIGN KEY (fk_id_mar) REFERENCES marcas(pk_id_mar);

ALTER TABLE ventas ADD CONSTRAINT fk_id_cli
FOREIGN KEY (fk_id_cli) REFERENCES clientes(pk_id_cli);

ALTER TABLE ventas ADD CONSTRAINT fk_id_mdp
FOREIGN KEY (fk_id_mdp) REFERENCES metodos_de_pagos(pk_id_mdp);

ALTER TABLE compras ADD CONSTRAINT fk_id_metododepago
FOREIGN KEY (fk_id_metododepago) REFERENCES metodos_de_pagos(pk_id_mdp);

ALTER TABLE ciudades ADD CONSTRAINT fk_id_provincia
FOREIGN KEY (fk_id_provincia) REFERENCES provincias(pk_id_prov);

ALTER TABLE proveedores ADD CONSTRAINT fk_id_ciu
FOREIGN KEY (fk_id_ciu) REFERENCES ciudades(pk_id_ciu);

-- Agregado MatiasEspindola 20/06/2019

ALTER TABLE clientes ADD CONSTRAINT fk_id_ciudad
FOREIGN KEY (fk_id_ciudad) REFERENCES ciudades(pk_id_ciu);

ALTER TABLE compras ADD CONSTRAINT fk_id_prod
FOREIGN KEY (fk_id_prod) REFERENCES productos(pk_id_prod);

ALTER TABLE ventas ADD CONSTRAINT fk_id_producto
FOREIGN KEY (fk_id_producto) REFERENCES productos(pk_id_prod);

ALTER TABLE ventas ADD CONSTRAINT fk_id_us
FOREIGN KEY (fk_id_us) REFERENCES usuarios(pk_id_us);

ALTER TABLE compras ADD CONSTRAINT fk_id_usuario
FOREIGN KEY (fk_id_usuario) REFERENCES usuarios(pk_id_us);

ALTER TABLE usuarios ADD CONSTRAINT fk_id_rol
FOREIGN KEY (fk_id_rol) REFERENCES roles(pk_id_rol);

ALTER TABLE tickets ADD CONSTRAINT fk_id_vent
FOREIGN KEY (fk_id_vent) REFERENCES ventas(pk_id_ven);

ALTER TABLE clientes ADD CONSTRAINT fk_id_tipo
FOREIGN KEY (fk_id_tipo) REFERENCES tipos_documentos(pk_id_tipo);
