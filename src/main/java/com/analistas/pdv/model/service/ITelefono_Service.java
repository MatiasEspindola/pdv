/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.entities.Proveedor;
import com.analistas.pdv.model.entities.Telefono_Cliente;
import com.analistas.pdv.model.entities.Telefono_Proveedor;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ITelefono_Service {

    public List<Telefono_Cliente> listarTelefonosClientes();

    public void guardarTelefonoCliente(Telefono_Cliente telefono);

    public void borrarTelefonoCliente(Telefono_Cliente telefono);

    public List<Telefono_Proveedor> listarTelefonosProveedores();

    public void guardarTelefonoProveedor(Telefono_Proveedor telefono);

    public void borrarTelefonoProveedor(Telefono_Proveedor telefono);

    public Telefono_Cliente buscarTelefonoCliente(Cliente cliente);

    public Telefono_Proveedor buscarTelefonoProveedor(Proveedor proveedor);

}
