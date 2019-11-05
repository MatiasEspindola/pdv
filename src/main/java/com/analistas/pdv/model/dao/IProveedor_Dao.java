/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entity.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface IProveedor_Dao extends JpaRepository<Proveedor, Integer>{
    
    @Query("Select p from Proveedor p where p.nombre like %?1%")
    public List<Proveedor> buscarPorNombre(String term);
    
}