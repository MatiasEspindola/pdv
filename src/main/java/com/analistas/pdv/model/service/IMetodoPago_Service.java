/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entity.Metodo_De_Pago;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IMetodoPago_Service {

    public List<Metodo_De_Pago> findAll();
    
    public Metodo_De_Pago findById(int id);

}
