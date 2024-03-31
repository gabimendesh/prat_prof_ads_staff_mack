package br.com.escolaoctogono.repositories;

import br.com.escolaoctogono.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
    Turma findByAnoAndIdentificacao(int ano, String identificacao);
}
