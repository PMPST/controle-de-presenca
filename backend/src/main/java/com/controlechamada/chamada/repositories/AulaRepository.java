package com.controlechamada.chamada.repositories;


import com.controlechamada.chamada.domain.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long> {
}
