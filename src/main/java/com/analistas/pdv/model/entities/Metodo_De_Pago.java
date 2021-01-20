/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author matia
 */
@Entity
@Table(name = "metodos_de_pagos")
public class Metodo_De_Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_mdp")
    private int id;

    private String metodo;

    private boolean hab;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "metodo_de_pago_venta")
    @JsonIgnore
    private List<Factura_Venta> facturas_ventas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "metodo_de_pago_compra")
    @JsonIgnore
    private List<Factura_Compra> facturas_compras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    public List<Factura_Venta> getFacturas_ventas() {
        return facturas_ventas;
    }

    public void setFacturas_ventas(List<Factura_Venta> facturas_ventas) {
        this.facturas_ventas = facturas_ventas;
    }

    public List<Factura_Compra> getFacturas_compras() {
        return facturas_compras;
    }

    public void setFacturas_compras(List<Factura_Compra> facturas_compras) {
        this.facturas_compras = facturas_compras;
    }

}
