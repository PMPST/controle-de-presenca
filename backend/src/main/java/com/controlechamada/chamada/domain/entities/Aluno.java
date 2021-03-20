package com.controlechamada.chamada.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;


    private LocalDate dataNascimento;


    private String urlFoto;


    private LocalDate dataMatricula;


    @OneToMany(mappedBy = "aluno")
    private List<Chamada> presencas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;


    public Aluno() { }

    public Aluno(Long id, String nome, LocalDate dataNascimento, String urlFoto, LocalDate dataMatricula, Turma turma) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.urlFoto = urlFoto;
        this.dataMatricula = dataMatricula;
        this.turma = turma;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public List<Chamada> getPresencas() {
        return presencas;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", urlFoto='" + urlFoto + '\'' +
                ", dataMatricula=" + dataMatricula +
                '}';
    }
}
