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
@Table(name = "facturas_ventas")
public class Factura_Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_fac_vent")
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
    @JoinColumn(name = "fk_id_lin_vent", referencedColumnName = "pk_id_lin_vent")
    private Item_Venta item_venta;

    @ManyToOne
    @JoinColumn(name = "fk_id_mdp_vent", referencedColumnName = "pk_id_mdp")
    private Metodo_De_Pago metodo_de_pago_venta;

    @ManyToOne
    @JoinColumn(name = "fk_id_cli", referencedColumnName = "pk_id_cli")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_id_tipo_cliente", referencedColumnName = "pk_id_tipo_cliente")
    private Tipocliente tipocliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "factura_venta")
    private List<Registro_Venta> registros_ventas;

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

    public Item_Venta getItem_venta() {
        return item_venta;
    }

    public void setItem_venta(Item_Venta item_venta) {
        this.item_venta = item_venta;
    }

    public Metodo_De_Pago getMetodo_de_pago_venta() {
        return metodo_de_pago_venta;
    }

    public void setMetodo_de_pago_venta(Metodo_De_Pago metodo_de_pago_venta) {
        this.metodo_de_pago_venta = metodo_de_pago_venta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tipocliente getTipo_cliente() {
        return tipocliente;
    }

    public void setTipo_cliente(Tipocliente tipocliente) {
        this.tipocliente = tipocliente;
    }

    public List<Registro_Venta> getRegistros_ventas() {
        return registros_ventas;
    }

    public void setRegistros_ventas(List<Registro_Venta> registros_ventas) {
        this.registros_ventas = registros_ventas;
    }

}
