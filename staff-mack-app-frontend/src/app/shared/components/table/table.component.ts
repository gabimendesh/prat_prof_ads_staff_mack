import { Component, Input, OnInit } from '@angular/core';
import { Student } from '../../interfaces/students';

@Component({
  selector: 'staff-mack-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent implements OnInit {

@Input() students: Student[] = [];
@Input() page!: number;
@Input() pageSize!: number;
  
  ngOnInit(): void {
    this.getRows();
  }

  getColumns() {
    return Object.keys(this.students[0]);
  }

  getRows() {
    return this.students.map(student => Object.values(student));
  }

  get totalPages(): number {
    return Math.ceil(this.students.length / this.pageSize);
  }

  get pageNumbers(): number[] {
    return Array(this.totalPages).fill(0).map((x, i) => i + 1);
  }
}
