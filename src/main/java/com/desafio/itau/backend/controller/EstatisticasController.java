package com.desafio.itau.backend.controller;


import com.desafio.itau.backend.model.Estatisticas;
import com.desafio.itau.backend.service.EstatisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EstatisticasController {

    @Autowired
    private EstatisticasService estatisticasService;


    @GetMapping("/estatisticas")
    public ResponseEntity<Estatisticas> getEstatisticas(){
        return ResponseEntity.ok(estatisticasService.getEstatisticas());

    }


}
