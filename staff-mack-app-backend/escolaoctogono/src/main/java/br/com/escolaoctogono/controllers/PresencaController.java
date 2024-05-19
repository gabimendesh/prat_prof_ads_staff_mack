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

    @PostMapping("/presenca")
    public ResponseEntity<Presenca> createPresenca(@RequestBody PresencaDTO presencaDTO) {

        List<Presenca> presencasExistentes = presencaRepository.findById_AlunoIdentificacaoAndId_DataAndId_AulaPeriodo(presencaDTO.getAluno(), presencaDTO.getData(), presencaDTO.getAulaPeriodo());

        if (!presencasExistentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

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

        Presenca presencaSalva = presencaRepository.save(novaPresenca);

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

        Optional<Presenca> presencaOptional = presencaRepository.findById(new PresencaId(presencaDTO.getAluno(), presencaDTO.getData(), presencaDTO.getTurmaAno(), presencaDTO.getTurmaIdentificacao(), presencaDTO.getProfessor(), presencaDTO.getAulaPeriodo(), presencaDTO.getAulaMateria()));
        if (!presencaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Presenca presenca = presencaOptional.get();
        presenca.setPresente(presencaDTO.isPresente());

        Presenca presencaAtualizada = presencaRepository.save(presenca);

        return ResponseEntity.ok().body(presencaAtualizada);
    }
}
