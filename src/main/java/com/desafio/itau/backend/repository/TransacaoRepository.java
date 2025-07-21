package com.desafio.itau.backend.repository;

import com.desafio.itau.backend.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
