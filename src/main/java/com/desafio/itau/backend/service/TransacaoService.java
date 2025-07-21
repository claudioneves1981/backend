package com.desafio.itau.backend.service;

import com.desafio.itau.backend.model.Transacao;
import com.desafio.itau.backend.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransacaoService {


    @Autowired
    private TransacaoRepository transacaoRepository;

    public void createTransacao(Transacao transacao){
        transacao.setDataHora(OffsetDateTime.now());
        transacaoRepository.save(transacao);
    }

    public void deleteTransacao(){
        transacaoRepository.deleteAll();
    }

}
