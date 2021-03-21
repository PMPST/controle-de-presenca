package com.controlechamada.chamada.domain.dto;

import com.controlechamada.chamada.domain.entities.Aluno;

import java.io.Serializable;

public class AlunoDTO implements Serializable {
    private final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String urlFoto;


    public AlunoDTO() {
    }

    public AlunoDTO(Aluno aluno) {
        if (aluno != null){
            this.id = aluno.getId();
            this.nome = aluno.getNome();
            this.urlFoto = aluno.getUrlFoto();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

}
