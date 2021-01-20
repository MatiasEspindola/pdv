/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Factura_Compra;
import com.analistas.pdv.model.entities.Item_Compra;
import com.analistas.pdv.model.entities.Registro_Compra;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ICompra_Service {
    
    public List<Item_Compra> listarItemCompra();
    
    public List<Factura_Compra> listarFacturaCompra();
    
    public List<Registro_Compra> listarRegistroCompra();

    public Item_Compra buscarItemCompraPorId(Integer id);
    
    public Factura_Compra buscarFacturaCompraPorId(Integer id);
    
    public Registro_Compra buscarRegistroCompraPorId(Integer id);

    public void guardarItemCompra(Item_Compra item_compra);
    
    public void guardarFacturaCompra(Factura_Compra factura_compra);
    
    public void guardarRegistroCompra(Registro_Compra registro_compra);

    public void borrarItemCompra(Item_Compra item_compra);
    
    public void borrarFacturaCompra(Factura_Compra factura_compra);
    
    public void borrarRegistroCompra(Registro_Compra registro_compra);
    
}
