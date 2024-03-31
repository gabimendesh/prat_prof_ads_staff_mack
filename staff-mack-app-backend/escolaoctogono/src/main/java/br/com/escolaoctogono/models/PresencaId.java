package br.com.escolaoctogono.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class PresencaId implements Serializable {

    @Column(name = "Aluno")
    private String alunoIdentificacao;

    @Column(name = "Data")
    private LocalDate data;

    @Column(name = "Turma_Ano")
    private int turmaAno;

    @Column(name = "Turma_Identificacao")
    private String turmaIdentificacao;

    @Column(name = "Professor")
    private String presencaProfessor;
    @Column(name = "Aula_Periodo")
    private String aulaPeriodo;

    @Column(name = "Aula_Materia")
    private int presencaMateria;


    public PresencaId(String aluno, LocalDate data, int turmaAno, String turmaIdentificacao, String professor, String aulaPeriodo, int aulaMateria) {
        this. alunoIdentificacao = aluno;
        this.data = data;
        this.turmaAno = turmaAno;
        this.turmaIdentificacao = turmaIdentificacao;
        this.presencaProfessor = professor;
        this.aulaPeriodo = aulaPeriodo;
        this.presencaMateria = aulaMateria;
    }

    public int getTurmaAno() {
        return turmaAno;
    }

    public PresencaId(String alunoIdentificacao, LocalDate data, String aulaPeriodo) {
        this.alunoIdentificacao = alunoIdentificacao;
        this.data = data;
        this.aulaPeriodo = aulaPeriodo;
    }

    public PresencaId(){

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

    public String getPresencaProfessor() {
        return presencaProfessor;
    }

    public void setPresencaProfessor(String presencaProfessor) {
        this.presencaProfessor = presencaProfessor;
    }

    public int getPresencaMateria() {
        return presencaMateria;
    }

    public void setPresencaMateria(int presencaMateria) {
        this.presencaMateria = presencaMateria;
    }

    @JsonProperty("alunoIdentificacao")
    public String getAlunoIdentificacao() {
        return alunoIdentificacao;
    }

    public void setAlunoIdentificacao(String alunoIdentificacao) {
        this.alunoIdentificacao = alunoIdentificacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getAulaPeriodo() {
        return aulaPeriodo;
    }

    public void setAulaPeriodo(String aulaPeriodo) {
        this.aulaPeriodo = aulaPeriodo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresencaId that = (PresencaId) o;
        return Objects.equals(alunoIdentificacao, that.alunoIdentificacao) && Objects.equals(data, that.data) && Objects.equals(aulaPeriodo, that.aulaPeriodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alunoIdentificacao, data, aulaPeriodo);
    }

}
