package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.services.ChamadaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chamadas")
public class ChamadaResource {
    @Autowired
    private ChamadaService service;


    @GetMapping("/{id}")
    @ApiOperation("Retornando uma chamada por ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "Chamada encontrada"),
            @ApiResponse(code=404, message = "Nenhuma chamada encontrada para o ID informado")
    })
    public ResponseEntity<Chamada> getById(@PathVariable @ApiParam("ID da chamada") Long id){
        Chamada chamada = service.findById(id);
        return ResponseEntity.ok(chamada);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizando dados de uma chamada")
    @ApiResponses({
            @ApiResponse(code=204, message = "Dados atualizados com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação de dados"),
            @ApiResponse(code=404, message = "Nenhuma chamada encontrada para o ID informado")
    })
    public ResponseEntity<Void> update(@PathVariable @ApiParam("ID da chamada") Long id, @RequestBody Chamada chamada){
        service.update(id, chamada);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletando uma chamada por ID")
    @ApiResponses({
            @ApiResponse(code=204, message = "Chamada deletada com sucesso"),
            @ApiResponse(code=404, message = "Nenhuma chamada encontrada para o ID informado")
    })
    public ResponseEntity<Void> delete(@PathVariable @ApiParam("ID da chamada") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
