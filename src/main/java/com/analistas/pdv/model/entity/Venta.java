/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.LocalTime;
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
 * @author nahuel
 */
@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_ven")
    private int id;

    private String descripcion;
    private double descu;
    private double monto;
    private int cantidad;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private LocalTime hora;

    private boolean en_camino;
    private boolean demorado;
    private boolean entregado;
    private boolean perdida;

    @ManyToOne
    @JoinColumn(name = "fk_id_producto", referencedColumnName = "pk_id_prod")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "fk_id_cli", referencedColumnName = "pk_id_cli")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_id_mdp", referencedColumnName = "pk_id_mdp")
    private Metodo_De_Pago metodo_de_pago;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venta")
    @JsonIgnore
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "fk_id_us", referencedColumnName = "pk_id_us")
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescu() {
        return descu;
    }

    public void setDescu(double descu) {
        this.descu = descu;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Metodo_De_Pago getMetodo_de_pago() {
        return metodo_de_pago;
    }

    public void setMetodo_de_pago(Metodo_De_Pago metodo_de_pago) {
        this.metodo_de_pago = metodo_de_pago;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
