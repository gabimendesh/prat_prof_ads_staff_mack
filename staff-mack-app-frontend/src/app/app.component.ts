import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'staff-mack-app-frontend';
  urlRoute: string = '';

  constructor() {}

  ngOnInit(): void {
    const route = window.location.pathname;
    this.urlRoute = route.split('/')[1];
  }
}
