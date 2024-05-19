package br.com.escolaoctogono.controllers;

import br.com.escolaoctogono.dto.TurmaDTO;
import br.com.escolaoctogono.models.Aluno;
import br.com.escolaoctogono.models.Turma;
import br.com.escolaoctogono.repositories.TurmaRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://dt95ctjg4nmhd.cloudfront.net"})
@RestController
@RequestMapping("/api/turma")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    @GetMapping("/{ano}/{identificacao}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable(value = "ano") int ano, @PathVariable(value = "identificacao") String identificacao) {
        Turma turma = turmaRepository.findByAnoAndIdentificacao(ano, identificacao);
        if (turma == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {

        Turma turmaExistente = turmaRepository.findByAnoAndIdentificacao(turma.getAno(), turma.getIdentificacao());
        if (turmaExistente != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Turma já existe
        }

        Turma novaTurma = turmaRepository.save(turma);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
    }


    @DeleteMapping("/{ano}/{identificacao}")
    public ResponseEntity<?> deleteTurma(@PathVariable(value = "ano") int ano, @PathVariable(value = "identificacao") String identificacao) {
        Turma turma = turmaRepository.findByAnoAndIdentificacao(ano, identificacao);
        if (turma == null) {
            return ResponseEntity.notFound().build();
        }
        turmaRepository.delete(turma);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{ano}/{identificacao}")
    public ResponseEntity<Turma> updateTurma(@RequestBody TurmaDTO identificacaoTurmaDTO) {

        Turma turmaExistente = turmaRepository.findByAnoAndIdentificacao(identificacaoTurmaDTO.getAno() , identificacaoTurmaDTO.getIdentificacao().toUpperCase());
        if (turmaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        turmaExistente.setIdentificacao(identificacaoTurmaDTO.getIdentificacao());

        Turma turmaAtualizada = turmaRepository.save(turmaExistente);

        return ResponseEntity.ok().body(turmaAtualizada);
    }


}
