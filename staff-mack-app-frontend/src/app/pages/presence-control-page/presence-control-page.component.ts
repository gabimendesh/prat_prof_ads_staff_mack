import { Component, Input, OnInit } from '@angular/core';
import { studentsMock } from '../../shared/mocks/students-mock/students-mock';
import { Student } from '../../shared/interfaces/students';
import { FilterService } from '../../shared/services/filter/filter.service';

@Component({
  selector: 'staff-mack-presence-control-page',
  templateUrl: './presence-control-page.component.html',
  styleUrl: './presence-control-page.component.scss'
})
export class PresenceControlPageComponent implements OnInit {
  filters = [
    {
      turma: [
        { label: 'A', value: 'A' },
        { label: '1B', value: '1B' },
        { label: '1C', value: '1C' },
        { label: '3A', value: '3A' }
      ]
    },
    {
      disciplina: [
        { label: 'Matemática', value: 'Matemática' },
        { label: 'Português', value: 'Português' },
        { label: 'História', value: 'História' },
        { label: 'Geografia', value: 'Geografia' }
      ]
    },
    {
      ano: [
        { label: '2024', value: '2024' },
        { label: '2º', value: '2' },
        { label: '3º', value: '3' },
        { label: '4º', value: '4' }
      ]
    }
  ];
  pageSize = 10;
  page = 1;
  students: Student[] = studentsMock;
  @Input() filter!: string;

  constructor(
    private filterService: FilterService
  ) {}

  ngOnInit(): void {
    console.log(this.students);
  }
  
  get totalPages(): number {
    return Math.ceil(this.students.length / this.pageSize);
  }

  get pageNumbers(): number[] {
    return Array(this.totalPages).fill(0).map((x, i) => i + 1);
  }
}
