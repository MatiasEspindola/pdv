 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Ciudad;
import com.analistas.pdv.model.entities.Provincia;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ICiudad_Service {

    public List<Ciudad> listarCiudades();

    public Ciudad buscarCiudadPorId(Integer id);

    public List<Ciudad> buscarCiudadPorNombre(String term);
    
    public List<Provincia> listarProvincias();

    public Provincia buscarProvinciaPorId(Integer id);

}
