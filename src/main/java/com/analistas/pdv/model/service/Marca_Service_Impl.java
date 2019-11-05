/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IMarca_Dao;
import com.analistas.pdv.model.entity.Marca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahue
 */
@Service
public class Marca_Service_Impl implements IMarca_Service {

    @Autowired
    private IMarca_Dao marcaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Marca> findAll() {
        return marcaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Marca findById(Integer id) {
        return marcaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Marca marca) {
        marcaDao.save(marca);
    }

    @Override
    @Transactional
    public void delete(Marca marca) {
        marcaDao.delete(marca);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Marca> buscarPorNombre(String term) {
        return marcaDao.buscarPorNombre(term);
    }

}
