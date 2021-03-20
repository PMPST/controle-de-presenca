package com.controlechamada.chamada.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="TB_TURMA")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dt_inicio")
    private LocalDate dataInicio;


    @OneToMany(mappedBy = "turma")
    private Set<Aluno> alunos = new HashSet<>();

    @OneToMany(mappedBy = "turma")
    private List<Aula> aulas = new ArrayList<>();


    public Turma() {
    }

    public Turma(Long id, String descricao, LocalDate dataInicio) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
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

    @JsonIgnore
    public Set<Aluno> getAlunos() {
        return alunos;
    }

    @JsonIgnore
    public List<Aula> getAulas() {
        return aulas;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
