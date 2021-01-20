/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Rol;
import com.analistas.pdv.model.entities.Usuario;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IUsuario_Service {

    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuarioPorId(Integer id);

    public void guardarUsuario(Usuario usuario);

    public void borrarUsuario(Usuario usuario);
    
    public List<Rol> listarRolesUsuarios();

    public Rol buscarRolUsuarioPorId(Integer id);

    public void guardarRolUsuario(Rol rol);

    public void borrarRolUsuario(Rol rol);

}
