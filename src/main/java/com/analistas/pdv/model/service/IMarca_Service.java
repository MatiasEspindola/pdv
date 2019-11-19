/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Marca;
import java.util.List;

/**
 *
 * @author nahue
 */
public interface IMarca_Service {

    public List<Marca> findAll();

    public Marca findById(Integer id);

    public void save(Marca marca);

    public void delete(Marca marca);

    public List<Marca> buscarPorNombre(String term);
}
