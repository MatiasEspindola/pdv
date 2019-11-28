/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Ticket;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ITicket_Service {
    
    public List<Ticket> findAll();
    public Ticket findById(Integer id);
    public void save(Ticket ticket);
    public void delete(Ticket ticket);
    
}
