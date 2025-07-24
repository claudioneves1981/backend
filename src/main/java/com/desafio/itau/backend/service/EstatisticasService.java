package com.desafio.itau.backend.service;

import com.desafio.itau.backend.dto.EstatisticasDTO;
import com.desafio.itau.backend.model.Transacao;
import com.desafio.itau.backend.repository.TransacaoRepository;
import com.desafio.itau.backend.util.RoundingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class EstatisticasService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public EstatisticasDTO getEstatisticas(){

        OffsetDateTime time = OffsetDateTime.now();

        DoubleSummaryStatistics stats = transacaoRepository.findAll()
                .stream()
                .filter(t -> t.getDataHora().isAfter(time.minusSeconds(60)))
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        RoundingUtil roundingUtil = new RoundingUtil();


        return new EstatisticasDTO(
                stats.getCount(),
                roundingUtil.roundingNumber(stats.getSum()),
                roundingUtil.roundingNumber(stats.getAverage()),
                roundingUtil.roundingNumber(stats.getCount() == 0 ? 0 : stats.getMin()),
                roundingUtil.roundingNumber(stats.getCount() == 0 ? 0 : stats.getMax()));

    }


}
