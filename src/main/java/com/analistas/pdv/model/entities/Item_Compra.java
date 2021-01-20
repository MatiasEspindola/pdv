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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author matias
 */
@Entity
@Table(name = "lineas_compras")
public class Item_Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_lin_comp")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_id_prod", referencedColumnName = "pk_id_prod")
    private Producto producto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item_compra")
    @JsonIgnore
    private List<Factura_Compra> facturas_compras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Factura_Compra> getFacturas_compras() {
        return facturas_compras;
    }

    public void setFacturas_compras(List<Factura_Compra> facturas_compras) {
        this.facturas_compras = facturas_compras;
    }

}
