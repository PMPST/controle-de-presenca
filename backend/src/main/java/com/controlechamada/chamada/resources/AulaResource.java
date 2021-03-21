package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.dto.ChamadaAulaDTO;
import com.controlechamada.chamada.domain.entities.Aula;
import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.services.AulaService;
import com.controlechamada.chamada.services.ChamadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aulas")
public class AulaResource {
    @Autowired
    private AulaService aulaService;

    @Autowired
    private ChamadaService chamadaService;


    @GetMapping
    public ResponseEntity<List<Aula>> findAll(){
        List<Aula> aulas = aulaService.findAll();
        return ResponseEntity.ok(aulas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> findById(@PathVariable Long id){
        Aula aula = aulaService.findById(id);
        return ResponseEntity.ok(aula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Aula aula){
        aulaService.update(id, aula);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        aulaService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}/chamada")
    public ResponseEntity<List<ChamadaAulaDTO>> getChamadas(@PathVariable Long id){
        Aula aula = aulaService.findById(id);
        List<ChamadaAulaDTO> chamada = aula.getChamada().stream().map(ChamadaAulaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(chamada);
    }

    @PostMapping("/{id}/chamada")
    public ResponseEntity<ChamadaAulaDTO> inserChamada(@PathVariable Long id, @RequestBody Chamada chamada){
        Aula aula = aulaService.findById(id);
        chamada.setId(null);
        chamada.setAula(aula);
        chamada = chamadaService.insert(chamada);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/chamada/{id}").buildAndExpand(chamada.getId()).toUri();
        return ResponseEntity.created(uri).body(new ChamadaAulaDTO(chamada));
    }

}
