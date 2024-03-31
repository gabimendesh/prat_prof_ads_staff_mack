package br.com.escolaoctogono.repositories;

import br.com.escolaoctogono.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Professor findByIdentificacao(String identificacao);
}
