/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entity.Compra;
import com.analistas.pdv.model.entity.Metodo_De_Pago;
import com.analistas.pdv.model.entity.Producto;
import com.analistas.pdv.model.service.Compra_Service_Impl;
import com.analistas.pdv.model.service.MetodoPago_Service_Impl;
import com.analistas.pdv.model.service.Producto_Service_Impl;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author matia
 */
@Controller
@SessionAttributes("compra")
@RequestMapping("/compras")
public class comprasController {

    @Autowired
    private Producto_Service_Impl productoServ;

    @Autowired
    private Compra_Service_Impl compraServ;

    @Autowired
    private MetodoPago_Service_Impl metododepagoServ;

    @GetMapping("/ver_compras")
    public String compras(Map m) {

        List<Compra> compras = compraServ.findAll();

        m.put("titulo", "Ver Compras");
        m.put("compras", compras);
        return "compras/ver_compras";
    }

    @GetMapping(value = "/cargar_producto/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Producto> cargarProducto(@PathVariable String term) {
        return productoServ.buscarPorNombre(term);
    }
    
    @GetMapping("/detalles/{id}")
    public String detalles_compra(Map m, @PathVariable(value = "id") int id) {

        Compra compra = compraServ.findById(id);

        m.put("titulo", "Detalles");
        m.put("compra", compra);
        return "compras/detalles";
    }

    //CRUD
    @GetMapping("/registrar")
    public String registrar(Map m) {

        List<Metodo_De_Pago> metodosdepago = metododepagoServ.findAll();
        Compra compra = new Compra();

        String inf = "Buscar Producto";

        m.put("inf", inf);

        m.put("titulo", "Registrar Compra");
        m.put("compra", compra);
        m.put("metodosdepago", metodosdepago);
        return "compras/registrar";
    }

    @GetMapping(value = "/editar/{id}")
    public String editar(Map m, @PathVariable(value = "id") int id) {
        Compra compra = null;
        List<Metodo_De_Pago> metodosdepago = metododepagoServ.findAll();

        if (id > 0) {
            compra = compraServ.findById(id);
            if (compra == null) {
                return "redirect:/compras/ver_compras";
            }

            String inf = compra.getProducto().getNombre();

            m.put("inf", inf);
        } else {
            return "redirect:/compras/ver_compras";
        }

        m.put("titulo", "Editar Compra");
        m.put("compra", compra);
        m.put("metodosdepago", metodosdepago);
        return "compras/registrar";
    }

    @PostMapping("/registrar")
    public String guardar(@Valid Compra compra, Map m) {
        compraServ.save(compra);
        return "redirect:/compras/ver_compras";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id) {

        if (id > 0) {
            Compra compra = compraServ.findById(id);
            compraServ.delete(compra);
        }

        return "redirect:/compras/ver_compras";
    }

}
