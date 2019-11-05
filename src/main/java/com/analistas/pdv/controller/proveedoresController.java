/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entity.Ciudad;
import com.analistas.pdv.model.entity.Proveedor;
import com.analistas.pdv.model.service.Ciudad_Service_Impl;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author nahuel
 */
@Controller
@SessionAttributes("proveedor")
@RequestMapping("/proveedores")
public class proveedoresController {

    @Autowired
    private IProveedor_Service proveedorServ;
    
    @Autowired
    private Ciudad_Service_Impl ciudadServ;
    
    private static boolean editar;

    @GetMapping("/ver_proveedores")
    public String proveedores(Map m) {

        List<Proveedor> proveedores = proveedorServ.findAll();

        m.put("titulo", "Ver Proveedores");
        m.put("proveedores", proveedores);
        return "proveedores/ver_proveedores";
    }
    
    @GetMapping(value = "/cargar_ciudad/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Ciudad> cargarCiudadProveedor(@PathVariable String term) {
        return ciudadServ.buscarPorNombre(term);
    }

    // VER FOTO
    /* @GetMapping(value="/ver/{id}")
     public String ver(@PathVariable(value="id") int id, Map<String, Object> model){
     Proveedor proveedor = proveedorServ.findById(id);
        
     model.put("proveedor", proveedor);
     model.put("imagen", "Detalle proveedor: " + proveedor.getNombre());
     return "ver";
     } */
    @GetMapping(value = "/registrar")
    public String registrar(Map m) {
        
        editar = false;
        
        m.put("editar", editar);

        Proveedor proveedor = new Proveedor();

        m.put("inf", "Buscar Ciudad");
        m.put("inf_img", "Buscar Imagen: ");
        m.put("titulo", "Registrar Proveedor");
        m.put("proveedor", proveedor);
        return "proveedores/registrar";

    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable(value = "id") int id, Map m) {
        Proveedor proveedor = null;

        if (id > 0) {
            proveedor = proveedorServ.findById(id);
            if (proveedor == null) {
                return "redirect:/proveedores/ver_proveedores";
            }
            
            editar = true;
            
            m.put("editar", editar);
            m.put("inf", proveedor.getCiudad().getCp() + ", " + proveedor.getCiudad().getCiudad() + ", " + proveedor.getCiudad().getProvincia().getProvincia());
            m.put("inf_img", "Cambiar Imagen: ");

        } else {
            return "redirect:/proveedores/ver_proveedores";
        }

        m.put("titulo", "Editar Proveedor");
        m.put("proveedor", proveedor);
        return "proveedores/registrar";
    }

    @PostMapping("/registrar")
    public String guardar(@Valid Proveedor proveedor, Map m, @RequestParam("file") MultipartFile foto) {
        if (!foto.isEmpty()) {
            Path directorioRecursos = Paths.get("src//main/resources//static/uploads/proveedores");
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

                proveedor.setFoto(foto.getOriginalFilename());
            } catch (IOException ex) {
                Logger.getLogger(clientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //mensaje
        }

        proveedorServ.save(proveedor);
        return "redirect:/proveedores/ver_proveedores";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id, RedirectAttributes mensaje) {

        if (id > 0) {
            Proveedor proveedor = proveedorServ.findById(id);
            proveedorServ.delete(proveedor);
        }

        //mensaje.addFlashAttribute("success", "Proveedor Eliminado con Exito");
        return "redirect:/proveedores/ver_proveedores";
    }

}
