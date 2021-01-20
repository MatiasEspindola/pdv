/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IFacturaCompra_Dao;
import com.analistas.pdv.model.dao.IRegistroCompra_Dao;
import com.analistas.pdv.model.dao.IitemCompra_Dao;
import com.analistas.pdv.model.entities.Factura_Compra;
import com.analistas.pdv.model.entities.Item_Compra;
import com.analistas.pdv.model.entities.Registro_Compra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class Compra_Service_Impl implements ICompra_Service {

    @Autowired
    private IitemCompra_Dao itemCompraDao;

    @Autowired
    private IFacturaCompra_Dao facturaCompraDao;

    @Autowired
    private IRegistroCompra_Dao registroCompraDao;

    @Override
    @Transactional(readOnly = true)
    public List<Item_Compra> listarItemCompra() {
        return (List<Item_Compra>) itemCompraDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura_Compra> listarFacturaCompra() {
        return (List<Factura_Compra>) facturaCompraDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Registro_Compra> listarRegistroCompra() {
        return (List<Registro_Compra>) registroCompraDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Item_Compra buscarItemCompraPorId(Integer id) {
        return itemCompraDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura_Compra buscarFacturaCompraPorId(Integer id) {
        return facturaCompraDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Registro_Compra buscarRegistroCompraPorId(Integer id) {
        return registroCompraDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarItemCompra(Item_Compra item_compra) {
        itemCompraDao.save(item_compra);
    }
    
    @Override
    @Transactional
    public void guardarFacturaCompra(Factura_Compra factura_compra) {
        facturaCompraDao.save(factura_compra);
    }
    
    @Override
    @Transactional
    public void guardarRegistroCompra(Registro_Compra registro_compra) {
        registroCompraDao.save(registro_compra);
    }

    @Override
    @Transactional
    public void borrarItemCompra(Item_Compra item_compra) {
        itemCompraDao.delete(item_compra);
    }
    
    @Override
    @Transactional
    public void borrarFacturaCompra(Factura_Compra factura_compra) {
        facturaCompraDao.delete(factura_compra);
    }
    
    @Override
    @Transactional
    public void borrarRegistroCompra(Registro_Compra registro_compra) {
        registroCompraDao.delete(registro_compra);
    }

}
