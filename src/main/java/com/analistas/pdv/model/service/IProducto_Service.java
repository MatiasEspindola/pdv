/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Categoria;
import com.analistas.pdv.model.entities.Marca;
import com.analistas.pdv.model.entities.Metodo_De_Pago;
import com.analistas.pdv.model.entities.Producto;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IProducto_Service {

    public List<Producto> listarProductos();

    public List<Producto> buscarProductosHabilitados();

    public List<Producto> buscarProductosDeshabilitados();

    public Producto buscarProductoPorId(Integer id);

    public List<Producto> buscarProductoPorNombre(String term);
    
    public List<Producto> buscarProductoPorNombreYStock(String term);

    public void guardarProducto(Producto producto);

    public void borrarProducto(Producto producto);
    
    public List<Categoria> listarCategoriasProductos();

    public Categoria buscarCategoriaProductoPorId(Integer id);

    public void guardarCategoriaProducto(Categoria categoria);

    public void borrarCategoriaProducto(Categoria categoria);
    
    public List<Categoria> buscarCategoriaProductoPorNombre(String term);
    
    public List<Marca> listarMarcasProductos();

    public Marca buscarMarcaProductoPorId(Integer id);

    public void guardarMarcaProducto(Marca marca);

    public void borrarMarcaProducto(Marca marca);
    
    public List<Marca> buscarMarcaProductoPorNombre(String term);
    
    public List<Metodo_De_Pago> listarMetodosDePagoProductos();
    
    public Metodo_De_Pago buscarMetodoDePagoProductoPorId(int id);

}
