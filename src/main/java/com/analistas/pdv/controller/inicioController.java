/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author matia
 */
@Controller
public class inicioController {
    
    @GetMapping({"/","/inicio"})
    public String inicio(Map m){
        
        m.put("titulo", "LOGO - Inicio");
        
        return "inicio";
    }
    
}
