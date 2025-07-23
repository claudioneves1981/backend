package com.desafio.itau.backend.service;

import com.desafio.itau.backend.dto.EstatisticasDTO;
import com.desafio.itau.backend.model.Transacao;
import com.desafio.itau.backend.repository.TransacaoRepository;
import com.desafio.itau.backend.util.RoundingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class EstatisticasService {

    @Autowired
    private TransacaoRepository transacaoRepository;



    public EstatisticasDTO getEstatisticas(){

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
        RoundingUtil roundingUtil = new RoundingUtil();

        EstatisticasDTO estatisticas = new EstatisticasDTO();
        estatisticas.setAvg(roundingUtil.roundingNumber(stats.getAverage()));
        estatisticas.setCount(stats.getCount());
        estatisticas.setMax(roundingUtil.roundingNumber(stats.getMax()));
        estatisticas.setMin(roundingUtil.roundingNumber(stats.getMin()));
        estatisticas.setSum(roundingUtil.roundingNumber(stats.getSum()));

        return estatisticas;

    }


}
