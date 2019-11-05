/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entity.Categoria;
import com.analistas.pdv.model.entity.Marca;
import com.analistas.pdv.model.entity.Metodo_De_Pago;
import com.analistas.pdv.model.entity.Producto;
import com.analistas.pdv.model.entity.Proveedor;
import com.analistas.pdv.model.service.ICategoria_Service;
import com.analistas.pdv.model.service.IMarca_Service;
import com.analistas.pdv.model.service.IMetodoPago_Service;
import com.analistas.pdv.model.service.IProducto_Service;
import com.analistas.pdv.model.service.IProveedor_Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static boolean editar;

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
    public String guardar(@Valid Producto producto, Map m, @RequestParam("file") MultipartFile foto) {

        if (!foto.isEmpty()) {
            Path directorioRecursos = Paths.get("src//main/resources//static/uploads/productos");
            String rootPath = directorioRecursos.toFile().getAbsolutePath();
            try {
                byte[] bytes = foto.getBytes();
                Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                if (!Files.exists(directorioRecursos)) {
                    Files.createDirectories(directorioRecursos);
                } else {
                    Files.write(rutaCompleta, bytes);
                }
                //mensaje

                producto.setFoto(foto.getOriginalFilename());
            } catch (IOException ex) {
                Logger.getLogger(productosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //mensaje
        }

        productoServ.save(producto);
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
