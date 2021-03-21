package com.controlechamada.chamada.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_CHAMADA")
public class Chamada implements Serializable {
    private final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bl_presente")
    private boolean presenca;

    @Column(name = "dt_aula")
    private LocalDate data;


    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    @JsonProperty(value = "aluno", access = JsonProperty.Access.WRITE_ONLY)
    private Aluno aluno;


    public Chamada() {
    }

    public Chamada(Long id, boolean presenca, LocalDate data, Aula aula, Aluno aluno) {
        this.id = id;
        this.presenca = presenca;
        this.data = data;
        this.aula = aula;
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getPresenca() {
        return presenca;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }


    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamada chamada = (Chamada) o;
        return Objects.equals(id, chamada.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chamada{" +
                "id=" + id +
                ", presenca=" + presenca +
                ", data=" + data +
                '}';
    }
}
