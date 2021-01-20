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
 * @author nahuel
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_us")
    private int id;

    private String us;
    private String ps;
    private boolean clave;
    private boolean hab;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario_venta")
    @JsonManagedReference
    @JsonIgnore
    private List<Registro_Venta> ventas;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario_compra")
    @JsonManagedReference
    @JsonIgnore
    private List<Registro_Compra> compras;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
    @JsonManagedReference
    @JsonIgnore
    private List<Rol> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public boolean isClave() {
        return clave;
    }

    public void setClave(boolean clave) {
        this.clave = clave;
    }

    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    public List<Registro_Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Registro_Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

}
