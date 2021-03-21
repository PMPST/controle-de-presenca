package com.controlechamada.chamada.domain.dto;

import com.controlechamada.chamada.domain.entities.Chamada;

import java.time.LocalDate;

public class ChamadaAulaDTO {
    private final long serialVersionUID = 1L;

    private Long id;
    private LocalDate data;
    private boolean presenca;
    private AlunoDTO aluno;


    public ChamadaAulaDTO() { }

    public ChamadaAulaDTO (Chamada chamada) {
        this.id = chamada.getId();
        this.data = chamada.getData();
        this.presenca = chamada.getPresenca();
        this.aluno = new AlunoDTO(chamada.getAluno());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean getPresenca() {
        return presenca;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }

    public AlunoDTO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoDTO aluno) {
        this.aluno = aluno;
    }
}

