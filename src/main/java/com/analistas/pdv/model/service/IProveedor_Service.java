/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Proveedor;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IProveedor_Service {

    public List<Proveedor> findAll();

    public List<Proveedor> buscarHabilitados();

    public List<Proveedor> buscarDeshabilitados();

    public Proveedor findById(Integer id);

    public void save(Proveedor proveedor);

    public void delete(Proveedor proveedor);

    public Proveedor findOne(Integer id);

    public List<Proveedor> buscarPorNombre(String term);

}
