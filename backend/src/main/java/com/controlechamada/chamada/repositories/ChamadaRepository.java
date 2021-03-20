package com.controlechamada.chamada.repositories;

import com.controlechamada.chamada.domain.entities.Chamada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadaRepository extends JpaRepository<Chamada, Long> {
}
