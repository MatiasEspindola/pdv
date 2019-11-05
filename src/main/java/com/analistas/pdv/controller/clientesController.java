package com.analistas.pdv.controller;

import com.analistas.pdv.model.entity.Ciudad;
import com.analistas.pdv.model.entity.Cliente;
import com.analistas.pdv.model.entity.Tipodocumento;
import com.analistas.pdv.model.service.Ciudad_Service_Impl;
import com.analistas.pdv.model.service.Cliente_Service_Impl;
import com.analistas.pdv.model.service.TipoDocumento_Service_Impl;
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
@SessionAttributes("cliente")
@RequestMapping("/clientes")
public class clientesController {

    @Autowired
    private Cliente_Service_Impl clienteServ;

    @Autowired
    private Ciudad_Service_Impl ciudadServ;

    @Autowired
    private TipoDocumento_Service_Impl tipodocumentoServ;

    @GetMapping("/ver_clientes")
    public String clientes(Map m) {

        List<Cliente> clientes = clienteServ.findAll();

        m.put("titulo", "Ver Clientes");
        m.put("clientes", clientes);
        return "clientes/ver_clientes";
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

    @PostMapping("/registrar")
    public String guardar(@Valid Cliente cliente, Map m) {
        clienteServ.save(cliente);
        return "redirect:/clientes/ver_clientes";
    }

    @RequestMapping(value = "/borrar/{id}")
    public String borrar(@PathVariable(value = "id") int id) {

        if (id > 0) {
            Cliente cliente = clienteServ.findById(id);
            clienteServ.delete(cliente);
        }

        return "redirect:/clientes/ver_clientes";
    }

}
