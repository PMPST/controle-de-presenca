package com.controlechamada.chamada.resources;

import com.controlechamada.chamada.domain.entities.Aluno;
import com.controlechamada.chamada.domain.entities.Aula;
import com.controlechamada.chamada.domain.entities.Turma;
import com.controlechamada.chamada.services.AulaService;
import com.controlechamada.chamada.services.TurmaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation("Buscar todas as turmas")
    @ApiResponses({
            @ApiResponse(code=200, message = "Lista de turmas cadastradas")
    })
    public ResponseEntity<List<Turma>> findAll(){
        List<Turma> turmas = turmaService.findAll();
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar turma por ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "Turma encotrada"),
            @ApiResponse(code=404, message = "Nenhuma turma encontrada para o ID informado")
    })
    public ResponseEntity<Turma> findById(@PathVariable @ApiParam("ID da turma") Long id){
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    @ApiOperation("Cadastrtar uma nova turma")
    @ApiResponses({
            @ApiResponse(code=201, message = "Turma cadastrada com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação de dados")
    })
    public ResponseEntity<Turma> insert(@RequestBody @ApiParam("Dados da turma") Turma turma){
        turma.setId(null);
        turma = turmaService.insert(turma);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(turma);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar dados de uma turma")
    @ApiResponses({
            @ApiResponse(code=204, message = "Dados atualizados com sucesso"),
            @ApiResponse(code=400, message = "Erro de validação de dados"),
            @ApiResponse(code=404, message = "Nenhuma turma encontrada para o ID informado")
    })
    public ResponseEntity<Void> update(@PathVariable @ApiParam("ID da turma") Long id, @RequestBody @ApiParam("Dados da turma") Turma turma){
        turma = turmaService.update(id, turma);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletando uma turma pelo ID")
    @ApiResponses({
            @ApiResponse(code=204, message = "Turma deletada com sucesso"),
            @ApiResponse(code=404, message = "Nenhuma turma encontrada para o ID informado")
    })
    public ResponseEntity<Void> delete(@PathVariable @ApiParam("ID da turma") Long id){
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}/aulas")
    @ApiOperation("Buscando as aulas de um turma")
    @ApiResponses({
            @ApiResponse(code=200, message = "Lista de aulas para a turma informada"),
            @ApiResponse(code=404, message = "Nenhuma turma encontrada para o ID informado")
    })
    public ResponseEntity<List<Aula>> findAllAulasByTurma(@PathVariable @ApiParam("ID da turma") Long id){
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma.getAulas());
    }

    @PostMapping("/{id}/aulas")
    @ApiOperation("Cadastrando uma aula na turma")
    @ApiResponses({
            @ApiResponse(code=201, message = "Turma encotrada"),
            @ApiResponse(code=400, message = "Erro de validação de dados"),
            @ApiResponse(code=404, message = "Nenhuma turma encontrada para o ID informado")
    })
    public ResponseEntity<Aula> insertAula(@PathVariable @ApiParam("ID da turma") Long id, @RequestBody @ApiParam("Dados da aula") Aula aula){
        Turma t = turmaService.findById(id);
        aula.setId(null);
        aula.setTurma(t);
        aula = aulaService.insert(aula);
        return ResponseEntity.ok(aula);
    }

    @GetMapping("/{id}/alunos")
    @ApiOperation("Buscando alunos de uma turma")
    @ApiResponses({
            @ApiResponse(code=200, message = "Alunos da turma para o ID informado"),
            @ApiResponse(code=404, message = "Nenhuma turma encontrada para o ID informado")
    })
    public ResponseEntity<Set<Aluno>> findAllAlunosByTurma(@PathVariable @ApiParam("ID da Turma") Long id){
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma.getAlunos());
    }
}
