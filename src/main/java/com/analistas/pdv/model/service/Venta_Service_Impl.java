/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IVenta_Dao;
import com.analistas.pdv.model.entities.Venta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */

@Service
public class Venta_Service_Impl implements IVenta_Service{
    
    @Autowired
    private IVenta_Dao ventaDao;
    
    @Override
    public List<Venta> findAll() {
       return ventaDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Venta findById(Integer id){
        return ventaDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Venta venta){
        ventaDao.save(venta);
    }
    
    @Override
    @Transactional
    public void delete(Venta venta){
        ventaDao.delete(venta);
    }

    @Override
    public Venta findOne(Integer id) {
         return ventaDao.findById(id).orElse(null);
    }
    
}
