import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Student } from '../../interfaces/students';

@Component({
  selector: 'staff-mack-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {
  @Input() page!: number;
  @Input() totalPages!: number;
  @Input() pageNumbers!: number[];
  @Output() pageChange = new EventEmitter<number>();

  onPageChange(page: number): void {
    console.log(page, this.pageNumbers, this.totalPages,  this.page);
    
    this.pageChange.emit(page);
  }
}
