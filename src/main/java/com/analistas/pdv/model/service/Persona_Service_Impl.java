/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IPersona_Dao;
import com.analistas.pdv.model.dao.ITipoDocumento_Dao;
import com.analistas.pdv.model.entities.Persona;
import com.analistas.pdv.model.entities.Telefono_Proveedor;
import com.analistas.pdv.model.entities.Tipodocumento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.analistas.pdv.model.dao.ITelefonoCliente_Dao;


/**
 *
 * @author matia
 */
@Service
public class Persona_Service_Impl implements IPersona_Service{
    
    @Autowired
    private IPersona_Dao personaDao;
    
    @Autowired
    private ITipoDocumento_Dao tipoDocumentoDao;

    @Override
    public List<Persona> listarPersonas() {
        return personaDao.findAll();
    }

    @Override
    public Persona buscarPersonaPorId(Integer id) {
        return personaDao.findById(id).orElse(null);
    }

    @Override
    public void guardarPersona(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    public void borrarPersona(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    public List<Tipodocumento> listarTiposDocumentos() {
        return tipoDocumentoDao.findAll();
    }
    
}
