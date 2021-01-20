/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Telefono_Proveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.analistas.pdv.model.dao.ITelefonoCliente_Dao;
import com.analistas.pdv.model.dao.ITelefonoProveedor_Dao;
import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Proveedor;
import com.analistas.pdv.model.entities.Telefono_Cliente;

/**
 *
 * @author matia
 */
@Service
public class Telefono_Service_Impl implements ITelefono_Service{
    
    @Autowired 
    private ITelefonoProveedor_Dao telefonoProveedor_Dao;
    
    @Autowired 
    private ITelefonoCliente_Dao telefonoCliente_Dao;
    
    @Override
    public List<Telefono_Proveedor> listarTelefonosProveedores() {
        return telefonoProveedor_Dao.findAll();
    }

    @Override
    public void guardarTelefonoProveedor(Telefono_Proveedor telefono) {
        telefonoProveedor_Dao.save(telefono);
    }

    @Override
    public void borrarTelefonoProveedor(Telefono_Proveedor telefono) {
        telefonoProveedor_Dao.delete(telefono);
    }
    
    @Override
    public Telefono_Proveedor buscarTelefonoProveedor(Proveedor proveedor) {
        return telefonoProveedor_Dao.buscarTelefonoProveedor(proveedor);
    }
    
    @Override
    public List<Telefono_Cliente> listarTelefonosClientes() {
        return telefonoCliente_Dao.findAll();
    }

    @Override
    public void guardarTelefonoCliente(Telefono_Cliente telefono) {
        telefonoCliente_Dao.save(telefono);
    }

    @Override
    public void borrarTelefonoCliente(Telefono_Cliente telefono) {
        telefonoCliente_Dao.delete(telefono);
    }

    @Override
    public Telefono_Cliente buscarTelefonoCliente(Cliente cliente) {
        return telefonoCliente_Dao.buscarTelefonoCliente(cliente);
    }
    
}
