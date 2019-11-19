/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Venta;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IVenta_Service {

    public List<Venta> findAll();

    public Venta findById(Integer id);

    public void save(Venta venta);

    public void delete(Venta Venta);

    //nahuelProgramador
    public Venta findOne(Integer id);

}
