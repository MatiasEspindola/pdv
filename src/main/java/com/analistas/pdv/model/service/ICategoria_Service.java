/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entity.Categoria;
import java.util.List;

/**
 *
 * @author nahue
 */
public interface ICategoria_Service {

    public List<Categoria> findAll();

    public Categoria findById(Integer id);

    public void save(Categoria categoria);

    public void delete(Categoria categoria);
    
    public List<Categoria> buscarPorNombre(String term);
}
