/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ICliente_Dao;
import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Cliente_Service_Impl implements ICliente_Service {

    @Autowired
    private ICliente_Dao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarCliente(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void borrarCliente(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarHabilitados() {
        return clienteDao.buscarHabilitados();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarDeshabilitados() {
        return clienteDao.buscarDeshabilitados();
    }

    @Override
    public List<Cliente> buscarCliente(String term) {
        return clienteDao.buscarCliente(term);
    }

    @Override
    public Cliente buscarPersonaCliente(Persona persona) {
        return clienteDao.buscarPersonaCliente(persona);
    }

}
