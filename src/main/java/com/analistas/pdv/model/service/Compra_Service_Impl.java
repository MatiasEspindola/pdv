/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ICompra_Dao;
import com.analistas.pdv.model.entities.Compra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */

@Service
public class Compra_Service_Impl implements ICompra_Service{
    
    @Autowired
    private ICompra_Dao compraDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Compra> findAll() {
        return compraDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Compra findById(Integer id){
        return compraDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Compra cliente){
        compraDao.save(cliente);
    }
    
    @Override
    @Transactional
    public void delete(Compra cliente){
        compraDao.delete(cliente);
    }
    
}
