/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IProveedor_Dao;
import com.analistas.pdv.model.entities.Proveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Proveedor_Service_Impl implements IProveedor_Service {

    @Autowired
    private IProveedor_Dao proveedorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> findAll() {
        return proveedorDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor findById(Integer id) {
        return proveedorDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Proveedor proveedor) {
        proveedorDao.save(proveedor);
    }

    @Override
    @Transactional
    public void delete(Proveedor proveedor) {
        proveedorDao.delete(proveedor);
    }

    @Override
    public Proveedor findOne(Integer id) {
        return proveedorDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> buscarPorNombre(String term) {
        return proveedorDao.buscarPorNombre(term);
    }

}
