/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ICiudad_Dao;
import com.analistas.pdv.model.dao.IProvincia_Dao;
import com.analistas.pdv.model.entities.Ciudad;
import com.analistas.pdv.model.entities.Provincia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Ciudad_Service_Impl implements ICiudad_Service {

    @Autowired
    private ICiudad_Dao ciudadDao;
    
    @Autowired
    private IProvincia_Dao provinciaDao;

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadDao.findAll();
    }

    @Override
    public Ciudad buscarCiudadPorId(Integer id) {
        return ciudadDao.findById(id).orElse(null);
    }

    @Override
    public List<Ciudad> buscarCiudadPorNombre(String term) {
        return ciudadDao.buscarPorNombre(term);
    }

    @Override
    public List<Provincia> listarProvincias() {
        return provinciaDao.findAll();
    }

    @Override
    public Provincia buscarProvinciaPorId(Integer id) {
        return provinciaDao.findById(id).orElse(null);
    }
    
}
