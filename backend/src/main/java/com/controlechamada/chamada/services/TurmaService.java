package com.controlechamada.chamada.services;

import com.controlechamada.chamada.domain.entities.Turma;

import com.controlechamada.chamada.repositories.TurmaRepository;
import com.controlechamada.chamada.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;

    public Turma findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhuma turma encontrado para o id: " + id));
    }

    public List<Turma> findAll(){
        return repository.findAll();
    }

    public Turma insert(Turma turma){
        return repository.save(turma);
    }

    public Turma update(Long id, Turma dados){
        Turma entity = findById(id);
        updateData(entity, dados);
        return repository.save(entity);
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }


    private void updateData(Turma entity, Turma dados){
        entity.setDescricao(dados.getDescricao());
    }
}
