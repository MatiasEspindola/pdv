/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IUsuario_Dao;
import com.analistas.pdv.model.entities.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */

@Service
public class Usuario_Service_Impl implements IUsuario_Service{
    
    @Autowired
    private IUsuario_Dao usuarioDao;
    
    @Override
    public List<Usuario> findAll() {
       return usuarioDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Integer id){
        return usuarioDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Usuario usuario){
        usuarioDao.save(usuario);
    }
    
    @Override
    @Transactional
    public void delete(Usuario usuario){
        usuarioDao.delete(usuario);
    }
    
}
