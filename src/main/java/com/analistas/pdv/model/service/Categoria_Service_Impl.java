/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ICategoria_Dao;
import com.analistas.pdv.model.entities.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nahue
 */
@Service
public class Categoria_Service_Impl implements ICategoria_Service {

    @Autowired
    private ICategoria_Dao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findById(Integer id) {
        return categoriaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarPorNombre(String term) {
        return categoriaDao.buscarPorNombre(term);
    }

}
