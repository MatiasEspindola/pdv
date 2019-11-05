/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ITipoDocumento_Dao;
import com.analistas.pdv.model.entity.Tipodocumento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class TipoDocumento_Service_Impl implements ITipoDocumento_Service {

    @Autowired
    private ITipoDocumento_Dao tipodocumentoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Tipodocumento> findAll() {
        return tipodocumentoDao.findAll();
    }

}
