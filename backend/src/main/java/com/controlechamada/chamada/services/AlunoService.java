package com.controlechamada.chamada.services;

import com.controlechamada.chamada.domain.entities.Aluno;
import com.controlechamada.chamada.repositories.AlunoRepository;
import com.controlechamada.chamada.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public Aluno findById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Nenhum aluno encontrado para o id: " + id));
    }

    public List<Aluno> findAll(){
        return repository.findAll();
    }

    public Aluno insert(Aluno aluno){
        return repository.save(aluno);
    }

    public Aluno update(Long id, Aluno dados){
        Aluno entity = findById(id);
        updateData(entity, dados);
        return repository.save(entity);
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }


    private void updateData(Aluno entity, Aluno dados){
        entity.setNome(dados.getNome());
        entity.setDataMatricula(dados.getDataMatricula());
        entity.setDataNascimento(dados.getDataNascimento());
        entity.setUrlFoto(dados.getUrlFoto());
    }
}
