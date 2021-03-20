package com.controlechamada.chamada.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_AULA")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "dt_aula", nullable = false)
    private LocalDate data;


    @ManyToOne
    @JoinColumn(name="turma_id")
    private Turma turma;

    @OneToMany(mappedBy = "aula")
    private List<Chamada> chamada = new ArrayList<>();


    public Aula() { }

    public Aula(Long id, String descricao, LocalDate data, Turma turma) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.turma = turma;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Chamada> getChamada() {
        return chamada;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(id, aula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                '}';
    }
}
