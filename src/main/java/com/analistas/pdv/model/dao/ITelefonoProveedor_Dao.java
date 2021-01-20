/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Proveedor;
import com.analistas.pdv.model.entities.Telefono_Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface ITelefonoProveedor_Dao extends JpaRepository<Telefono_Proveedor, Integer>{
    
    @Query("Select t_p from Telefono_Proveedor t_p where t_p.proveedor = ?1")
    public Telefono_Proveedor buscarTelefonoProveedor(Proveedor proveedor);
    
}
