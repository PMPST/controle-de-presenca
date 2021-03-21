package com.controlechamada.chamada.domain.dto;

import com.controlechamada.chamada.domain.entities.Turma;

import java.io.Serializable;
import java.time.LocalDate;

public class TurmaDTO implements Serializable {
    private final long serialVersionUID = 1L;

    private Long id;
    private String descricao;
    private LocalDate dataInicio;


    public TurmaDTO() {
    }

    public TurmaDTO(Turma turma) {
        if (turma != null){
            this.id = turma.getId();
            this.descricao = turma.getDescricao();
            this.dataInicio = turma.getDataInicio();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
}
