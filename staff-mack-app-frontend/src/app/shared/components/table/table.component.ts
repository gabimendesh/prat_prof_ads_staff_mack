import { Component, Input, OnInit } from '@angular/core';
import { Student } from '../../interfaces/students';

@Component({
  selector: 'staff-mack-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent implements OnInit {

columns: string[] =  [ "Aluno", "Matrícula", "Turma", "E-mail do responsável", "Data", "Presença" ];
@Input() students: Student[] = [];
@Input() page!: number;
@Input() pageSize!: number;
  
  ngOnInit(): void {
    console.log(this.students, this.page, this.pageSize)
  }

  getColumns() {
    return this.columns;
  }

  getRows() {
    return this.students.map(student => Object.values(student));
  }

  // get totalPages(): number {
  //   return Math.ceil(this.students.length / this.pageSize);
  // }

  // get pageNumbers(): number[] {
  //   return Array(this.totalPages).fill(0).map((x, i) => i + 1);
  // }
}
