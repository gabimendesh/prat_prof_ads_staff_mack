import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) { }

  login(user: string, password: string): Observable<any> {
    const url = `https://44.205.12.176:443/api/professores/${user}/${password}`;
    return this.http.get(url);
  }

  handleLogin(user: string, password: string) {
    this.login(user, password).subscribe(
      (response: any) => {
        if (response.email === user) {
          localStorage.setItem('professor', response.nome);
          localStorage.setItem('user_logged', 'true');
          this.router.navigate(['/controle-presenca']);
        } else {
          this.router.navigate(['/recuperar-acesso']);
        }
      },
      (error) => {
        console.error('Erro ao fazer login:', error);
        this.router.navigate(['/recuperar-acesso']);
      }
    );
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('user_logged');
  }

  isAuthenticated(): boolean {
    // Verifique se o usuário está autenticado (implementação fictícia)
    // Aqui você pode verificar se o token está presente ou se o usuário está logado de outra maneira
    return localStorage.getItem('user_logged') !== null; // Verifica se há um token no localStorage
  }

  
  logout(): void {
    // Lógica de logout (geralmente limpeza de dados do usuário e redirecionamento para a página de login)
    localStorage.removeItem('user_logged'); // Remove o token do localStorage
    localStorage.removeItem('professor');
    this.router.navigate(['/login']); // Redireciona para a página de login
  }

}
