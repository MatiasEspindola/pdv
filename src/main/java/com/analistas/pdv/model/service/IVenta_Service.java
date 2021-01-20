/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Factura_Venta;
import com.analistas.pdv.model.entities.Item_Venta;
import com.analistas.pdv.model.entities.Registro_Venta;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IVenta_Service {

    public List<Item_Venta> listarItemVenta();
    
    public List<Factura_Venta> listarFacturaVenta();
    
    public List<Registro_Venta> listarRegistroVenta();

    public Item_Venta buscarItemVentaPorId(Integer id);
    
    public Factura_Venta buscarFacturaVentaPorId(Integer id);
    
    public Registro_Venta buscarRegistroVentaPorId(Integer id);

    public void guardarItemVenta(Item_Venta item_venta);
    
    public void guardarFacturaVenta(Factura_Venta factura_venta);
    
    public void guardarRegistroVenta(Registro_Venta registro_venta);

    public void borrarItemVenta(Item_Venta item_Venta);
    
    public void borrarFacturaVenta(Factura_Venta factura_venta);
    
    public void borrarRegistroVenta(Registro_Venta registro_venta);

}
