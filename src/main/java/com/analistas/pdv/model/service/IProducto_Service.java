/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entity.Producto;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IProducto_Service {

    public List<Producto> findAll();

    public Producto findById(Integer id);
    
    public List<Producto> buscarPorNombre(String term);

    public void save(Producto producto);

    public void delete(Producto producto);

   

}
