package com.suma.reserva_de_canchas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saludo")
public class SaludoController {

    @GetMapping("/desprotegida")
    public String saludo() {
        return "Saludo";
    }
    @GetMapping("/protegido")
    public String saludo2() {
        return "Saludo web protegida";
    }

}
