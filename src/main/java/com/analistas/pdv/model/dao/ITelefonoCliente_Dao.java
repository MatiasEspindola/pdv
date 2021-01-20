/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Telefono_Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface ITelefonoCliente_Dao extends JpaRepository<Telefono_Cliente, Integer>{
    
    @Query("Select t_c from Telefono_Cliente t_c where t_c.cliente = ?1")
    public Telefono_Cliente buscarTelefonoCliente(Cliente cliente);
    
}
