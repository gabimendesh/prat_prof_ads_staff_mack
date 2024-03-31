package br.com.escolaoctogono.models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "turma")
@IdClass(TurmaId.class)
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int ano;

    @Id
    private String identificacao;

    @OneToMany(mappedBy = "turma")
    private List<Presenca> presencas;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

}
