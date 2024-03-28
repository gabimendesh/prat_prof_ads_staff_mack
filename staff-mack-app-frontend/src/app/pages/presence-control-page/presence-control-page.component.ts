import { Component } from '@angular/core';
import { studentsMock } from '../../shared/mocks/students-mock/students-mock';
import { Student } from '../../shared/interfaces/students';

@Component({
  selector: 'staff-mack-presence-control-page',
  templateUrl: './presence-control-page.component.html',
  styleUrl: './presence-control-page.component.scss'
})
export class PresenceControlPageComponent {
  filters = [
    {
      name: 'Turma',
      options: [
        { label: '1A', value: '1A' },
        { label: '1B', value: '1B' },
        { label: '1C', value: '1C' },
        { label: '1D', value: '1D' }
      ]
    },
    {
      name: 'Disciplina',
      options: [
        { label: 'Matemática', value: 'Matemática' },
        { label: 'Português', value: 'Português' },
        { label: 'História', value: 'História' },
        { label: 'Geografia', value: 'Geografia' }
      ]
    },
    {
      name: "Ano",
      options: [
        { label: '1º', value: '1' },
        { label: '2º', value: '2' },
        { label: '3º', value: '3' },
        { label: '4º', value: '4' }
      ]
    }
  ]
  pageSize = 10;
  page = 1;
  students: Student[] = studentsMock;
  
  get totalPages(): number {
    return Math.ceil(this.students.length / this.pageSize);
  }

  get pageNumbers(): number[] {
    return Array(this.totalPages).fill(0).map((x, i) => i + 1);
  }
}
