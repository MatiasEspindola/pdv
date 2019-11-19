/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ICiudad_Dao;
import com.analistas.pdv.model.entities.Ciudad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class Ciudad_Service_Impl implements ICiudad_Service {

    @Autowired
    private ICiudad_Dao ciudadDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ciudad> findAll() {
        return ciudadDao.findAll();
    }

    // ESTE NO
    @Override
    @Transactional(readOnly = true)
    public List<Ciudad> buscarPorNombre(String term) {
        return ciudadDao.buscarPorNombre(term);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Ciudad findById(Integer id){
        return ciudadDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Ciudad ciudad){
        ciudadDao.save(ciudad);
    }
    
    @Override
    @Transactional
    public void delete(Ciudad ciudad){
        ciudadDao.delete(ciudad);
    }

}
