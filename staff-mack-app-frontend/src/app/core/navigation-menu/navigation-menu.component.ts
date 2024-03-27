import { AfterViewInit, Component, ElementRef, OnInit, QueryList, Renderer2, ViewChild, ViewChildren } from '@angular/core';

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
    private renderer: Renderer2
  ) { }

  ngOnInit(): void {
    
  }

  ngAfterViewInit(): void {
    this.navButton.nativeElement.classList.add('active'); 
    this.navButtons.forEach(button => {
      this.renderer.listen(button.nativeElement, 'click', () => {
        this.navButtons.forEach(btn => {
          this.renderer.removeClass(btn.nativeElement, 'active');
        });
        this.renderer.addClass(button.nativeElement, 'active');
      });
    });
  }


}