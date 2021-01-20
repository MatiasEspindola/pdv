/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IRol_Dao;
import com.analistas.pdv.model.dao.IUsuario_Dao;
import com.analistas.pdv.model.entities.Rol;
import com.analistas.pdv.model.entities.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author matia
 */
@Service
public class Usuario_Service_Impl implements IUsuario_Service {

    @Autowired
    private IUsuario_Dao usuarioDao;

    @Autowired
    private IRol_Dao rolDao;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public void borrarUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public List<Rol> listarRolesUsuarios() {
        return rolDao.findAll();
    }

    @Override
    public Rol buscarRolUsuarioPorId(Integer id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    public void guardarRolUsuario(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    public void borrarRolUsuario(Rol rol) {
        rolDao.delete(rol);
    }

}
