/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IMetodoPago_Dao;
import com.analistas.pdv.model.entity.Metodo_De_Pago;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class MetodoPago_Service_Impl implements IMetodoPago_Service{

    @Autowired
    private IMetodoPago_Dao metodopagoDao;
    
    @Override
    @Transactional(readOnly = true)
    public Metodo_De_Pago findById(int id) {
        return metodopagoDao.findById(id).orElse(null);
    }

    @Override
    public List<Metodo_De_Pago> findAll() {
        return metodopagoDao.findAll();
    }
    
}
