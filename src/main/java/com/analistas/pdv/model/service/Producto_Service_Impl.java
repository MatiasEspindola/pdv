/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ICategoria_Dao;
import com.analistas.pdv.model.dao.IMarca_Dao;
import com.analistas.pdv.model.dao.IMetodoPago_Dao;
import com.analistas.pdv.model.dao.IProducto_Dao;
import com.analistas.pdv.model.entities.Categoria;
import com.analistas.pdv.model.entities.Marca;
import com.analistas.pdv.model.entities.Metodo_De_Pago;
import com.analistas.pdv.model.entities.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author matia
 */
@Service
public class Producto_Service_Impl implements IProducto_Service {

    @Autowired
    private IProducto_Dao productoDao;

    @Autowired
    private IMarca_Dao marcaDao;

    @Autowired
    private ICategoria_Dao categoriaDao;

    @Autowired
    private IMetodoPago_Dao metodoPagoDao;

    @Override
    public List<Producto> listarProductos() {
        return productoDao.findAll();
    }

    @Override
    public List<Producto> buscarProductosHabilitados() {
        return productoDao.buscarHabilitados();
    }

    @Override
    public List<Producto> buscarProductosDeshabilitados() {
        return productoDao.buscarDeshabilitados();
    }

    @Override
    public Producto buscarProductoPorId(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public List<Producto> buscarProductoPorNombre(String term) {
        return productoDao.buscarPorNombre(term);
    }

    @Override
    public List<Producto> buscarProductoPorNombreYStock(String term) {
        return productoDao.buscarPorNombreYStock(term);
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    public void borrarProducto(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    public List<Categoria> listarCategoriasProductos() {
        return categoriaDao.findAll();
    }

    @Override
    public Categoria buscarCategoriaProductoPorId(Integer id) {
        return categoriaDao.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> buscarCategoriaProductoPorNombre(String term) {
        return categoriaDao.buscarPorNombre(term);
    }

    @Override
    public void guardarCategoriaProducto(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    public void borrarCategoriaProducto(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    @Override
    public List<Marca> listarMarcasProductos() {
        return marcaDao.findAll();
    }

    @Override
    public Marca buscarMarcaProductoPorId(Integer id) {
        return marcaDao.findById(id).orElse(null);
    }
    
    @Override
    public List<Marca> buscarMarcaProductoPorNombre(String term) {
        return marcaDao.buscarPorNombre(term);
    }

    @Override
    public void guardarMarcaProducto(Marca marca) {
        marcaDao.save(marca);
    }

    @Override
    public void borrarMarcaProducto(Marca marca) {
        marcaDao.delete(marca);
    }

    @Override
    public List<Metodo_De_Pago> listarMetodosDePagoProductos() {
        return metodoPagoDao.findAll();
    }

    @Override
    public Metodo_De_Pago buscarMetodoDePagoProductoPorId(int id) {
        return metodoPagoDao.findById(id).orElse(null);
    }

}
