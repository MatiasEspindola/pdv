/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Persona;
import com.analistas.pdv.model.entities.Telefono_Proveedor;
import com.analistas.pdv.model.entities.Tipodocumento;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IPersona_Service {

    public List<Persona> listarPersonas();

    public Persona buscarPersonaPorId(Integer id);
    
    public void guardarPersona(Persona persona);
    
    public void borrarPersona(Persona persona);
    
    public List<Tipodocumento> listarTiposDocumentos();
    
}
