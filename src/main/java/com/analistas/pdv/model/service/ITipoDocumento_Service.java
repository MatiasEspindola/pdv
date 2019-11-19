/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Tipodocumento;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ITipoDocumento_Service {

    public List<Tipodocumento> findAll();

}
