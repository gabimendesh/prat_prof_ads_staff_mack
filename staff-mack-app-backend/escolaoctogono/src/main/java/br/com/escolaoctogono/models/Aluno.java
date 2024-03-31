package br.com.escolaoctogono.models;

import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @Column(name = "Identificacao")
    private String identificacao;

    @Transient
    private Integer identificacaoNumero;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Email_Responsavel")
    private String emailResponsavel;

    @Column(name = "Turma_Ano")
    private int turmaAno;

    @Column(name = "Turma_Identificacao")
    private String turmaIdentificacao;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Turma_Ano", referencedColumnName = "Ano", insertable = false, updatable = false),
            @JoinColumn(name = "Turma_Identificacao", referencedColumnName = "Identificacao", insertable = false, updatable = false)
    })
    private Turma turma;

    @PostLoad
    private void onLoad(){
        this.identificacaoNumero = Integer.parseInt(this.identificacao.substring(1));
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {

        this.identificacao = identificacao != null ? identificacao.toUpperCase() : null;
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

    public int getTurmaAno() {
        return turmaAno;
    }

    public void setTurmaAno(int turmaAno) {
        this.turmaAno = turmaAno;
    }

    public String getTurmaIdentificacao() {
        return turmaIdentificacao;
    }

    public void setTurmaIdentificacao(String turmaIdentificacao) {

        this.turmaIdentificacao = turmaIdentificacao != null ? turmaIdentificacao.toUpperCase() : null;
    }

    // Getters and setters for Turma omitted for brevity
}
