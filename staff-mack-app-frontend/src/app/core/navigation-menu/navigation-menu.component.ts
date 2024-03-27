import { AfterViewInit, Component, ElementRef, OnInit, QueryList, Renderer2, ViewChild, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'staff-mack-navigation-menu',
  templateUrl: './navigation-menu.component.html',
  styleUrl: './navigation-menu.component.scss'
})
export class NavigationMenuComponent implements OnInit, AfterViewInit {
  @ViewChild('navButton') navButton!: ElementRef;
  @ViewChildren('navButton') navButtons!: QueryList<ElementRef>;
  constructor(
    private el: ElementRef,
    private renderer: Renderer2,
    private router: Router
  ) { }

  ngOnInit(): void {
    
  }

  ngAfterViewInit(): void {
    this.navButton.nativeElement.classList.add('active'); 
    this.navButtons.forEach((button, index) => { // Add 'index' as a parameter
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


}