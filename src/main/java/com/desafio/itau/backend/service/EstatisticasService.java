package com.desafio.itau.backend.service;

import com.desafio.itau.backend.model.Estatisticas;
import com.desafio.itau.backend.model.Transacao;
import com.desafio.itau.backend.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class EstatisticasService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Estatisticas getEstatisticas(){

        List<Transacao> transacoes = transacaoRepository.findAll();

        double[] valores = new double[transacoes.size()];

        OffsetDateTime time = OffsetDateTime.now();
        OffsetDateTime minute = time.minusMinutes(1);



        int i = 0;
        for(Transacao transacao : transacoes){

            if(transacao.getDataHora().isAfter(minute) && transacao.getDataHora().isBefore(time)) {

                valores[i] = transacao.getValor();
                i++;
            }

        }

        DoubleSummaryStatistics stats = DoubleStream.of(valores).summaryStatistics();


        Estatisticas estatisticas = new Estatisticas();
        estatisticas.setAvg(stats.getAverage());
        estatisticas.setCount(stats.getCount());
        estatisticas.setMax(stats.getMax());
        estatisticas.setMin(stats.getMin());
        estatisticas.setSum(stats.getSum());

        return estatisticas;

    }


}
