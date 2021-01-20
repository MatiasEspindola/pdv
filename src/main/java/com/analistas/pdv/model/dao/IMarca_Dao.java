/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Marca;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IMarca_Dao extends JpaRepository<Marca, Integer> {

    @Query("Select m from Marca m where m.marca like %?1%")
    public List<Marca> buscarPorNombre(String term);

}
