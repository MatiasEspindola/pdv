/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Rol;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IRol_Service {

    public List<Rol> findAll();

    public Rol findById(Integer id);

    public void save(Rol rol);

    public void delete(Rol rol);

}
