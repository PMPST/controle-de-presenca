package com.controlechamada.chamada.config;

import com.controlechamada.chamada.domain.entities.Aluno;
import com.controlechamada.chamada.domain.entities.Aula;
import com.controlechamada.chamada.domain.entities.Chamada;
import com.controlechamada.chamada.domain.entities.Turma;
import com.controlechamada.chamada.repositories.AlunoRepository;
import com.controlechamada.chamada.repositories.AulaRepository;
import com.controlechamada.chamada.repositories.ChamadaRepository;
import com.controlechamada.chamada.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig  implements CommandLineRunner {
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private ChamadaRepository chamadaRepository;




    @Override
    public void run(String... args) throws Exception {
        Turma t = new Turma(null, "2TDST", LocalDate.now());
        t = turmaRepository.save(t);

        Aluno aluno = new Aluno(null, "João Queiroz", LocalDate.now(), "", LocalDate.now(), t);
        aluno = alunoRepository.save(aluno);

        Aula aula = new Aula(null, "Aula 01 - Teste", LocalDate.now(), t);
        aula = aulaRepository.save(aula);

        Chamada chamada = new Chamada(null, true, aula.getData(), aula, aluno);
        Chamada chamada2 = new Chamada(null, false, aula.getData(), aula, aluno);

        chamada = chamadaRepository.save(chamada);
        chamada2 = chamadaRepository.save(chamada2);

        aluno.getPresencas().addAll(Arrays.asList(chamada, chamada2));


        aluno = alunoRepository.save(aluno);
    }


}
