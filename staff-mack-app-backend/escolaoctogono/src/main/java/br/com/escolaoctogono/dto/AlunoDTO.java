package br.com.escolaoctogono.dto;

import java.util.List;

public class AlunoDTO {
    private String identificacao;
    private List<Integer> materiaCodigo;

    private String nome;
    private String emailResponsavel;
    private String turmaIdentificacao;
    private int turmaAno;

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao != null ? identificacao.toUpperCase() : null;
    }

    public List<Integer> getMateriaCodigo() {
        return materiaCodigo;
    }

    public void setMateriaCodigo(List<Integer> materiaCodigo) {
        this.materiaCodigo = materiaCodigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

    public String getTurmaIdentificacao() {
        return turmaIdentificacao;
    }

    public void setTurmaIdentificacao(String turmaIdentificacao) {
        this.turmaIdentificacao = turmaIdentificacao != null ? turmaIdentificacao.toUpperCase() : null;
    }

    public int getTurmaAno() {
        return turmaAno;
    }

    public void setTurmaAno(int turmaAno) {
        this.turmaAno = turmaAno;
    }
}
