/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.dao;

import com.analistas.pdv.model.entities.Item_Compra;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matia
 */
public interface IitemCompra_Dao extends CrudRepository <Item_Compra, Integer>{
    
}
