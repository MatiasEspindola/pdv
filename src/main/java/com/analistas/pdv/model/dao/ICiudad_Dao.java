/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Ciudad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface ICiudad_Dao extends JpaRepository<Ciudad, Integer>{
    
    @Query("Select c from Ciudad c where c.ciudad like %?1%")
    public List<Ciudad> buscarPorNombre(String term);
    
}
