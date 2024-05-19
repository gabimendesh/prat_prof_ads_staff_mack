package br.com.escolaoctogono.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TurmaId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "Ano", nullable = false)
    private int ano;

    @Column(name = "Identificacao", nullable = false)
    private String identificacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurmaId turmaId = (TurmaId) o;
        return ano == turmaId.ano &&
                Objects.equals(identificacao, turmaId.identificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ano, identificacao);
    }
}
