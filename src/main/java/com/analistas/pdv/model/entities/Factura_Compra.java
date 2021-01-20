/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

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
 * @author matia
 */
@Entity
@Table(name = "facturas_compras")
public class Factura_Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_fac_comp")
    private int id;

    private double subtotal;
    private double total;
    private double descu;
    private double precio_unitario;
    private int cantidad;
    private String descripcion;
    private double monto_envio;
    private boolean hab;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaVenta;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "fk_id_lin_comp", referencedColumnName = "pk_id_lin_comp")
    private Item_Compra item_compra;

    @ManyToOne
    @JoinColumn(name = "fk_id_mdp_comp", referencedColumnName = "pk_id_mdp")
    private Metodo_De_Pago metodo_de_pago_compra;

    @ManyToOne
    @JoinColumn(name = "fk_id_prov", referencedColumnName = "pk_id_prov")
    private Proveedor proveedor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "factura_compra")
    private List<Registro_Compra> registros_compras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescu() {
        return descu;
    }

    public void setDescu(double descu) {
        this.descu = descu;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto_envio() {
        return monto_envio;
    }

    public void setMonto_envio(double monto_envio) {
        this.monto_envio = monto_envio;
    }

    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Item_Compra getItem_compra() {
        return item_compra;
    }

    public void setItem_compra(Item_Compra item_compra) {
        this.item_compra = item_compra;
    }

    public Metodo_De_Pago getMetodo_de_pago_compra() {
        return metodo_de_pago_compra;
    }

    public void setMetodo_de_pago_compra(Metodo_De_Pago metodo_de_pago_compra) {
        this.metodo_de_pago_compra = metodo_de_pago_compra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Registro_Compra> getRegistros_compras() {
        return registros_compras;
    }

    public void setRegistros_compras(List<Registro_Compra> registros_compras) {
        this.registros_compras = registros_compras;
    }

}
