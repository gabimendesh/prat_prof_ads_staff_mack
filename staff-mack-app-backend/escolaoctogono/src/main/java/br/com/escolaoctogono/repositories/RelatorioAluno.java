package br.com.escolaoctogono.repositories;

import br.com.escolaoctogono.models.Aluno;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class RelatorioAluno {

    @Id
    @Column(name = "Aluno_Identificacao")
    private Aluno aluno;
    @Column(name = "Turma_Identificacao")

    private String turma;
    @Column(name = "Ano")

    private int ano;
    @Column(name = "Professor")

    private String professor;
    @Column(name = "Aula_Materia")
    private String disciplina;
    private Date data;
    @Column(name = "Aula_Periodo")

    private String periodo;
    @Column(name = "Frequencia")

    private double frequencia;

    public RelatorioAluno(Aluno aluno, String turma, int ano, String professor, String disciplina, Date data, String periodo, double frequencia) {
        this.aluno = aluno;
        this.turma = turma;
        this.ano = ano;
        this.professor = professor;
        this.disciplina = disciplina;
        this.data = data;
        this.periodo = periodo;
        this.frequencia = frequencia;
    }

}
