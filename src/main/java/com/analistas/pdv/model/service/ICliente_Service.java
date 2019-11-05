/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entity.Ciudad;
import com.analistas.pdv.model.entity.Cliente;
import java.util.List;

/**
 *
 * @author nahuel
 */
public interface ICliente_Service {
    
    public List<Cliente> findAll();

    public Cliente findById(Integer id);

    public void save(Cliente cliente);

    public void delete(Cliente cliente);
    
    public void uploadCliente(Ciudad ciudad);
    
}
