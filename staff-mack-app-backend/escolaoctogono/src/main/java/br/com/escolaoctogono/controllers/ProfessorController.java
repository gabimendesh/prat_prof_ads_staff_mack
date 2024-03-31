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
            return ResponseEntity.notFound().build(); // Usuário e/ou senha inválidos
        }
        Professor professor = professorRepository.findByIdentificacao(login.getIdentificacaoProfessor());
        if (professor == null) {
            return ResponseEntity.notFound().build(); // Identificação de professor não encontrada
        }
        return ResponseEntity.ok().body(professor);
    }

    @PostMapping("/login")
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        // Verificar se já existe um login com o mesmo nome de usuário ou o mesmo professor
        Login existingLogin = loginRepository.findByIdentificacao(login.getIdentificacaoProfessor());
        if (existingLogin != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Conflito - Login já existe
        }

        // Crie um novo objeto Login
        login.setUsuario(login.getUsuario());
        login.setIdentificacaoProfessor(login.getIdentificacaoProfessor());
        login.setSenha(login.getSenha());

        // Salve o novo login no banco de dados
        Login novoLogin = loginRepository.save(login);

        // Retorne uma resposta indicando o sucesso da operação
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLogin);
    }

//    @PutMapping("/login/{id}/{user}/{password}")
//    public ResponseEntity<Login> updateLogin(@RequestBody Login login) {
//        // Atualize os dados do login com base nos dados recebidos
//        login.setIdentificacaoProfessor(login.getIdentificacaoProfessor());
//        login.setUsuario(login.getUsuario());
//        login.setSenha(login.getSenha());
//
//        // Salve o login atualizado no banco de dados
//        Login loginAtualizado = loginRepository.save(login);
//
//        // Retorne uma resposta indicando o sucesso da operação
//        return ResponseEntity.ok().body(loginAtualizado);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Login> updateLoginPassword(@RequestBody Login login) {
        // Atualize os dados do login com base nos dados recebidos
        login.setSenha(login.getSenha());

        // Salve o login atualizado no banco de dados
        Login loginAtualizado = loginRepository.save(login);

        // Retorne uma resposta indicando o sucesso da operação
        return ResponseEntity.ok().body(loginAtualizado);
    }

//    @PostMapping
//    public ResponseEntity<Professor> createProfessor(
//            @ApiParam(value = "Identificação do professor", required = true) @RequestParam String identificacao,
//            @ApiParam(value = "Nome do professor", required = true) @RequestParam String nome,
//            @ApiParam(value = "E-mail do professor", required = true) @RequestParam String email) {
//
//        // Verificar se a identificação está presente
//        if (identificacao == null || identificacao.isEmpty()) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        Professor professor = new Professor();
//        professor.setIdentificacao(identificacao);
//        professor.setNome(nome);
//        professor.setEmail(email);
//
//        // Salvar o novo professor no banco de dados
//        Professor novoProfessor = professorRepository.save(professor);
//
//        // Retornar o novo professor criado com status 201 (CREATED)
//        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
//    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO professorDTO) {
        // Verificar se a identificação está presente
        if (professorDTO.getIdentificacao() == null || professorDTO.getIdentificacao().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Verificar se já existe um professor com a mesma identificação
        Professor professorExistente = professorRepository.findByIdentificacao(professorDTO.getIdentificacao().toUpperCase());
        if (professorExistente != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Identificação de professor já existe
        }

        // Criar um novo professor
        Professor novoProfessor = new Professor();
        novoProfessor.setIdentificacao(professorDTO.getIdentificacao().toUpperCase());
        novoProfessor.setNome(professorDTO.getNome());
        novoProfessor.setEmail(professorDTO.getEmail());

        // Salvar o novo professor no banco de dados
        Professor professorSalvo = professorRepository.save(novoProfessor);

        // Retornar o novo professor criado com status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(professorSalvo);
    }


//    @PutMapping("/{identificacao}")
//    public ResponseEntity<Professor> updateProfessor(
//            @PathVariable(value = "identificacao") String identificacao,
//            @RequestBody ProfessorDTO professorDTO) {
//
//        // Verificar se o professor existe no banco de dados
//        Professor professorExistente = professorRepository.findByIdentificacao(identificacao.toUpperCase());
//        if (professorExistente == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Atualizar os dados do professor com base nos dados recebidos no corpo da requisição
//        professorExistente.setNome(professorDTO.getNome());
//        professorExistente.setEmail(professorDTO.getEmail());
//
//        // Salvar o professor atualizado no banco de dados
//        Professor professorAtualizado = professorRepository.save(professorExistente);
//
//        // Retornar o professor atualizado com status 200 (OK)
//        return ResponseEntity.ok().body(professorAtualizado);
//    }


    //    @PutMapping("/atualizar/{identificacao}")
//    public ResponseEntity<Professor> updateProfessor(
//            @PathVariable(value = "identificacao") String identificacao,
//            @RequestBody ProfessorDTO professorDTO) {
//
//        // Verificar se o professor existe no banco de dados
//        Professor professorExistente = professorRepository.findByIdentificacao(identificacao.toUpperCase());
//        if (professorExistente == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Atualizar os dados do professor com base nos dados recebidos no corpo da requisição
//        professorExistente.setNome(professorDTO.getNome());
//        professorExistente.setEmail(professorDTO.getEmail());
//
//        // Salvar o professor atualizado no banco de dados
//        Professor professorAtualizado = professorRepository.save(professorExistente);
//
//        // Retornar o professor atualizado com status 200 (OK)
//        return ResponseEntity.ok().body(professorAtualizado);
//    }
    @PutMapping("/atualizar")
    public ResponseEntity<Professor> updateProfessor(@RequestBody ProfessorDTO professorDTO) {
        // Verificar se o professor existe no banco de dados
        Professor professorExistente = professorRepository.findByIdentificacao(professorDTO.getIdentificacao().toUpperCase());
        if (professorExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualizar os dados do professor com base nos dados recebidos no corpo da requisição
        professorExistente.setNome(professorDTO.getNome());
        professorExistente.setEmail(professorDTO.getEmail());

        // Salvar o professor atualizado no banco de dados
        Professor professorAtualizado = professorRepository.save(professorExistente);

        // Retornar o professor atualizado com status 200 (OK)
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
