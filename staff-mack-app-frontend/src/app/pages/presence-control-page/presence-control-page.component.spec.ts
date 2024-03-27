import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PresenceControlPageComponent } from './presence-control-page.component';

describe('PresenceControlPageComponent', () => {
  let component: PresenceControlPageComponent;
  let fixture: ComponentFixture<PresenceControlPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PresenceControlPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PresenceControlPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
