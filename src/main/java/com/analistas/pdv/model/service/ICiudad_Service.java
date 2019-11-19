 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Ciudad;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ICiudad_Service {

    public List<Ciudad> findAll();

    public Ciudad findById(Integer id);

    public void save(Ciudad ciudad);

    public void delete(Ciudad ciudad);

    public List<Ciudad> buscarPorNombre(String term);
    
    

}
