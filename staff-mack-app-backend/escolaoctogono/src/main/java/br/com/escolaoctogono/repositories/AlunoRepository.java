package br.com.escolaoctogono.repositories;

import br.com.escolaoctogono.models.Aluno;
import br.com.escolaoctogono.projections.AlunoWithMateriaCodigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Aluno findByIdentificacao(String identificacao);
    void deleteByIdentificacao(String identificacao);
    List<Aluno> findByTurmaIdentificacao(String turmaIdentificacao);

    @Query("SELECT a.identificacao as identificacao, a.nome as nome, a.emailResponsavel as emailResponsavel, " +
            "a.turmaAno as turmaAno, a.turmaIdentificacao as turmaIdentificacao, aa.materiaCodigo as materiaCodigo " +
            "FROM Aluno a, Aluno_Aula aa WHERE a.identificacao = aa.alunoIdentificacao")
    List<AlunoWithMateriaCodigo> findAllAlunosWithMateriaCodigo();

}
