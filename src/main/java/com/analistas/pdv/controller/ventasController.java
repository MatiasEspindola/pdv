/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;
import com.analistas.pdv.model.entity.Compra;
import com.analistas.pdv.model.entity.Metodo_De_Pago;
import com.analistas.pdv.model.entity.Producto;
import com.analistas.pdv.model.entity.Venta;
import com.analistas.pdv.model.service.MetodoPago_Service_Impl;
import com.analistas.pdv.model.service.Producto_Service_Impl;
import com.analistas.pdv.model.service.Venta_Service_Impl;
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
@SessionAttributes("venta")
@RequestMapping("/ventas")
public class ventasController {

    @Autowired
    private Producto_Service_Impl productoServ;

    @Autowired
    private Venta_Service_Impl ventaServ;

    @Autowired
    private MetodoPago_Service_Impl metododepagoServ;

    @GetMapping("/ver_ventas")
    public String ventas(Map m) {

        List<Venta> ventas = ventaServ.findAll();

        m.put("titulo", "Ver Ventas");
        m.put("ventas", ventas);
        return "ventas/ver_ventas";
    }

    @GetMapping(value = "/cargar_producto/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Producto> cargarProducto(@PathVariable String term) {
        return productoServ.buscarPorNombre(term);
    }
    
    @GetMapping("/detalles/{id}")
    public String detalles_venta(Map m, @PathVariable(value = "id") int id) {

        Venta venta = ventaServ.findById(id);

        m.put("titulo", "Detalles");
        m.put("venta", venta);
        return "ventas/detalles";
    }

    //CRUD
    @GetMapping("/registrar")
    public String registrar(Map m) {

        List<Metodo_De_Pago> metodosdepago = metododepagoServ.findAll();
        Venta venta = new Venta();

        String inf = "Buscar Producto";

        m.put("inf", inf);

        m.put("titulo", "Registrar Venta");
        m.put("venta", venta);
        m.put("metodosdepago", metodosdepago);
        return "ventas/registrar";
    }

    @GetMapping(value = "/editar/{id}")
    public String editar(Map m, @PathVariable(value = "id") int id) {
        Venta venta = null;
        List<Metodo_De_Pago> metodosdepago = metododepagoServ.findAll();

        if (id > 0) {
            venta = ventaServ.findById(id);
            if (venta == null) {
                return "redirect:/compras/ver_compras";
            }

            String inf = venta.getProducto().getNombre();

            m.put("inf", inf);
        } else {
            return "redirect:/ventas/ver_ventas";
        }

        m.put("titulo", "Editar Venta");
        m.put("venta", venta);
        m.put("metodosdepago", metodosdepago);
        return "ventas/registrar";
    }

    @PostMapping("/registrar")
    public String guardar(@Valid Venta venta, Map m) {
        ventaServ.save(venta);
        return "redirect:/ventas/ver_ventas";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id) {

        if (id > 0) {
            Venta venta = ventaServ.findById(id);
            ventaServ.delete(venta);
        }

        return "redirect:/ventas/ver_venta";
    }

}