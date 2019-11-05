/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IProvincia_Dao;
import com.analistas.pdv.model.entity.Provincia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class Provincia_Service_Impl implements IProvincia_Service{

    @Autowired
    private IProvincia_Dao provinciaDao;
    
    @Override
    public List<Provincia> findAll() {
       return provinciaDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Provincia findById(Integer id){
        return provinciaDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Provincia provincia){
        provinciaDao.save(provincia);
    }
    
    @Override
    @Transactional
    public void delete(Provincia provincia){
        provinciaDao.delete(provincia);
    }
    
}
