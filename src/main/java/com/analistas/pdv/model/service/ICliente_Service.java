/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Ciudad;
import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Persona;
import java.util.List;

public interface ICliente_Service {

    public List<Cliente> buscarCliente(String term);

    public List<Cliente> buscarHabilitados();

    public List<Cliente> buscarDeshabilitados();

    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer id);

    public void guardarCliente(Cliente cliente);

    public void borrarCliente(Cliente cliente);
    
    public Cliente buscarPersonaCliente(Persona persona);

}
