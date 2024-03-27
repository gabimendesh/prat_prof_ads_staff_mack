import { Component, Input } from '@angular/core';

@Component({
  selector: 'staff-mack-filter',
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.scss'
})
export class FilterComponent {
  @Input() filters: any = [];
}
