/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IProducto_Dao;
import com.analistas.pdv.model.entity.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class Producto_Service_Impl implements IProducto_Service {

    @Autowired
    private IProducto_Dao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String term) {
        return productoDao.buscarPorNombre(term);
    }

}
