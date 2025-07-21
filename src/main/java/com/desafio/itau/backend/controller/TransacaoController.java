package com.desafio.itau.backend.controller;


import com.desafio.itau.backend.model.Transacao;
import com.desafio.itau.backend.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transacao")
    public ResponseEntity<Transacao> createTransacao(@RequestBody Transacao transacao) {

        if (transacao == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else if (transacao.getValor() < 0) {

            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        }

        transacaoService.createTransacao(transacao);
        return new ResponseEntity<>(transacao, HttpStatus.CREATED);

    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteTransacao(){

        transacaoService.deleteTransacao();
    }
}
