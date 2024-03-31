package br.com.escolaoctogono.dto;

import br.com.escolaoctogono.controllers.PresencaProjection;
import java.time.LocalDate;

public class PresencaProjectionImpl implements PresencaProjection {
    private final String aluno;
    private final String aulaPeriodo;
    private final LocalDate data;
    private final boolean presente;
    private final int presencaMateria;

    public PresencaProjectionImpl(String aluno, String aulaPeriodo, LocalDate data, boolean presente, int presencaMateria) {
        this.aluno = aluno;
        this.aulaPeriodo = aulaPeriodo;
        this.data = data;
        this.presente = presente;
        this.presencaMateria = presencaMateria;
    }


    @Override
    public String getAluno() {
        return aluno;
    }

    @Override
    public String getAulaPeriodo() {
        return aulaPeriodo;
    }

    @Override
    public LocalDate getData() {
        return data;
    }

    @Override
    public boolean isPresente() {
        return presente;
    }


}
