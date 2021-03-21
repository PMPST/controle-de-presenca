package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.dto.TurmaDTO;
import com.controlechamada.chamada.domain.entities.Aluno;
import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.services.AlunoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
@Api
public class AlunoResource {

    @Autowired
    private AlunoService service;


    @GetMapping
    @ApiOperation("Buscar todos os alunos")
    public ResponseEntity<List<Aluno>> findAll(){
        List<Aluno> alunos = service.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar aluno pelo ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "Aluno encontrado"),
            @ApiResponse(code=404, message = "Aluno não encontrado para o ID informado")
    })
    public ResponseEntity<Aluno> getById(@PathVariable @ApiParam("ID do aluno") Long id){
        Aluno aluno = service.findById(id);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    @ApiOperation("Inserir um novo aluno")
    @ApiResponses({
            @ApiResponse(code=201, message = "Aluno inserido com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação")
    })
    public ResponseEntity<Aluno> insert(@RequestBody @ApiParam("Dados do aluno") Aluno aluno){
        aluno.setId(null);
        aluno = service.insert(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(aluno);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar dados de um aluno")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Dados atualizados com sucesso"),
            @ApiResponse(code = 404, message = "Aluno não encontrado para o ID informados"),
            @ApiResponse(code = 400, message = "Erro de validação de dados")
    })
    public ResponseEntity<Void> update(@PathVariable @ApiParam("ID do aluno") Long id, @RequestBody @ApiParam("Dados do aluno") Aluno aluno){
        aluno = service.update(id, aluno);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar um aluno pelo seu ID")
    @ApiResponses({
            @ApiResponse(code=204, message = "Aluno deletado com sucesso"),
            @ApiResponse(code=404, message = "Aluno não encotrado para o ID informado"),
    })
    public ResponseEntity<Void> delete(@PathVariable @ApiParam("ID do aluno") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/presencas")
    @ApiOperation("Obter lista de presencas de um aluno")
    @ApiResponses({
            @ApiResponse(code=200, message = "Lista de presencas retornada com sucesso"),
            @ApiResponse(code=404, message = "Nenhum aluno encontrado para o ID informado")
    })
    public ResponseEntity<List<Chamada>> getPresencas(@PathVariable @ApiParam("ID do aluno") Long id){
        Aluno aluno = service.findById(id);
        return ResponseEntity.ok(aluno.getPresencas());
    }

    @GetMapping("/{id}/turma")
    @ApiOperation("Obter a turma de um aluno")
    @ApiResponses({
            @ApiResponse(code=200, message = "Turma do aluno encontrada"),
            @ApiResponse(code=404, message = "Nenhum aluno encotrado para o id informado")
    })
    public ResponseEntity<TurmaDTO> getTurma(@PathVariable @ApiParam("ID do aluno") Long id){
        Aluno aluno = service.findById(id);
        TurmaDTO turma = new TurmaDTO(aluno.getTurma());
        return ResponseEntity.ok(turma);
    }
}
