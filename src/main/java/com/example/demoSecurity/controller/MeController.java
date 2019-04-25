package com.example.demoSecurity.controller;

import com.example.demoSecurity.model.Me;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    @GetMapping("/cashier")
    public Me getCashier(){
        return new Me(1, "Cashier");
    }

    @GetMapping("/check")
    public Me getCheck(){
        return new Me(2, "Check");
    }
    @GetMapping("/general")
    public Me getGeneral(){
        return new Me(3, "General");
    }
    @GetMapping("/shared")
    public Me getShared(){
        return new Me(4, "Shared");
    }
}
