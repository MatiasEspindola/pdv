/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author nahuel
 */
@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_com")
    private int id;
    private double subtotal;
    private double total;
    private int cantidad;
    private String descripcion;
    private boolean demorado;
    private boolean entregado;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCompra;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaEntrega;
    
    private Double monto_envio;
    private boolean en_camino;
    private boolean perdida;

    @ManyToOne
    @JoinColumn(name = "fk_id_prod", referencedColumnName = "pk_id_prod")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "fk_id_metododepago", referencedColumnName = "pk_id_mdp")
    private Metodo_De_Pago metodo_de_pago;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "pk_id_us")
    private Usuario usuario;

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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Double getMonto_envio() {
        return monto_envio;
    }

    public void setMonto_envio(Double monto_envio) {
        this.monto_envio = monto_envio;
    }

    public boolean isEn_camino() {
        return en_camino;
    }

    public void setEn_camino(boolean en_camino) {
        this.en_camino = en_camino;
    }

    public boolean isDemorado() {
        return demorado;
    }

    public void setDemorado(boolean demorado) {
        this.demorado = demorado;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isPerdida() {
        return perdida;
    }

    public void setPerdida(boolean perdida) {
        this.perdida = perdida;
    }

    public Metodo_De_Pago getMetodo_de_pago() {
        return metodo_de_pago;
    }

    public void setMetodo_de_pago(Metodo_De_Pago metodo_de_pago) {
        this.metodo_de_pago = metodo_de_pago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
