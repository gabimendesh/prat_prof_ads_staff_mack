package br.com.escolaoctogono.dto;

import java.util.List;

public class AlunoPresencaDTO {

    private String identificacao;
    private List<String> materiaCodigo;
    private String nome;
    private String emailResponsavel;
    private String turmaIdentificacao;
    private int turmaAno;
    private int totalDias;
    private long totalFaltas;
    private long totalPresencas;


    public AlunoPresencaDTO() {
    }

    public AlunoPresencaDTO(String identificacao, List<String> materiaCodigo, String nome, String emailResponsavel,
                            String turmaIdentificacao, int turmaAno, int totalDias, long totalFaltas, long totalPresencas) {
        this.identificacao = identificacao;
        this.materiaCodigo = materiaCodigo;
        this.nome = nome;
        this.emailResponsavel = emailResponsavel;
        this.turmaIdentificacao = turmaIdentificacao;
        this.turmaAno = turmaAno;
        this.totalDias = totalDias;
        this.totalFaltas = totalFaltas;
        this.totalPresencas = totalPresencas;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public List<String> getMateriaCodigo() {
        return materiaCodigo;
    }

    public void setMateriaCodigo(List<String> materiaCodigo) {
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
        this.turmaIdentificacao = turmaIdentificacao;
    }

    public int getTurmaAno() {
        return turmaAno;
    }

    public void setTurmaAno(int turmaAno) {
        this.turmaAno = turmaAno;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public long getTotalFaltas() {
        return totalFaltas;
    }

    public void setTotalFaltas(long totalFaltas) {
        this.totalFaltas = totalFaltas;
    }

    public long getTotalPresencas() {
        return totalPresencas;
    }

    public void setTotalPresencas(long totalPresencas) {
        this.totalPresencas = totalPresencas;
    }
}
