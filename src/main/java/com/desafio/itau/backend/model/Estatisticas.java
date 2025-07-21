package com.desafio.itau.backend.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Estatisticas {

    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

}
