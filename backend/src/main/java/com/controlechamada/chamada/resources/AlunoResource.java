package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.dto.TurmaDTO;
import com.controlechamada.chamada.domain.entities.Aluno;
import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.domain.entities.Turma;
import com.controlechamada.chamada.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

    @Autowired
    private AlunoService service;


    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){
        List<Aluno> alunos = service.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id){
        Aluno aluno = service.findById(id);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> insert(@RequestBody Aluno aluno){
        aluno.setId(null);
        aluno = service.insert(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Aluno aluno){
        aluno = service.update(id, aluno);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}/presencas")
    public ResponseEntity<List<Chamada>> getPresencas(@PathVariable Long id){
        Aluno aluno = service.findById(id);
        return ResponseEntity.ok(aluno.getPresencas());
    }

    @GetMapping("/{id}/turma")
    public ResponseEntity<TurmaDTO> getTurma(@PathVariable Long id){
        Aluno aluno = service.findById(id);
        TurmaDTO turma = new TurmaDTO(aluno.getTurma());
        return ResponseEntity.ok(turma);
    }
}
