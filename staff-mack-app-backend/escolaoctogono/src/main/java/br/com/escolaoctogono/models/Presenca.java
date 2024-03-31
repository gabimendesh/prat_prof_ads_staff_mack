package br.com.escolaoctogono.models;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;

@Entity
@Table(name = "presenca")
public class Presenca {

    @EmbeddedId
    @JsonUnwrapped
    private PresencaId id;
    @Column(name = "Presente", nullable = false)
    private boolean presente;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Turma_Ano", referencedColumnName = "Ano", insertable = false, updatable = false),
            @JoinColumn(name = "Turma_Identificacao", referencedColumnName = "Identificacao", insertable = false, updatable = false)
    })
    private Turma turma;

    @Transient
    private int presencaMateria;
    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public void setId(PresencaId id) {
        this.id = id;
    }

    public int getPresencaMateria(){
        return presencaMateria;
    }

}
