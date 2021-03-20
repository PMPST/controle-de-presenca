package com.controlechamada.chamada.services;

import com.controlechamada.chamada.domain.entities.Aula;
import com.controlechamada.chamada.repositories.AulaRepository;
import com.controlechamada.chamada.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {
    @Autowired
    private AulaRepository repository;


    public Aula findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum aula encontrado para o id: " + id));
    }

    public List<Aula> findAll(){
        return repository.findAll();
    }

    public Aula insert(Aula aula){
        return repository.save(aula);
    }

    public Aula update(Long id, Aula dados){
        Aula entity = findById(id);
        updateData(entity, dados);
        return repository.save(entity);
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }


    private void updateData(Aula entity, Aula dados){
        entity.setDescricao(dados.getDescricao());
        entity.setData(dados.getData());
    }

}
