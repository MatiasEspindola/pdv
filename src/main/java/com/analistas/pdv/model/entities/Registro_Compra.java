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
@Table(name = "registros_compras")
public class Registro_Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_reg_comp")
    private int id;

    private boolean entregado;
    private boolean reanudada;
    private boolean perdida;
    private boolean hab;

    @ManyToOne
    @JoinColumn(name = "fk_id_fac_comp", referencedColumnName = "pk_id_fac_comp")
    private Factura_Compra factura_compra;

    @ManyToOne
    @JoinColumn(name = "fk_id_us_rc", referencedColumnName = "pk_id_us")
    private Usuario usuario_compra;

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

    public Factura_Compra getFactura_compra() {
        return factura_compra;
    }

    public void setFactura_compra(Factura_Compra factura_compra) {
        this.factura_compra = factura_compra;
    }

    public Usuario getUsuario_compra() {
        return usuario_compra;
    }

    public void setUsuario_compra(Usuario usuario_compra) {
        this.usuario_compra = usuario_compra;
    }

}
