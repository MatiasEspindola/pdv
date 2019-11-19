/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Provincia;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IProvincia_Service {
    
    public List<Provincia> findAll();

    public Provincia findById(Integer id);

    public void save(Provincia provincia);

    public void delete(Provincia provincia);
    
}
