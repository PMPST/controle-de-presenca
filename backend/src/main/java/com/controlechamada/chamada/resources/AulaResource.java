package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.dto.ChamadaAulaDTO;
import com.controlechamada.chamada.domain.entities.Aula;
import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.services.AulaService;
import com.controlechamada.chamada.services.ChamadaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aulas")
@Api
public class AulaResource {
    @Autowired
    private AulaService aulaService;

    @Autowired
    private ChamadaService chamadaService;


    @GetMapping
    @ApiOperation("Buscar todas as aulas")
    @ApiResponse(code = 200, message = "Aulas encontradas")
    public ResponseEntity<List<Aula>> findAll(){
        List<Aula> aulas = aulaService.findAll();
        return ResponseEntity.ok(aulas);
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar aula pelo ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "Aula encontrada"),
            @ApiResponse(code=404, message = "Aula não encotrada para o ID informado")
    })
    public ResponseEntity<Aula> findById(@PathVariable @ApiParam("ID da aula") Long id){
        Aula aula = aulaService.findById(id);
        return ResponseEntity.ok(aula);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar informações de uma aula")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Aula atualizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação de dados")
    })
    public ResponseEntity<Void> update(@PathVariable @ApiParam("ID da aula") Long id, @RequestBody @ApiParam("Dados da aula") Aula aula){
        aulaService.update(id, aula);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar uma aula pelo ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Aula deletada com sucesso"),
            @ApiResponse(code = 404, message = "Nenhuma aula encontrada para o ID informado")
    })
    public ResponseEntity<Void> delete(@PathVariable @ApiParam("ID da aula") Long id){
        aulaService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}/chamada")
    @ApiOperation("Buscar a lista de chamada de uma aula")
    @ApiResponses({
            @ApiResponse(code=200, message = "Lista de chamada da aula"),
            @ApiResponse(code=404, message = "Nenhuma aula encontrada para o id informado")
    })
    public ResponseEntity<List<ChamadaAulaDTO>> getChamadas(@PathVariable @ApiParam("ID da aula") Long id){
        Aula aula = aulaService.findById(id);
        List<ChamadaAulaDTO> chamada = aula.getChamada().stream().map(ChamadaAulaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(chamada);
    }


    @PostMapping("/{id}/chamada")
    @ApiOperation("Realizar a chamada de uma aula")
    @ApiResponses({
            @ApiResponse(code=201, message = "Chamada criada com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação de dados"),
            @ApiResponse(code=404, message = "Nenhuma aula encontrada para o ID informado")
    })
    public ResponseEntity<ChamadaAulaDTO> inserChamada(@PathVariable @ApiParam("ID da aula") Long id, @RequestBody Chamada chamada){
        Aula aula = aulaService.findById(id);
        chamada.setId(null);
        chamada.setAula(aula);
        chamada = chamadaService.insert(chamada);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/chamada/{id}").buildAndExpand(chamada.getId()).toUri();
        return ResponseEntity.created(uri).body(new ChamadaAulaDTO(chamada));
    }

}
