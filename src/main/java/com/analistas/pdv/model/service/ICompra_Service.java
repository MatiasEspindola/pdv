/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entity.Compra;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ICompra_Service {
    
    public List<Compra> findAll();

    public Compra findById(Integer id);

    public void save(Compra compra);

    public void delete(Compra compra);
    
}
