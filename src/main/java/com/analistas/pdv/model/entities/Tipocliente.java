/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matia
 */
@Entity
@Table(name = "tipos_clientes")
public class Tipocliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_tipo_cliente")
    private int id;

    private boolean anonimo;

    private boolean registrado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipocliente")
    private List<Factura_Venta> facturas_ventas;

    @ManyToOne
    @JoinColumn(name = "fk_id_clie", referencedColumnName = "pk_id_cli")
    private Cliente cliente;

    private boolean hab;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Factura_Venta> getFacturas_ventas() {
        return facturas_ventas;
    }

    public void setFacturas_ventas(List<Factura_Venta> facturas_ventas) {
        this.facturas_ventas = facturas_ventas;
    }

    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    public boolean isRegistrado() {
        return registrado;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }

}
