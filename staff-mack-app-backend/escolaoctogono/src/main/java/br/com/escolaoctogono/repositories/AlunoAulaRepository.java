package br.com.escolaoctogono.repositories;

import br.com.escolaoctogono.controllers.PresencaProjection;
import br.com.escolaoctogono.models.Aluno_Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoAulaRepository extends JpaRepository<Aluno_Aula, String> {

    @Query("SELECT DISTINCT a FROM Aluno_Aula a WHERE a.alunoIdentificacao = :alunoIdentificacao")
    List<Aluno_Aula> findByAlunoIdentificacao(String alunoIdentificacao);

    @Query("SELECT p FROM Presenca p WHERE p.id.alunoIdentificacao = :alunoIdentificacao")
    List<PresencaProjection> findPresencasByAlunoIdentificacao(String alunoIdentificacao);

}