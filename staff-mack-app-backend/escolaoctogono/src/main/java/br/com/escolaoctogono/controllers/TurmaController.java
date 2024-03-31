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


//    @PostMapping
//    public ResponseEntity<Turma> createTurma(
//        @ApiParam(value = "Ano da turma", required = true) @RequestParam int turmaAno,
//        @ApiParam(value = "Identificação da turma", required = true) @RequestParam String identificacaoTurma){
//            Turma turma = new Turma();
//            turma.setAno(turmaAno);
//            turma.setIdentificacao(identificacaoTurma);
//            Turma novaTurma = turmaRepository.save(turma);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(novaTurma);
//    }

    @PostMapping
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {
        // Verificar se já existe uma turma com os mesmos dados
        Turma turmaExistente = turmaRepository.findByAnoAndIdentificacao(turma.getAno(), turma.getIdentificacao());
        if (turmaExistente != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Turma já existe
        }

        // Salvar a nova turma no banco de dados
        Turma novaTurma = turmaRepository.save(turma);

        // Retornar a nova turma criada com status 201 (CREATED)
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

//    @PutMapping("/{ano}/{identificacao}")
//    public ResponseEntity<Turma> updateTurma(
//            @PathVariable(value = "ano") int ano,
//            @PathVariable(value = "identificacao") String identificacao,
//            @RequestBody Turma turma) {
//
//        // Verificar se a turma existe no banco de dados
//        Turma turmaExistente = turmaRepository.findByAnoAndIdentificacao(ano, identificacao);
//        if (turmaExistente == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Atualizar os dados da turma com base nos dados recebidos no corpo da requisição
//        turmaExistente.setAno(turma.getAno());
//        turmaExistente.setIdentificacao(turma.getIdentificacao());
//
//        // Salvar a turma atualizada no banco de dados
//        Turma turmaAtualizada = turmaRepository.save(turmaExistente);
//
//        // Retornar a turma atualizada com status 200 (OK)
//        return ResponseEntity.ok().body(turmaAtualizada);
//    }


    @PutMapping("/{ano}/{identificacao}")
    public ResponseEntity<Turma> updateTurma(@RequestBody TurmaDTO identificacaoTurmaDTO) {

        // Verificar se a turma existe no banco de dados
        Turma turmaExistente = turmaRepository.findByAnoAndIdentificacao(identificacaoTurmaDTO.getAno() , identificacaoTurmaDTO.getIdentificacao().toUpperCase());
        if (turmaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizar os dados da turma com base na identificação recebida no corpo da requisição
        turmaExistente.setIdentificacao(identificacaoTurmaDTO.getIdentificacao());

        // Salvar a turma atualizada no banco de dados
        Turma turmaAtualizada = turmaRepository.save(turmaExistente);

        // Retornar a turma atualizada com status 200 (OK)
        return ResponseEntity.ok().body(turmaAtualizada);
    }


}
