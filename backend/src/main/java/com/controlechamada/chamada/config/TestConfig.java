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

        Aluno aluno = new Aluno(null, "João Queiroz", LocalDate.now(), null, LocalDate.now(), t);
        aluno = alunoRepository.save(aluno);

        Aula a1 = new Aula(null, "Aula 01 - Teste de GET", LocalDate.now(), t);
        Aula a2 = new Aula(null, "Aula 02 - Teste de POST", LocalDate.now().plusDays(1), t);
        aulaRepository.saveAll(Arrays.asList(a1, a2));


        Chamada c1 = new Chamada(null, true, a1.getData(), a1, aluno);
        Chamada c2 = new Chamada(null, false, a2.getData(), a2, aluno);
        chamadaRepository.saveAll(Arrays.asList(c1, c2));


        aluno.getPresencas().addAll(Arrays.asList(c1, c2));
        aluno = alunoRepository.save(aluno);
    }


}
