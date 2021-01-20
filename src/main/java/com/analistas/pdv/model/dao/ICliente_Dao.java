/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ICliente_Dao extends JpaRepository<Cliente, Integer>{
    
    @Query("select c from Cliente c where c.hab = true")
    public List<Cliente> buscarHabilitados();
    
    @Query("select c from Cliente c where c.hab = false")
    public List<Cliente> buscarDeshabilitados();
    
    @Query("Select c from Cliente c where ((c.persona.nombre like %?1% or c.persona.apellido like %?1%) or c.persona.doc like %?1%) and c.hab = true")
    public List<Cliente> buscarCliente(String term);
    
    @Query("Select c from Cliente c where c.persona = ?1")
    public Cliente buscarPersonaCliente(Persona persona);
    
}
