/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author nahuel
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_cli")
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Factura_Venta> facturas_ventas;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Tipocliente> tiposclientes;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    @JsonIgnore
    private List<Telefono_Cliente> telefono_cliente;

    private boolean hab;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date alta;

    @ManyToOne
    @JoinColumn(name = "fk_id_per", referencedColumnName = "pk_id_per")
    @NotNull
    private Persona persona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Factura_Venta> getFacturas_ventas() {
        return facturas_ventas;
    }

    public void setFacturas_ventas(List<Factura_Venta> facturas_ventas) {
        this.facturas_ventas = facturas_ventas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public List<Tipocliente> getTiposclientes() {
        return tiposclientes;
    }

    public void setTiposclientes(List<Tipocliente> tiposclientes) {
        this.tiposclientes = tiposclientes;
    }

    public List<Telefono_Cliente> getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(List<Telefono_Cliente> telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }
    
    

}
