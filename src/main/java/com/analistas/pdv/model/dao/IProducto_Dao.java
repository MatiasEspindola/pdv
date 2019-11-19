/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author nahuel
 */
public interface IProducto_Dao extends JpaRepository<Producto, Integer>{
    
    @Query("Select p from Producto p where p.nombre like %?1%")
    public List<Producto> buscarPorNombre(String term);
    
}
