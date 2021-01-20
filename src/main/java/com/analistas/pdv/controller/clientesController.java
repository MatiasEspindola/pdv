/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Ciudad;
import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Persona;
import com.analistas.pdv.model.entities.Telefono_Cliente;
import com.analistas.pdv.model.entities.Tipocliente;
import com.analistas.pdv.model.service.ICiudad_Service;
import com.analistas.pdv.model.service.ICliente_Service;
import com.analistas.pdv.model.service.IPersona_Service;
import com.analistas.pdv.model.service.ITelefono_Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author matia
 */
@Controller
@SessionAttributes("persona")
@RequestMapping("/clientes")
public class clientesController {

    @Autowired
    private IPersona_Service personaService;

    @Autowired
    private ITelefono_Service telefonoService;

    @Autowired
    private ICliente_Service clienteService;

    @Autowired
    private ICiudad_Service ciudadService;

    private Persona persona;

    private Cliente cliente;

    private Tipocliente tipo_cliente;

    private Telefono_Cliente telefono_cliente;

    private List<Cliente> clientes;

    private List<Telefono_Cliente> telefonos_clientes;

    private Date fecha;

    private boolean editar;
    
    private static String filtrar;

    @GetMapping("/ver/habilitacion/{id}")
    public String habilitacion(@PathVariable int id) {

        if (clienteService.buscarClientePorId(id).isHab()) {
            clienteService.buscarClientePorId(id).setHab(false);
        } else {
            clienteService.buscarClientePorId(id).setHab(true);
        }

        clienteService.guardarCliente(clienteService.buscarClientePorId(id));

        return "redirect:/clientes/ver";
    }
    
    @GetMapping("/ver/{filtrar}")
    public String filtrados(@PathVariable String filtrar){
        
        this.filtrar = filtrar;
        
        return "redirect:/clientes/ver";
    }

    @GetMapping("/ver")
    public String ver(Map m) {
        
        m.put("titulo", "Ver Clientes");
        
        if(filtrar == null || filtrar.equals("listar_todo")){
            clientes = clienteService.listarClientes();
        }else if(filtrar.equals("clientes_habilitados")){
            clientes = clienteService.buscarHabilitados();
        }else if(filtrar.equals("clientes_deshabilitados")){
            clientes = clienteService.buscarDeshabilitados();
        }
        
        for (Cliente cliente : clientes) {
            telefono_cliente = telefonoService.buscarTelefonoCliente(cliente);
            m.put("telefono1", telefono_cliente.getTel1());
            m.put("telefono2", telefono_cliente.getTel2());
        }
        
        m.put("clientes", clientes);

        return "clientes/ver";
    }

    @GetMapping(value = "/cargar_ciudad/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Ciudad> cargarCiudad(@PathVariable String term) {
        return ciudadService.buscarCiudadPorNombre(term);
    }

    @GetMapping("/formulario")
    public String agregar(Map m) {

        persona = new Persona();

        cliente = new Cliente();

        fecha = new Date();

        telefono_cliente = new Telefono_Cliente();

        editar = false;

        m.put("editar", editar);
        m.put("tipos_documentos", personaService.listarTiposDocumentos());
        m.put("persona", persona);

        m.put("titulo", "Añadir Cliente");

        return "clientes/formulario";
    }

    @GetMapping("/formulario/{id}")
    public String modificar(@PathVariable int id, Map m) {

        persona = personaService.buscarPersonaPorId(id);

        cliente = clienteService.buscarPersonaCliente(persona);

        telefono_cliente = telefonoService.buscarTelefonoCliente(cliente);

        editar = true;

        m.put("editar", editar);
        m.put("tipos_documentos", personaService.listarTiposDocumentos());
        m.put("persona", persona);
        m.put("datos_ciudad", persona.getCiudad().getCp() + ", " + persona.getCiudad().getCiudad() + ", "
                + persona.getCiudad().getProvincia().getNombre());
        m.put("ciudad", persona.getCiudad());

        cliente = clienteService.buscarPersonaCliente(persona);

        telefono_cliente = telefonoService.buscarTelefonoCliente(cliente);

        m.put("telefono1", telefono_cliente.getTel1());
        m.put("telefono2", telefono_cliente.getTel2());

        m.put("titulo", "Editar Datos del Cliente");

        return "clientes/formulario";
    }

    @PostMapping("/formulario")
    public String guardar(@Valid Persona persona, @RequestParam(name = "telefono1") String tel1,
            @RequestParam(name = "telefono2") String tel2, RedirectAttributes flash) {

        if (comprobarDuplicacionDeDatos(persona, editar)) {
            //Mensaje de error
            flash.addFlashAttribute("duplicacion", "Ya se encuentra registrado el cliente"
                    + " con el dni: " + persona.getDoc());
            if (editar) {
                return "redirect:/clientes/formulario/" + persona.getId();
            } else {
                return "redirect:/clientes/formulario";
            }

        } else {

            if (!editar) {
                telefono_cliente.setTel1(tel1);
                telefono_cliente.setTel2(tel2);

                cliente.setPersona(persona);
                cliente.setAlta(fecha);
                cliente.setHab(true);

                telefono_cliente.setCliente(cliente);
            } else {
                telefono_cliente.setTel1(tel1);
                telefono_cliente.setTel2(tel2);
            }

            personaService.guardarPersona(persona);
            clienteService.guardarCliente(cliente);
            telefonoService.guardarTelefonoCliente(telefono_cliente);

        }

        return "redirect:/clientes/ver";
    }

    public boolean comprobarDuplicacionDeDatos(Persona persona, boolean editar) {
        List<Persona> personas = personaService.listarPersonas();

        if (!personas.isEmpty()) {
            if (editar) {
                for (Persona p : personas) {
                    if (!(p.getId() == persona.getId())) {
                        if (p.getDoc().equals(persona.getDoc())) {
                            System.out.println("¡Duplicacion de datos en editar!");
                            return true;
                        }
                    }
                }
            } else {
                for (Persona p : personas) {
                    if (p.getDoc().equals(persona.getDoc())) {
                        System.out.println("¡Duplicacion de datos en agregar!");
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
