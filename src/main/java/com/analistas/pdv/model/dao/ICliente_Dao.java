/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entity.Ciudad;
import com.analistas.pdv.model.entity.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author nahuel
 */
public interface ICliente_Dao extends JpaRepository<Cliente, Integer>{
    
    @Modifying
    @Query("update Cliente c set c.ciudad=?1")
    public void uploadCliente(Ciudad ciudad);
    
}
