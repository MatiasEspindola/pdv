/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Factura_Venta;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matia
 */
public interface IFacturaVenta_Dao extends CrudRepository<Factura_Venta, Integer>{
    
}
