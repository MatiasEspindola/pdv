/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Ciudad;
import com.analistas.pdv.model.entities.Proveedor;
import com.analistas.pdv.model.service.Ciudad_Service_Impl;
import com.analistas.pdv.model.service.IProveedor_Service;
import com.analistas.pdv.model.service.IUploadFile_Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
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
 * @author nahuel
 */
@Controller
@SessionAttributes("proveedor")
@RequestMapping("/proveedores")
public class proveedoresController {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUploadFile_Service upl;

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
    public String detalles_proveedor(Map m, @PathVariable(value = "id") int id) {

        Proveedor proveedor = proveedorServ.findById(id);

        m.put("titulo", "Detalles");
        m.put("proveedor", proveedor);

        return "proveedores/detalles";
    }

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
    public String guardar(@Valid Proveedor proveedor, Map m, @RequestParam("file") MultipartFile foto, RedirectAttributes flash) {

        if (!foto.isEmpty()) {
            if (proveedor.getId() > 0 && proveedor.getFoto() != null
                    && proveedor.getFoto().length() > 0) {
                upl.delete(proveedor.getFoto());
            }
            String uniqueFilename = null;

            try {
                uniqueFilename = upl.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            proveedor.setFoto(uniqueFilename);
        }

        List<Proveedor> proveedores = proveedorServ.findAll();

        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedor.getNombre().equals(proveedores.get(i).getNombre()) && editar == false) {
                if (proveedor.getCiudad().equals(proveedores.get(i).getCiudad())) {
                    flash.addFlashAttribute("existente", "¡El proveedor " + proveedor.getNombre() + " - " + proveedor.getCiudad().getCiudad() + " ya existe!");
                    return "redirect:/proveedores/ver_proveedores";
                }
            }
        }

        if (editar) {
            flash.addFlashAttribute("editar", "¡Datos modificados con éxito!");
        } else {
            flash.addFlashAttribute("nuevo", "¡Proveedor agregado con éxito!");
        }

        proveedorServ.save(proveedor);
        return "redirect:/proveedores/ver_proveedores";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id, RedirectAttributes mensaje, RedirectAttributes flash) {

        if (id > 0) {
            
            flash.addFlashAttribute("eliminar", "Se ha eliminado con éxito");
            
            Proveedor proveedor = proveedorServ.findById(id);
            proveedorServ.delete(proveedor);
        }

        //mensaje.addFlashAttribute("success", "Proveedor Eliminado con Exito");
        return "redirect:/proveedores/ver_proveedores";
    }

}
