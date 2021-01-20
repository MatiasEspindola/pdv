/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ICategoria_Dao extends JpaRepository<Categoria, Integer> {

    @Query("Select c from Categoria c where c.categoria like %?1%")
    public List<Categoria> buscarPorNombre(String term);

}
