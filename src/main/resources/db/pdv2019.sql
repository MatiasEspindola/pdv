drop database pdv;
create database pdv;
use pdv;

CREATE TABLE `roles` (
  `pk_id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(20) NOT NULL,
  `autorithy` varchar(20) NOT NULL,
  PRIMARY KEY (`pk_id_rol`)
);

CREATE TABLE `usuarios` (
  `pk_id_us` int(11) NOT NULL AUTO_INCREMENT,
  `us` varchar(25) NOT NULL,
  `ps` varchar(25) NOT NULL,
  `clave` varchar(25) NOT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_rol` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_us`),
  KEY `fk_id_rol` (`fk_id_rol`),
  CONSTRAINT `fk_id_rol` FOREIGN KEY (`fk_id_rol`) REFERENCES `roles` (`pk_id_rol`)
);

CREATE TABLE `provincias` (
  `pk_id_prov` int(11) NOT NULL AUTO_INCREMENT,
  `provincia` varchar(25) NOT NULL,
  PRIMARY KEY (`pk_id_prov`)
); 

CREATE TABLE `ciudades` (
  `pk_id_ciu` int(11) NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(60) NOT NULL,
  `cp` int(11) NOT NULL,
  `fk_id_provincia` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_ciu`),
  KEY `fk_id_provincia` (`fk_id_provincia`),
  CONSTRAINT `fk_id_provincia` FOREIGN KEY (`fk_id_provincia`) REFERENCES `provincias` (`pk_id_prov`)
);

CREATE TABLE `tipos_documentos` (
  `pk_id_tipo_documento` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`pk_id_tipo_documento`)
); 

CREATE TABLE `personas` (
  `pk_id_per` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `nacimiento` date NOT NULL,
  `doc` varchar(10) NOT NULL,
  `calle` varchar(55) NOT NULL,
  `numero` varchar(5) DEFAULT NULL,
  `piso` char(1) DEFAULT NULL,
  `dpto` varchar(4) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `genero` char(1) NOT NULL,
  `fk_id_ciudad` int(11) DEFAULT NULL,
  `fk_id_tipo_documento` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_per`),
  KEY `fk_id_ciudad` (`fk_id_ciudad`),
  KEY `fk_id_tipo_documento` (`fk_id_tipo_documento`),
  CONSTRAINT `fk_id_ciudad` FOREIGN KEY (`fk_id_ciudad`) REFERENCES `ciudades` (`pk_id_ciu`),
  CONSTRAINT `fk_id_tipo_documento` FOREIGN KEY (`fk_id_tipo_documento`) REFERENCES `tipos_documentos` (`pk_id_tipo_documento`)
);

CREATE TABLE `clientes` (
  `pk_id_cli` int(11) NOT NULL AUTO_INCREMENT,
  `alta` date NOT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_per` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_cli`),
  KEY `fk_id_per` (`fk_id_per`),
  CONSTRAINT `fk_id_per` FOREIGN KEY (`fk_id_per`) REFERENCES `personas` (`pk_id_per`)
);

CREATE TABLE `tipos_clientes` (
  `pk_id_tipo_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `anonimo` tinyint(1) DEFAULT NULL,
  `registrado` tinyint(1) DEFAULT NULL,
  `fk_id_clie` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_tipo_cliente`),
   KEY `fk_id_clie` (`fk_id_clie`),
  CONSTRAINT `fk_id_clie` FOREIGN KEY (`fk_id_clie`) REFERENCES `clientes` (`pk_id_cli`)
);

CREATE TABLE `categorias` (
  `pk_id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_id_cat`)
);


CREATE TABLE `marcas` (
  `pk_id_mar` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk_id_mar`)
);

CREATE TABLE `metodos_de_pagos` (
  `pk_id_mdp` int(11) NOT NULL AUTO_INCREMENT,
  `metodo` varchar(65) NOT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`pk_id_mdp`)
);

CREATE TABLE `proveedores` (
  `pk_id_prov` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `calle` varchar(55) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `piso` char(1) DEFAULT NULL,
  `dpto` varchar(4) DEFAULT NULL,
  `alta` date NOT NULL,
  `email` varchar(30) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_ciu` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_prov`),
  KEY `fk_id_ciu` (`fk_id_ciu`),
  CONSTRAINT `fk_id_ciu` FOREIGN KEY (`fk_id_ciu`) REFERENCES `ciudades` (`pk_id_ciu`)
);

CREATE TABLE `telefonos_clientes` (
  `pk_id_tel_cli` int(11) NOT NULL AUTO_INCREMENT,
  `tel1` varchar(20) NOT NULL,
  `tel2` varchar(20) DEFAULT NULL,
  `fk_id_cl` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_tel_cli`),
  KEY `fk_id_cl` (`fk_id_cl`),
  CONSTRAINT `fk_id_cl` FOREIGN KEY (`fk_id_cl`) REFERENCES `clientes` (`pk_id_cli`)
);

CREATE TABLE `telefonos_proveedores` (
  `pk_id_tel_prov` int(11) NOT NULL AUTO_INCREMENT,
  `tel1` varchar(20) NOT NULL,
  `tel2` varchar(20) DEFAULT NULL,
  `fk_id_pv` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_tel_prov`),
  KEY `fk_id_pv` (`fk_id_pv`),
  CONSTRAINT `fk_id_pv` FOREIGN KEY (`fk_id_pv`) REFERENCES `proveedores` (`pk_id_prov`)
);

CREATE TABLE `productos` (
  `pk_id_prod` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `modelo` varchar(15) NOT NULL,
  `precio` double NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_cat` int(11) DEFAULT NULL,
  `fk_id_mar` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_prod`),
  KEY `fk_id_cat` (`fk_id_cat`),
  KEY `fk_id_mar` (`fk_id_mar`),
  CONSTRAINT `fk_id_cat` FOREIGN KEY (`fk_id_cat`) REFERENCES `categorias` (`pk_id_cat`),
  CONSTRAINT `fk_id_mar` FOREIGN KEY (`fk_id_mar`) REFERENCES `marcas` (`pk_id_mar`)
);

CREATE TABLE `lineas_compras` (
  `pk_id_lin_comp` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_prod` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_lin_comp`),
  KEY `fk_id_prod` (`fk_id_prod`),
  CONSTRAINT `fk_id_prod` FOREIGN KEY (`fk_id_prod`) REFERENCES `productos` (`pk_id_prod`)
); 

CREATE TABLE `lineas_ventas` (
  `pk_id_lin_vent` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_producto` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_lin_vent`),
  KEY `fk_id_producto` (`fk_id_producto`),
  CONSTRAINT `fk_id_producto` FOREIGN KEY (`fk_id_producto`) REFERENCES `productos` (`pk_id_prod`)
);

CREATE TABLE `facturas_compras` (
  `pk_id_fac_comp` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `descu` double NOT NULL,
  `precio_unitario` double NOT NULL,
  `subtotal` double NOT NULL,
  `total` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
  `fechaEntrega` date NOT NULL,
  `monto_envio` double NOT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_lin_comp` int(11) DEFAULT NULL,
  `fk_id_mdp_comp` int(11) DEFAULT NULL,
  `fk_id_prov` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_fac_comp`),
  KEY `fk_id_lin_comp` (`fk_id_lin_comp`),
  KEY `fk_id_mdp_comp` (`fk_id_mdp_comp`),
  KEY `fk_id_prov` (`fk_id_prov`),
  CONSTRAINT `fk_id_lin_comp` FOREIGN KEY (`fk_id_lin_comp`) REFERENCES `lineas_compras` (`pk_id_lin_comp`),
  CONSTRAINT `fk_id_mdp_comp` FOREIGN KEY (`fk_id_mdp_comp`) REFERENCES `metodos_de_pagos` (`pk_id_mdp`),
  CONSTRAINT `fk_id_prov` FOREIGN KEY (`fk_id_prov`) REFERENCES `proveedores` (`pk_id_prov`)
);

CREATE TABLE `facturas_ventas` (
  `pk_id_fac_vent` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `descu` double NOT NULL,
  `precio_unitario` double NOT NULL,
  `subtotal` double NOT NULL,
  `total` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fechaVenta` date NOT NULL,
  `fechaEntrega` date NOT NULL,
  `monto_envio` double NOT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_lin_vent` int(11) DEFAULT NULL,
  `fk_id_mdp_vent` int(11) DEFAULT NULL,
  `fk_id_cli` int(11) DEFAULT NULL,
  `fk_id_tipo_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_fac_vent`),
  KEY `fk_id_lin_vent` (`fk_id_lin_vent`),
  KEY `fk_id_mdp_vent` (`fk_id_mdp_vent`),
  KEY `fk_id_cli` (`fk_id_cli`),
  KEY `fk_id_tipo_cliente` (`fk_id_tipo_cliente`),
  CONSTRAINT `fk_id_lin_vent` FOREIGN KEY (`fk_id_lin_vent`) REFERENCES `lineas_ventas` (`pk_id_lin_vent`),
  CONSTRAINT `fk_id_mdp_vent` FOREIGN KEY (`fk_id_mdp_vent`) REFERENCES `metodos_de_pagos` (`pk_id_mdp`),
  CONSTRAINT `fk_id_cli` FOREIGN KEY (`fk_id_cli`) REFERENCES `clientes` (`pk_id_cli`),
  CONSTRAINT `fk_id_tipo_cliente` FOREIGN KEY (`fk_id_tipo_cliente`) REFERENCES `tipos_clientes` (`pk_id_tipo_cliente`)
);

CREATE TABLE `registros_compras` (
  `pk_id_reg_comp` int(11) NOT NULL AUTO_INCREMENT,
  `entregado` tinyint(1) DEFAULT NULL,
  `perdida` tinyint(1) DEFAULT NULL,
  `reanudada` tinyint(1) DEFAULT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_fac_comp` int(11) DEFAULT NULL,
  `fk_id_us_rv` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_reg_comp`),
  KEY `fk_id_fac_comp` (`fk_id_fac_comp`),
  KEY `fk_id_us_rv` (`fk_id_us_rv`),
  CONSTRAINT `fk_id_fac_comp` FOREIGN KEY (`fk_id_fac_comp`) REFERENCES `facturas_compras` (`pk_id_fac_comp`),
  CONSTRAINT `fk_id_us_rv` FOREIGN KEY (`fk_id_us_rv`) REFERENCES `usuarios` (`pk_id_us`)
);

CREATE TABLE `registros_ventas` (
  `pk_id_reg_vent` int(11) NOT NULL AUTO_INCREMENT,
  `entregado` tinyint(1) DEFAULT NULL,
  `perdida` tinyint(1) DEFAULT NULL,
  `reanudada` tinyint(1) DEFAULT NULL,
  `hab` tinyint(1) DEFAULT NULL,
  `fk_id_fac_vent` int(11) DEFAULT NULL,
  `fk_id_us_rc` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id_reg_vent`),
  KEY `fk_id_fac_vent` (`fk_id_fac_vent`),
  KEY `fk_id_us_rc` (`fk_id_us_rc`),
  CONSTRAINT `fk_id_fac_vent` FOREIGN KEY (`fk_id_fac_vent`) REFERENCES `facturas_ventas` (`pk_id_fac_vent`),
  CONSTRAINT `fk_id_us_rc` FOREIGN KEY (`fk_id_us_rc`) REFERENCES `usuarios` (`pk_id_us`)
);

