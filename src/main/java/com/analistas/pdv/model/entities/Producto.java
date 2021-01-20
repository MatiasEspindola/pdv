/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author matias
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_prod")
    private int id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String modelo;

    @NotNull
    private double precio;

    private int stock;

    private String foto;

    private boolean hab;

//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date fecha_envasado;
//
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date fecha_vencimiento;ç
    

    @ManyToOne
    @JoinColumn(name = "fk_id_mar", referencedColumnName = "pk_id_mar")
    @NotNull
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "fk_id_cat", referencedColumnName = "pk_id_cat")
    @NotNull
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "producto")
    @JsonIgnore
    private List<Item_Compra> items_compras;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "producto")
    @JsonIgnore
    private List<Item_Venta> item_ventas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Item_Compra> getItems_compras() {
        return items_compras;
    }

    public void setItems_compras(List<Item_Compra> items_compras) {
        this.items_compras = items_compras;
    }

    public List<Item_Venta> getItem_ventas() {
        return item_ventas;
    }

    public void setItem_ventas(List<Item_Venta> item_ventas) {
        this.item_ventas = item_ventas;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

//    public Date getFecha_envasado() {
//        return fecha_envasado;
//    }
//
//    public void setFecha_envasado(Date fecha_envasado) {
//        this.fecha_envasado = fecha_envasado;
//    }
//
//    public Date getFecha_vencimiento() {
//        return fecha_vencimiento;
//    }
//
//    public void setFecha_vencimiento(Date fecha_vencimiento) {
//        this.fecha_vencimiento = fecha_vencimiento;
//    }
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

}
