package com.controlechamada.chamada.repositories;

import com.controlechamada.chamada.domain.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
