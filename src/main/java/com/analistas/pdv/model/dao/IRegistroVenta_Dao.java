/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Registro_Venta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author matia
 */
public interface IRegistroVenta_Dao extends JpaRepository<Registro_Venta, Integer>{
    
}
