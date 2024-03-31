package br.com.escolaoctogono.controllers;

import br.com.escolaoctogono.dto.AlunoDTO;
import br.com.escolaoctogono.models.Aluno;
import br.com.escolaoctogono.projections.AlunoWithMateriaCodigo;
import br.com.escolaoctogono.repositories.AlunoRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "https://dt95ctjg4nmhd.cloudfront.net"})
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;
    @GetMapping
    public List<AlunoDTO> getAllAlunosWithMateriaCodigo() {
        List<AlunoWithMateriaCodigo> alunoAulas = alunoRepository.findAllAlunosWithMateriaCodigo();

        Map<String, List<AlunoWithMateriaCodigo>> grouped = alunoAulas.stream()
                .collect(Collectors.groupingBy(AlunoWithMateriaCodigo::getIdentificacao));

        List<AlunoDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<AlunoWithMateriaCodigo>> entry : grouped.entrySet()) {
            AlunoDTO dto = new AlunoDTO();
            dto.setIdentificacao(entry.getKey());
            dto.setMateriaCodigo(entry.getValue().stream()
                    .map(AlunoWithMateriaCodigo::getMateriaCodigo)
                    .collect(Collectors.toList()));

            AlunoWithMateriaCodigo firstAluno = entry.getValue().get(0);
            dto.setNome(firstAluno.getNome());
            dto.setEmailResponsavel(firstAluno.getEmailResponsavel());
            dto.setTurmaIdentificacao(firstAluno.getTurmaIdentificacao());
            dto.setTurmaAno(firstAluno.getTurmaAno());

            result.add(dto);
        }

        return result;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable(value = "id") String id) {
        Aluno aluno = alunoRepository.findByIdentificacao(id.toUpperCase());
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping("/turma/{turmaIdentificacao}")
    public ResponseEntity<List<Aluno>> getAlunosByTurma(@PathVariable(value = "turmaIdentificacao") String turmaIdentificacao) {
        List<Aluno> alunos = alunoRepository.findByTurmaIdentificacao(turmaIdentificacao.toUpperCase());
        if (alunos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(alunos);
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@ApiParam(value = "Dados do aluno", required = true) @RequestBody AlunoDTO alunoDTO) {
        // Verificar se já existe um aluno com a identificação fornecida
        Aluno existingAluno = alunoRepository.findByIdentificacao(alunoDTO.getIdentificacao());
        if (existingAluno != null) {
            // Se já existe, retornar um erro informando que o aluno já existe
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // Se não existe, criar o novo aluno e salvar no banco de dados
        Aluno aluno = new Aluno();
        aluno.setIdentificacao(alunoDTO.getIdentificacao());
        aluno.setNome(alunoDTO.getNome());
        aluno.setEmailResponsavel(alunoDTO.getEmailResponsavel());
        aluno.setTurmaAno(alunoDTO.getTurmaAno());
        aluno.setTurmaIdentificacao(alunoDTO.getTurmaIdentificacao());

        // Salvar o novo aluno no banco de dados
        Aluno novoAluno = alunoRepository.save(aluno);

        // Retornar o novo aluno criado com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable(value = "id") String id) {
        Aluno aluno = alunoRepository.findByIdentificacao(id.toUpperCase());
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        alunoRepository.delete(aluno);
        return ResponseEntity.ok().build();
    }
}
