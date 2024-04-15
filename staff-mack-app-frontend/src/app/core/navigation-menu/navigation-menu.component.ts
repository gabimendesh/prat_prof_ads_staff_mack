import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  QueryList,
  Renderer2,
  ViewChild,
  ViewChildren
} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'staff-mack-navigation-menu',
  templateUrl: './navigation-menu.component.html',
  styleUrl: './navigation-menu.component.scss'
})
export class NavigationMenuComponent implements OnInit, AfterViewInit {
  @ViewChild('navButton') navButton!: ElementRef;
  @ViewChildren('navButton') navButtons!: QueryList<ElementRef>;
  isActive = false;
  professorName = localStorage.getItem('professor');
  professorPhoto = localStorage.getItem('professorPhoto');
  nickname = '';
  constructor(
    private renderer: Renderer2,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    const name = this.professorName?.split(' ');
    const firstLetter = name?.[0].charAt(0);
    const lastName = name?.[name.length - 1].charAt(0);
    this.nickname = `${firstLetter}${lastName}`;

    this.professorName = `${this.professorName?.split(' ')[0]} ${name?.[name.length - 1]}` ?? null;
  }

  ngAfterViewInit(): void {
    this.navButton.nativeElement.classList.add('active');
    this.navButtons.forEach((button, index) => {
      this.renderer.listen(button.nativeElement, 'click', () => {
        this.navButtons.forEach(btn => {
          this.renderer.removeClass(btn.nativeElement, 'active');
        });
        this.renderer.addClass(button.nativeElement, 'active');

        const routes = ['controle-presenca', 'relatorio', 'ajuda'];
        this.router.navigate([routes[index]]);
      });
    });
  }

  logout() {
    this.authService.logout();
  }

}
