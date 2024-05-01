import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'staff-mack-app-frontend';

  constructor(
    private authService: AuthService
  ) {}

  ngOnInit(): void {

  }

  isLoggedIn(): boolean {
    return this.authService.isAuthenticated();
  }
}
