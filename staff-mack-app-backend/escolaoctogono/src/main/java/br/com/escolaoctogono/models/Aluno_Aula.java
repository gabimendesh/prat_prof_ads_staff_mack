package br.com.escolaoctogono.models;

import javax.persistence.*;

@Entity
@Table(name = "aluno_aula")
public class Aluno_Aula {

    @Id
    @Column(name = "Aluno_Identificacao")
    private String alunoIdentificacao;
    @Column(name = "Materia_Codigo")
    private int materiaCodigo;

    public String getAlunoIdentificacao() {
        return alunoIdentificacao;
    }

    public void setAlunoIdentificacao(String alunoIdentificacao) {
        this.alunoIdentificacao = alunoIdentificacao;
    }

    public int getMateriaCodigo() {
        return materiaCodigo;
    }

    public void setMateriaCodigo(int materiaCodigo) {
        this.materiaCodigo = materiaCodigo;
    }
}
