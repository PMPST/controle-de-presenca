package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.entities.Aluno;
import com.controlechamada.chamada.domain.entities.Aula;
import com.controlechamada.chamada.domain.entities.Turma;
import com.controlechamada.chamada.services.AulaService;
import com.controlechamada.chamada.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {
    @Autowired
    private TurmaService turmaService;

    @Autowired
    private AulaService aulaService;



    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        List<Turma> turmas = turmaService.findAll();
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Long id){
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> insert(@RequestBody Turma turma){
        turma.setId(null);
        turma = turmaService.insert(turma);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Turma turma){
        turma = turmaService.update(id, turma);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}/aulas")
    public ResponseEntity<List<Aula>> findAllAulasByTurma(@PathVariable Long id){
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma.getAulas());
    }

    @PostMapping("/{id}/aulas")
    public ResponseEntity<Aula> insertAula(@PathVariable Long id, @RequestBody Aula aula){
        Turma t = turmaService.findById(id);
        aula.setId(null);
        aula.setTurma(t);
        aula = aulaService.insert(aula);
        return ResponseEntity.ok(aula);
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<Set<Aluno>> findAllAlunosByTurma(@PathVariable Long id){
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma.getAlunos());
    }
}
