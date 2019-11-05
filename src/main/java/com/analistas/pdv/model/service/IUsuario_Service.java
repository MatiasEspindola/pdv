/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entity.Usuario;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IUsuario_Service {

    public List<Usuario> findAll();

    public Usuario findById(Integer id);

    public void save(Usuario usuario);

    public void delete(Usuario usuario);

}
