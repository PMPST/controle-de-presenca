package com.controlechamada.chamada.repositories;

import com.controlechamada.chamada.domain.entities.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
