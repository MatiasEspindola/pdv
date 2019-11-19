/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IRol_Dao;
import com.analistas.pdv.model.entities.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */

@Service
public class Rol_Service_Impl implements IRol_Service{
    
    @Autowired
    private IRol_Dao rolDao;
    
    @Override
    public List<Rol> findAll() {
       return rolDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Rol findById(Integer id){
        return rolDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Rol rol){
        rolDao.save(rol);
    }
    
    @Override
    @Transactional
    public void delete(Rol rol){
        rolDao.delete(rol);
    }
    
}
