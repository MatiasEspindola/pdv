select * from clientes order by pk_id_cli asc;

select * from tipos_documentos;

select * from proveedores;

select * from productos;

select * from detalles_compras;

select * from compras;
 
select * from ventas;

select * from tickets;

delete from ventas where pk_id_ven = 1;

select * from ciudades where fk_id_provincia = 2;

select * from telefonos_clientes;

select * from categorias;

select * from personas;

select * from facturas_ventas;

delete from personas where pk_id_per = 3; 