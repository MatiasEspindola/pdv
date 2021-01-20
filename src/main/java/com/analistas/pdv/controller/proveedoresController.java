/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Ciudad;
import com.analistas.pdv.model.entities.Proveedor;
import com.analistas.pdv.model.entities.Telefono_Proveedor;
import com.analistas.pdv.model.service.ICiudad_Service;
import com.analistas.pdv.model.service.IProveedor_Service;
import com.analistas.pdv.model.service.ITelefono_Service;
import com.analistas.pdv.model.service.IUploadFile_Service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
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
@SessionAttributes("proveedor")
@RequestMapping("/proveedores")
public class proveedoresController {

    @Autowired
    private IProveedor_Service proveedorService;

    @Autowired
    private ITelefono_Service telefonoService;

    @Autowired
    private ICiudad_Service ciudadService;

    @Autowired
    private IUploadFile_Service upl;

    private Telefono_Proveedor telefono_proveedor;

    private List<Proveedor> proveedores;

    private List<Telefono_Proveedor> telefonos_proveedores;

    private Proveedor proveedor;

    private Date fecha;

    private boolean editar;

    private static String filtrar;

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

        if (proveedorService.findById(id).isHab()) {
            proveedorService.findById(id).setHab(false);
        } else {
            proveedorService.findById(id).setHab(true);
        }

        proveedorService.save(proveedorService.findById(id));

        return "redirect:/proveedores/ver";
    }

    @GetMapping("/ver/{filtrar}")
    public String filtrados(@PathVariable String filtrar) {

        this.filtrar = filtrar;

        return "redirect:/proveedores/ver";
    }

    @GetMapping("/ver")
    public String ver(Map m) {

        m.put("titulo", "Ver Proveedores");

        if (filtrar == null || filtrar.equals("listar_todo")) {
            proveedores = proveedorService.findAll();
        } else if (filtrar.equals("proveedores_habilitados")) {
            proveedores = proveedorService.buscarHabilitados();
        } else if (filtrar.equals("proveedores_deshabilitados")) {
            proveedores = proveedorService.buscarDeshabilitados();
        }

        for (Proveedor proveedor : proveedores) {
            telefono_proveedor = telefonoService.buscarTelefonoProveedor(proveedor);
            m.put("telefono1", telefono_proveedor.getTel1());
            m.put("telefono2", telefono_proveedor.getTel2());
        }

        m.put("proveedores", proveedores);

        return "proveedores/ver";
    }

    @GetMapping(value = "/cargar_ciudad/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Ciudad> cargarCiudad(@PathVariable String term) {
        return ciudadService.buscarCiudadPorNombre(term);
    }

    @GetMapping("/formulario")
    public String agregar(Map m) {

        proveedor = new Proveedor();

        telefono_proveedor = new Telefono_Proveedor();

        fecha = new Date();

        editar = false;

        m.put("editar", editar);
        m.put("proveedor", proveedor);

        m.put("titulo", "Añadir Proveedor");

        return "proveedores/formulario";
    }

    @GetMapping("/formulario/{id}")
    public String modificar(@PathVariable int id, Map m) {

        editar = true;

        proveedor = proveedorService.findById(id);

        telefono_proveedor = telefonoService.buscarTelefonoProveedor(proveedor);

        m.put("editar", editar);
        m.put("proveedor", proveedor);
        m.put("datos_ciudad", proveedor.getCiudad().getCp() + ", " + proveedor.getCiudad().getCiudad() + ", "
                + proveedor.getCiudad().getProvincia().getNombre());
        m.put("ciudad", proveedor.getCiudad());

        m.put("telefono1", telefono_proveedor.getTel1());
        m.put("telefono2", telefono_proveedor.getTel2());

        m.put("titulo", "Editar Datos del Proveedor");

        return "proveedores/formulario";
    }

    @PostMapping("/formulario")
    public String guardar(@Valid Proveedor proveedor, @RequestParam(name = "telefono1") String tel1,
            @RequestParam(name = "telefono2") String tel2, @RequestParam("file") MultipartFile foto, RedirectAttributes flash) {

        if (comprobarDuplicacionDeDatos(proveedor, editar)) {
            //Mensaje de error
            flash.addFlashAttribute("duplicacion", "Ya se encuentra registrado el proveedor "
                    + proveedor.getNombre() + "(" + proveedor.getCiudad().getCiudad() + ")");
            if (editar) {
                return "redirect:/proveedores/formulario/" + proveedor.getId();
            } else {
                return "redirect:/proveedores/formulario";
            }

        } else {

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

            if (!editar) {
                telefono_proveedor.setTel1(tel1);
                telefono_proveedor.setTel2(tel2);

                proveedor.setAlta(fecha);
                proveedor.setHab(true);

                telefono_proveedor.setProveedor(proveedor);
            } else {
                telefono_proveedor.setTel1(tel1);
                telefono_proveedor.setTel2(tel2);
            }

            proveedorService.save(proveedor);
            telefonoService.guardarTelefonoProveedor(telefono_proveedor);

        }

        return "redirect:/proveedores/ver";
    }

    public boolean comprobarDuplicacionDeDatos(Proveedor proveedor, boolean editar) {
        proveedores = proveedorService.findAll();

        if (!proveedores.isEmpty()) {
            if (editar) {
                for (Proveedor p : proveedores) {
                    if (!(p.getId() == proveedor.getId())) {
                        if (p.getNombre().equals(proveedor.getNombre()) && p.getCiudad().equals(proveedor.getCiudad())) {
                            System.out.println("¡Duplicacion de datos en editar!");
                            return true;
                        }
                    }
                }
            } else {
                for (Proveedor p : proveedores) {
                    if (p.getNombre().equals(proveedor.getNombre()) && p.getCiudad().equals(proveedor.getCiudad())) {
                        System.out.println("¡Duplicacion de datos en agregar!");
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
