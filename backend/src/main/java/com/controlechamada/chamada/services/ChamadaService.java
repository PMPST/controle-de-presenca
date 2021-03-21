package com.controlechamada.chamada.services;

import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.repositories.ChamadaRepository;
import com.controlechamada.chamada.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadaService {
    @Autowired
    private ChamadaRepository repository;

    @Autowired
    private AlunoService alunoService;




    public Chamada findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhuma chamada encontrado para o id: " + id));

    }

    public List<Chamada> findAll(){
        return repository.findAll();
    }

    public Chamada insert(Chamada chamada){
        chamada = repository.save(chamada);
        chamada.setAluno(alunoService.findById(chamada.getAluno().getId()));
        return chamada;
    }

    public Chamada update(Long id, Chamada dados){
        Chamada entity = findById(id);
        updateData(entity, dados);
        return repository.save(entity);
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }


    private void updateData(Chamada entity, Chamada dados){
        entity.setData(dados.getData());
        entity.setPresenca(dados.getPresenca());
    }

}
