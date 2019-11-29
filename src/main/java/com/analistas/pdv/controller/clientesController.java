package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Ciudad;
import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Tipodocumento;
import com.analistas.pdv.model.service.Ciudad_Service_Impl;
import com.analistas.pdv.model.service.Cliente_Service_Impl;
import com.analistas.pdv.model.service.TipoDocumento_Service_Impl;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("cliente")
@RequestMapping("/clientes")
public class clientesController {

    @Autowired
    private Cliente_Service_Impl clienteServ;

    @Autowired
    private Ciudad_Service_Impl ciudadServ;

    @Autowired
    private TipoDocumento_Service_Impl tipodocumentoServ;

    private static boolean editar;

    @GetMapping("/ver_clientes")
    public String clientes(Map m) {

        List<Cliente> clientes = clienteServ.findAll();

        m.put("titulo", "Ver Clientes");
        m.put("clientes", clientes);
        return "clientes/ver_clientes";
    }

    @GetMapping("/detalles/{id}")
    public String detalles_cliente(Map m, @PathVariable(value = "id") int id) {

        Cliente cliente = clienteServ.findById(id);

        m.put("titulo", "Detalles");
        m.put("cliente", cliente);
        return "clientes/detalles";
    }

    @GetMapping(value = "/cargar_ciudad/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Ciudad> cargarCiudadCliente(@PathVariable String term) {
        return ciudadServ.buscarPorNombre(term);
    }

    //CRUD
    @GetMapping("/registrar")
    public String registrar(Map m) {
        Cliente cliente = new Cliente();

        List<Tipodocumento> tiposdocumentos = tipodocumentoServ.findAll();

        String inf = "Buscar Ciudad";

        editar = false;

        m.put("inf", inf);
        m.put("titulo", "Registrar Cliente");
        m.put("cliente", cliente);
        m.put("tiposdocumentos", tiposdocumentos);
        return "clientes/registrar";
    }

    @GetMapping(value = "/editar/{id}")
    public String editar(Map m, @PathVariable(value = "id") int id) {
        Cliente cliente = null;

        List<Tipodocumento> tiposdocumentos = tipodocumentoServ.findAll();

        editar = true;

        if (id > 0) {
            cliente = clienteServ.findById(id);
            if (cliente == null) {
                return "redirect:/clientes/ver_clientes";
            }

            String inf = cliente.getCiudad().getCp() + ", " + cliente.getCiudad().getCiudad() + ", " + cliente.getCiudad().getProvincia().getProvincia();

            m.put("inf", inf);
        } else {
            return "redirect:/clientes/ver_clientes";
        }

        m.put("titulo", "Editar Cliente");
        m.put("cliente", cliente);
        m.put("tiposdocumentos", tiposdocumentos);
        return "clientes/registrar";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Map m, RedirectAttributes flash) {

        String titulo;
        boolean error = false;

        if (editar) {
            flash.addFlashAttribute("editar", "¡Datos modificados con éxito!");
            titulo = "Editar Cliente";
        } else {
            flash.addFlashAttribute("nuevo", "¡Cliente agregado con éxito!");
            titulo = "Registrar Cliente";
        }

        if (result.hasErrors()) {

            error = true;

            List<Tipodocumento> tiposdocumentos = tipodocumentoServ.findAll();
            m.put("tiposdocumentos", tiposdocumentos);

            m.put("titulo", titulo);
            m.put("error", error);

            return "clientes/registrar";
        } else {

            List<Cliente> clientes = clienteServ.findAll();

            for (int i = 0; i < clientes.size(); i++) {
                if (cliente.getDoc().equals(clientes.get(i).getDoc()) && editar == false) {
                    flash.addFlashAttribute("existente", "¡El cliente con el dni " + cliente.getDoc() + " ya existe!");
                    return "redirect:/clientes/ver_clientes";
                }
            }

            clienteServ.save(cliente);

            m.put("error", error);
            
            return "redirect:/clientes/ver_clientes";

        }

    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

        if (id > 0) {
            Cliente cliente = clienteServ.findById(id);
            clienteServ.delete(cliente);

            flash.addFlashAttribute("eliminar", "Se ha eliminado con éxito");
        }

        return "redirect:/clientes/ver_clientes";
    }

}
