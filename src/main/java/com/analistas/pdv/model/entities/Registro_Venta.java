/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author matia
 */
@Entity
@Table(name = "registros_ventas")
public class Registro_Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_reg_vent")
    private int id;

    private boolean entregado;
    private boolean reanudada;
    private boolean perdida;
    private boolean hab;

    @ManyToOne
    @JoinColumn(name = "fk_id_fac_vent", referencedColumnName = "pk_id_fac_vent")
    private Factura_Venta factura_venta;

    @ManyToOne
    @JoinColumn(name = "fk_id_us_rv", referencedColumnName = "pk_id_us")
    private Usuario usuario_venta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isReanudada() {
        return reanudada;
    }

    public void setReanudada(boolean reanudada) {
        this.reanudada = reanudada;
    }

    public boolean isPerdida() {
        return perdida;
    }

    public void setPerdida(boolean perdida) {
        this.perdida = perdida;
    }

    public boolean isHab() {
        return hab;
    }

    public void setHab(boolean hab) {
        this.hab = hab;
    }

    public Factura_Venta getFactura_venta() {
        return factura_venta;
    }

    public void setFactura_venta(Factura_Venta factura_venta) {
        this.factura_venta = factura_venta;
    }

    public Usuario getUsuario_venta() {
        return usuario_venta;
    }

    public void setUsuario_venta(Usuario usuario_venta) {
        this.usuario_venta = usuario_venta;
    }

}
