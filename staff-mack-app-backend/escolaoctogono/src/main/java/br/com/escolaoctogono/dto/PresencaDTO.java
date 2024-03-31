package br.com.escolaoctogono.dto;

import java.time.LocalDate;
import java.util.List;

public class PresencaDTO {
    private String aluno;
    private LocalDate data;
    private int turmaAno;
    private String turmaIdentificacao;
    private String professor;
    private String aulaPeriodo;
    private int aulaMateria;
    private boolean presente;

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno != null? aluno.toUpperCase() : null;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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
        this.turmaIdentificacao = turmaIdentificacao;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getAulaPeriodo() {
        return aulaPeriodo;
    }

    public void setAulaPeriodo(String aulaPeriodo) {
        this.aulaPeriodo = aulaPeriodo;
    }

    public int getAulaMateria() {
        return aulaMateria;
    }

    public void setAulaMateria(int aulaMateria) {
        this.aulaMateria = aulaMateria;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}

