/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Categoria;
import com.analistas.pdv.model.entities.Compra;
import com.analistas.pdv.model.entities.Marca;
import com.analistas.pdv.model.entities.Metodo_De_Pago;
import com.analistas.pdv.model.entities.Producto;
import com.analistas.pdv.model.entities.Proveedor;
import com.analistas.pdv.model.service.Compra_Service_Impl;
import com.analistas.pdv.model.service.ICategoria_Service;
import com.analistas.pdv.model.service.IMarca_Service;
import com.analistas.pdv.model.service.IMetodoPago_Service;
import com.analistas.pdv.model.service.IProducto_Service;
import com.analistas.pdv.model.service.IProveedor_Service;
import com.analistas.pdv.model.service.IUploadFile_Service;
import com.analistas.pdv.model.service.MetodoPago_Service_Impl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("producto")
@RequestMapping("/productos")
public class productosController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IProducto_Service productoServ;

    @Autowired
    private IProveedor_Service proveedorServ;

    @Autowired
    private IMarca_Service marcaServ;

    @Autowired
    private ICategoria_Service categoriaServ;

    @Autowired
    private IMetodoPago_Service metodopagoServ;

    @Autowired
    private IUploadFile_Service upl;

    private static boolean editar;

    @Autowired
    private MetodoPago_Service_Impl metododepagoServ;
    
    @Autowired
    private Compra_Service_Impl compraServ;

    @GetMapping("/ver_productos")
    public String productos(Map m) {

        List<Producto> productos = productoServ.findAll();

        m.put("titulo", "Ver Productos");
        m.put("productos", productos);
        return "productos/ver_productos";
    }

    //AUTOCOMPLETE
    @GetMapping(value = "/cargar_marca/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Marca> cargarMarca(@PathVariable String term) {
        return marcaServ.buscarPorNombre(term);
    }

    @GetMapping(value = "/cargar_categoria/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Categoria> cargarCategoria(@PathVariable String term) {
        return categoriaServ.buscarPorNombre(term);
    }

    @GetMapping(value = "/cargar_proveedor/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Proveedor> cargarProveedor(@PathVariable String term) {
        return proveedorServ.buscarPorNombre(term);
    }

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = upl.load(filename);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    @GetMapping("/detalles/{id}")
    public String detalles_producto(Map m, @PathVariable(value = "id") int id) {

        Producto producto = productoServ.findById(id);

        m.put("titulo", "Detalles");
        m.put("producto", producto);
        return "productos/detalles";
    }

    //CRUD
    @GetMapping("/registrar")
    public String registrar(Map m) {

        editar = false;

        m.put("editar", editar);

        List<Metodo_De_Pago> metodosdepago = metodopagoServ.findAll();
        Producto producto = new Producto();

        m.put("inf1", "Buscar Proveedor");
        m.put("inf2", "Buscar Categoria");
        m.put("inf3", "Buscar Marca");
        m.put("inf_img", "Buscar Imagen: ");
        m.put("titulo", "Registrar Producto");
        m.put("producto", producto);
        m.put("metodosdepago", metodosdepago);
        return "productos/registrar";
    }

    @GetMapping(value = "/editar/{id}")
    public String editar(Map m, @PathVariable(value = "id") int id) {
        Producto producto = null;

        if (id > 0) {
            producto = productoServ.findById(id);
            if (producto == null) {
                return "redirect:/clientes/ver_clientes";
            }

            editar = true;

            m.put("editar", editar);
            m.put("inf1", producto.getProveedor().getNombre());
            m.put("inf2", producto.getCategoria().getCategoria());
            m.put("inf3", producto.getMarca().getMarca());
            m.put("inf_img", "Cambiar Imagen: ");

        } else {
            return "redirect:/productos/ver_productos";
        }

        m.put("titulo", "Editar Producto");
        m.put("producto", producto);
        return "productos/registrar";
    }

    @PostMapping("/registrar")
    public String guardar(@Valid Producto producto, Map m, @RequestParam("file") MultipartFile foto,
            @RequestParam("fechaCompra") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaCompra, @RequestParam("fechaEntrega") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaEntrega,
            @RequestParam("cantidad") int cantidad, @RequestParam("montoEnvio") double monto,
            @RequestParam("metodoPago") int metodoPago, @RequestParam("descripcion") String descripcion) {

        if (!foto.isEmpty()) {
            if (producto.getId() > 0 && producto.getFoto() != null
                    && producto.getFoto().length() > 0) {
                upl.delete(producto.getFoto());
            }
            String uniqueFilename = null;

            try {
                uniqueFilename = upl.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            producto.setFoto(uniqueFilename);
        }

        Compra compra = new Compra();
        
        compra.setCantidad(cantidad);
        compra.setFechaCompra(fechaCompra);
        compra.setFechaEntrega(fechaEntrega);
        compra.setMonto_envio(monto);
        compra.setMetodo_de_pago(metodopagoServ.findById(metodoPago));
        compra.setDescripcion(descripcion);
        compra.setSubtotal(producto.getPrecio());
        compra.setTotal(compra.getSubtotal() + compra.getMonto_envio());
        compra.setProducto(producto);

        producto.setStock(cantidad);
        
        compra.setDemorado(false);
        
        if(fechaEntrega.after(fechaCompra)){
            compra.setEn_camino(true);
        }else{
            compra.setEn_camino(false);
        }

        productoServ.save(producto);
        compraServ.save(compra);
        return "redirect:/productos/ver_productos";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id) {

        if (id > 0) {
            Producto producto = productoServ.findById(id);
            productoServ.delete(producto);
        }

        return "redirect:/productos/ver_productos";
    }

}
