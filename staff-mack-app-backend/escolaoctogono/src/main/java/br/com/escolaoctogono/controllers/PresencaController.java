package br.com.escolaoctogono.controllers;

import br.com.escolaoctogono.dto.PresencaDTO;
import br.com.escolaoctogono.models.Presenca;
import br.com.escolaoctogono.models.PresencaId;
import br.com.escolaoctogono.repositories.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "https://dt95ctjg4nmhd.cloudfront.net"})
@RestController
@RequestMapping("/api/presencas")
public class PresencaController {

    @Autowired
    private PresencaRepository presencaRepository;

    @GetMapping
    public List<Presenca> getAllPresencas() {
        return presencaRepository.findAll();
    }

//    @PostMapping
//    public ResponseEntity<Presenca> createPresenca(
//            @RequestParam String aluno,
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate data,
//            @RequestParam int turmaAno,
//            @RequestParam String turmaIdentificacao,
//            @RequestParam String professor,
//            @RequestParam String aulaPeriodo,
//            @RequestParam int aulaMateria,
//            @RequestParam boolean presente) {
//
//        // Criar uma nova instância de PresencaId
//        PresencaId id = new PresencaId();
//        id.setAlunoIdentificacao(aluno.toUpperCase());
//        id.setData(data);
//        id.setTurmaAno(turmaAno);
//        id.setTurmaIdentificacao(turmaIdentificacao.toUpperCase());
//        id.setPresencaProfessor(professor.toUpperCase());
//        id.setAulaPeriodo(aulaPeriodo);
//        id.setPresencaMateria(aulaMateria);
//
//        // Criar uma nova instância de Presenca e definir o ID e a presença
//        Presenca novaPresenca = new Presenca();
//        novaPresenca.setId(id);
//        novaPresenca.setPresente(presente);
//
//        // Salvar a nova presença no banco de dados
//        Presenca presencaSalva = presencaRepository.save(novaPresenca);
//
//        // Retornar a resposta com a presença salva e o status CREATED
//        return ResponseEntity.status(HttpStatus.CREATED).body(presencaSalva);
//    }


//    @PostMapping
//    public ResponseEntity<Presenca> createPresenca(@RequestBody PresencaDTO presencaDTO) {
//        // Extrair os dados do DTO e criar uma nova instância de Presenca
//        PresencaId id = new PresencaId();
//        id.setAlunoIdentificacao(presencaDTO.getAluno().toUpperCase());
//        id.setData(presencaDTO.getData());
//        id.setTurmaAno(presencaDTO.getTurmaAno());
//        id.setTurmaIdentificacao(presencaDTO.getTurmaIdentificacao().toUpperCase());
//        id.setPresencaProfessor(presencaDTO.getProfessor().toUpperCase());
//        id.setAulaPeriodo(presencaDTO.getAulaPeriodo());
//        id.setPresencaMateria(presencaDTO.getAulaMateria());
//
//        Presenca novaPresenca = new Presenca();
//        novaPresenca.setId(id);
//        novaPresenca.setPresente(presencaDTO.isPresente());
//
//        // Salvar a nova presença no banco de dados
//        Presenca presencaSalva = presencaRepository.save(novaPresenca);
//
//        // Retornar a resposta com a presença salva e o status CREATED
//        return ResponseEntity.status(HttpStatus.CREATED).body(presencaSalva);
//    }

    @PostMapping("/presenca")
    public ResponseEntity<Presenca> createPresenca(@RequestBody PresencaDTO presencaDTO) {
        // Verificar se já existe uma presença com os mesmos parâmetros
        List<Presenca> presencasExistentes = presencaRepository.findById_AlunoIdentificacaoAndId_DataAndId_AulaPeriodo(presencaDTO.getAluno(), presencaDTO.getData(), presencaDTO.getAulaPeriodo());

        if (!presencasExistentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Conflito - Presença já existe
        }

        // Extrair os dados do DTO e criar uma nova instância de Presenca
        PresencaId id = new PresencaId();
        id.setAlunoIdentificacao(presencaDTO.getAluno().toUpperCase());
        id.setData(presencaDTO.getData());
        id.setTurmaAno(presencaDTO.getTurmaAno());
        id.setTurmaIdentificacao(presencaDTO.getTurmaIdentificacao().toUpperCase());
        id.setPresencaProfessor(presencaDTO.getProfessor().toUpperCase());
        id.setAulaPeriodo(presencaDTO.getAulaPeriodo());
        id.setPresencaMateria(presencaDTO.getAulaMateria());

        Presenca novaPresenca = new Presenca();
        novaPresenca.setId(id);
        novaPresenca.setPresente(presencaDTO.isPresente());

        // Salvar a nova presença no banco de dados
        Presenca presencaSalva = presencaRepository.save(novaPresenca);

        // Retornar a resposta com a presença salva e o status CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(presencaSalva);
    }


    @DeleteMapping("/{aluno}/{data}/{aulaPeriodo}")
    public ResponseEntity<?> deletePresenca(
            @PathVariable String aluno,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @PathVariable String aulaPeriodo) {

        List<Presenca> presencas = presencaRepository.findById_AlunoIdentificacaoAndId_DataAndId_AulaPeriodo(aluno, data, aulaPeriodo);
        if (presencas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        presencaRepository.deleteAll(presencas);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Presenca> updatePresenca(@RequestBody PresencaDTO presencaDTO) {
        // Verificar se a presença existe no banco de dados
        Optional<Presenca> presencaOptional = presencaRepository.findById(new PresencaId(presencaDTO.getAluno(), presencaDTO.getData(), presencaDTO.getTurmaAno(), presencaDTO.getTurmaIdentificacao(), presencaDTO.getProfessor(), presencaDTO.getAulaPeriodo(), presencaDTO.getAulaMateria()));
        if (!presencaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Atualizar o status de presença
        Presenca presenca = presencaOptional.get();
        presenca.setPresente(presencaDTO.isPresente());

        // Salvar a presença atualizada no banco de dados
        Presenca presencaAtualizada = presencaRepository.save(presenca);

        // Retornar a presença atualizada e o status OK
        return ResponseEntity.ok().body(presencaAtualizada);
    }


//
//    {
//        "data": "2025-02-02",
//            "turmaAno": 2024,
//            "turmaIdentificacao": "T1",
//            "presencaProfessor": "P1",
//            "aulaPeriodo": "1",
//            "presencaMateria": 101,
//            "alunoIdentificacao": "A1",
//            "presente": true
//    }

}
