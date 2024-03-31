import { Component } from '@angular/core';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(username: string, password: string) {
    const loginData = {
      username: username,
      password: password
    };

    this.authService.login(loginData).subscribe(
      (response) => {
        this.router.navigate(['/controle-presenca']);
      },
      (error) => {
        this.router.navigate(['/recuperar-acesso']);
      }
    );
  }
}
