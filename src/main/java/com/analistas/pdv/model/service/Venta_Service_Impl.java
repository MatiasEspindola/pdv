/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.IFacturaVenta_Dao;
import com.analistas.pdv.model.dao.IRegistroVenta_Dao;
import com.analistas.pdv.model.dao.IitemVenta_Dao;
import com.analistas.pdv.model.entities.Factura_Venta;
import com.analistas.pdv.model.entities.Item_Venta;
import com.analistas.pdv.model.entities.Registro_Venta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */

@Service
public class Venta_Service_Impl implements IVenta_Service{
    
    @Autowired
    private IitemVenta_Dao itemVentaDao;

    @Autowired
    private IFacturaVenta_Dao facturaVentaDao;

    @Autowired
    private IRegistroVenta_Dao registroVentaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Item_Venta> listarItemVenta() {
        return (List<Item_Venta>) itemVentaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura_Venta> listarFacturaVenta() {
        return (List<Factura_Venta>) facturaVentaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Registro_Venta> listarRegistroVenta() {
        return (List<Registro_Venta>) registroVentaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Item_Venta buscarItemVentaPorId(Integer id) {
        return itemVentaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura_Venta buscarFacturaVentaPorId(Integer id) {
        return facturaVentaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Registro_Venta buscarRegistroVentaPorId(Integer id) {
        return registroVentaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarItemVenta(Item_Venta item_venta) {
        itemVentaDao.save(item_venta);
    }
    
    @Override
    @Transactional
    public void guardarFacturaVenta(Factura_Venta factura_venta) {
        facturaVentaDao.save(factura_venta);
    }
    
    @Override
    @Transactional
    public void guardarRegistroVenta(Registro_Venta registro_venta) {
        registroVentaDao.save(registro_venta);
    }

    @Override
    @Transactional
    public void borrarItemVenta(Item_Venta item_venta) {
        itemVentaDao.delete(item_venta);
    }
    
    @Override
    @Transactional
    public void borrarFacturaVenta(Factura_Venta factura_venta) {
        facturaVentaDao.delete(factura_venta);
    }
    
    @Override
    @Transactional
    public void borrarRegistroVenta(Registro_Venta registro_venta) {
        registroVentaDao.delete(registro_venta);
    }
    
}
