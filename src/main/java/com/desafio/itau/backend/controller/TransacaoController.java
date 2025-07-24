package com.desafio.itau.backend.controller;


import com.desafio.itau.backend.dto.TransacaoDTO;
import com.desafio.itau.backend.model.Transacao;
import com.desafio.itau.backend.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    private final List<TransacaoDTO> transacoes = new CopyOnWriteArrayList<>();

    @PostMapping("/transacao")
    public ResponseEntity<Void> createTransacao(@RequestBody TransacaoDTO transacao) {

        boolean isValid = transacaoService.isValidTransacao(transacao);

        if(isValid){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteTransacao(){
        transacaoService.deleteTransacao();
        return ResponseEntity.ok().build();
    }
}
