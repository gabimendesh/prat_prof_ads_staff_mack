package br.com.escolaoctogono.repositories;

import br.com.escolaoctogono.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByUsuarioAndSenha(String usuario, String senha);
    Login findByIdentificacao(String identificacao);

}
