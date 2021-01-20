/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Categoria;
import com.analistas.pdv.model.entities.Marca;
import com.analistas.pdv.model.entities.Producto;
import com.analistas.pdv.model.entities.Proveedor;
import com.analistas.pdv.model.service.IProducto_Service;
import com.analistas.pdv.model.service.IProveedor_Service;
import com.analistas.pdv.model.service.IUploadFile_Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author matia
 */
@Controller
@SessionAttributes("producto")
@RequestMapping("/productos")
public class productosController {

    @Autowired
    private IProveedor_Service proveedorService;

    @Autowired
    private IProducto_Service productoService;

    @Autowired
    private IUploadFile_Service upl;

    private static String filtrar;

    private List<Producto> productos;

    private Producto producto;

    private boolean editar;

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

    @GetMapping("/ver/habilitacion/{id}")
    public String habilitacion(@PathVariable int id) {

        if (productoService.buscarProductoPorId(id).isHab()) {
            productoService.buscarProductoPorId(id).setHab(false);
        } else {
            productoService.buscarProductoPorId(id).setHab(true);
        }

        productoService.guardarProducto(productoService.buscarProductoPorId(id));

        return "redirect:/productos/ver";
    }

    @GetMapping("/ver/{filtrar}")
    public String filtrados(@PathVariable String filtrar) {

        this.filtrar = filtrar;

        return "redirect:/productos/ver";
    }

    @GetMapping("/ver")
    public String ver(Map m) {

        m.put("titulo", "Ver Productos");

        if (filtrar == null || filtrar.equals("listar_todo")) {
            productos = productoService.listarProductos();
        } else if (filtrar.equals("productos_habilitados")) {
            productos = productoService.buscarProductosHabilitados();
        } else if (filtrar.equals("productos_deshabilitados")) {
            productos = productoService.buscarProductosDeshabilitados();
        }

        m.put("productos", productos);

        return "productos/ver";
    }

    @GetMapping(value = "/cargar_categoria/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Categoria> cargarCategoria(@PathVariable String term) {
        return productoService.buscarCategoriaProductoPorNombre(term);
    }

    @GetMapping(value = "/cargar_marca/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Marca> cargarMarca(@PathVariable String term) {
        return productoService.buscarMarcaProductoPorNombre(term);
    }

    @GetMapping("/formulario")
    public String agregar(Map m) {

        producto = new Producto();

        editar = false;

        m.put("editar", editar);
        m.put("producto", producto);

        m.put("titulo", "Añadir Producto");

        return "productos/formulario";
    }

    @GetMapping("/formulario/{id}")
    public String modificar(@PathVariable int id, Map m) {

        editar = true;

        producto = productoService.buscarProductoPorId(id);

        m.put("editar", editar);
        m.put("producto", producto);
        m.put("categoria", producto.getCategoria().getCategoria());
        m.put("marca", producto.getMarca().getMarca());

        m.put("titulo", "Editar Datos del Producto");

        return "productos/formulario";
    }

    @PostMapping("/formulario")
    public String guardar(@Valid Producto producto, @RequestParam("file") MultipartFile foto, RedirectAttributes flash) {

        if (comprobarDuplicacionDeDatos(producto, editar)) {
            //Mensaje de error
            flash.addFlashAttribute("duplicacion", "Ya se encuentra registrado el producto "
                    + producto.getNombre() + "(" + producto.getModelo() + ")");
            if (editar) {
                return "redirect:/productos/formulario/" + producto.getId();
            } else {
                return "redirect:/productos/formulario";
            }

        } else {

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

            productoService.guardarProducto(producto);

        }

        return "redirect:/productos/ver";
    }

    public boolean comprobarDuplicacionDeDatos(Producto producto, boolean editar) {
        productos = productoService.listarProductos();

        if (!productos.isEmpty()) {
            if (editar) {
                for (Producto p : productos) {
                    if (!(p.getId() == producto.getId())) {
                        if (p.getNombre().equals(producto.getNombre()) && p.getModelo().equals(producto.getModelo())) {
                            System.out.println("¡Duplicacion de datos en editar!");
                            return true;
                        }
                    }
                }
            } else {
                for (Producto p : productos) {
                    if (p.getNombre().equals(producto.getNombre()) && p.getModelo().equals(producto.getModelo())) {
                        System.out.println("¡Duplicacion de datos en agregar!");
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
