package br.com.escolaoctogono.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
    @Id
    private String usuario;
    private String senha;
    @Column(name = "IdentificacaoProfessor")
    private String identificacao;


    public String getIdentificacaoProfessor() {
        return identificacao;
    }

    public void setIdentificacaoProfessor(String identificacaoProfessor) {
        this.identificacao = identificacaoProfessor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
