package br.com.escolaoctogono.models;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @Column(name = "Identificacao")
    private String identificacao;


    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Email")
    private String email;

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
