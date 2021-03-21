package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.services.ChamadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chamadas")
public class ChamadaResource {
    @Autowired
    private ChamadaService service;


    @GetMapping("/{id}")
    public ResponseEntity<Chamada> getById(@PathVariable Long id){
        Chamada chamada = service.findById(id);
        return ResponseEntity.ok(chamada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Chamada chamada){
        service.update(id, chamada);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
