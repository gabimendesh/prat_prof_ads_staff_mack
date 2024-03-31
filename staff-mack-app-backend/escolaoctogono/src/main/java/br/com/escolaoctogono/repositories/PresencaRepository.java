package br.com.escolaoctogono.repositories;

import br.com.escolaoctogono.dto.PresencaProjectionImpl;
import br.com.escolaoctogono.models.Presenca;
import br.com.escolaoctogono.models.PresencaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, PresencaId> {
    List<Presenca> findById_AlunoIdentificacaoAndId_DataAndId_AulaPeriodo(String alunoIdentificacao, LocalDate data, String aulaPeriodo);

}

