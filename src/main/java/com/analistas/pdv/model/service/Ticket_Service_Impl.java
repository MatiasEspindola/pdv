/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.dao.ITicket_Dao;
import com.analistas.pdv.model.entities.Ticket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */

@Service
public class Ticket_Service_Impl implements ITicket_Service{

    @Autowired
    private ITicket_Dao ticketDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Ticket findById(Integer id) {
        return ticketDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

    @Override
    @Transactional
    public void delete(Ticket ticket) {
        ticketDao.delete(ticket);
    }

}
