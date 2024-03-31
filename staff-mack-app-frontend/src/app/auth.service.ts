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
}
