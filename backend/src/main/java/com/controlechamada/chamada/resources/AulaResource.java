package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.entities.Aula;
import com.controlechamada.chamada.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaResource {
    @Autowired
    private AulaService service;

    @GetMapping
    public ResponseEntity<List<Aula>> findAll(){
        List<Aula> aulas = service.findAll();
        return ResponseEntity.ok(aulas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> findById(@PathVariable Long id){
        Aula aula =service.findById(id);
        return ResponseEntity.ok(aula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Aula aula){
        service.update(id, aula);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
