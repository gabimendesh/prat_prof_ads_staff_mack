package br.com.escolaoctogono.controllers;

import br.com.escolaoctogono.dto.LoginDTO;
import br.com.escolaoctogono.dto.ProfessorDTO;
import br.com.escolaoctogono.models.Login;
import br.com.escolaoctogono.models.Professor;
import br.com.escolaoctogono.repositories.LoginRepository;
import br.com.escolaoctogono.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "https://dt95ctjg4nmhd.cloudfront.net"})
@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    LoginRepository loginRepository;

    @GetMapping
    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @GetMapping("/{identificacao}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable(value = "identificacao") String identificacao) {
        Professor professor = professorRepository.findByIdentificacao(identificacao);
        if (professor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(professor);
    }


    @GetMapping("/{user}/{password}")
    public ResponseEntity<Professor> getLoginByUserAndPassword(@PathVariable(value = "user") String usuario, @PathVariable(value = "password") String senha) {
        Login login = loginRepository.findByUsuarioAndSenha(usuario, senha);
        if (login == null) {
            return ResponseEntity.notFound().build();
        }
        Professor professor = professorRepository.findByIdentificacao(login.getIdentificacaoProfessor());
        if (professor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(professor);
    }

    @PostMapping("/login")
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {

        Login existingLogin = loginRepository.findByIdentificacao(login.getIdentificacaoProfessor());
        if (existingLogin != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Conflito - Login já existe
        }

        login.setUsuario(login.getUsuario());
        login.setIdentificacaoProfessor(login.getIdentificacaoProfessor());
        login.setSenha(login.getSenha());

        Login novoLogin = loginRepository.save(login);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoLogin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Login> updateLoginPassword(@RequestBody Login login) {

        login.setSenha(login.getSenha());

        Login loginAtualizado = loginRepository.save(login);

        return ResponseEntity.ok().body(loginAtualizado);
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO professorDTO) {

        if (professorDTO.getIdentificacao() == null || professorDTO.getIdentificacao().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Professor professorExistente = professorRepository.findByIdentificacao(professorDTO.getIdentificacao().toUpperCase());
        if (professorExistente != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Professor novoProfessor = new Professor();
        novoProfessor.setIdentificacao(professorDTO.getIdentificacao().toUpperCase());
        novoProfessor.setNome(professorDTO.getNome());
        novoProfessor.setEmail(professorDTO.getEmail());

        Professor professorSalvo = professorRepository.save(novoProfessor);

        return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Professor> updateProfessor(@RequestBody ProfessorDTO professorDTO) {

        Professor professorExistente = professorRepository.findByIdentificacao(professorDTO.getIdentificacao().toUpperCase());
        if (professorExistente == null) {
            return ResponseEntity.notFound().build();
        }

        professorExistente.setNome(professorDTO.getNome());
        professorExistente.setEmail(professorDTO.getEmail());

        Professor professorAtualizado = professorRepository.save(professorExistente);

        return ResponseEntity.ok().body(professorAtualizado);
    }


    @DeleteMapping("/{identificacao}")
    public ResponseEntity<?> deleteProfessor(@PathVariable(value = "identificacao") String identificacao) {
        Professor professor = professorRepository.findByIdentificacao(identificacao);
        if (professor == null) {
            return ResponseEntity.notFound().build();
        }
        professorRepository.delete(professor);
        return ResponseEntity.ok().build();
    }

}
