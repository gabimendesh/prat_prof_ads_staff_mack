import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Student } from '../../interfaces/students';
import { FilterService } from '../../services/filter/filter.service';

@Component({
  selector: 'staff-mack-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent implements OnInit {

  // columns: string[] = ["aluno", "matricula", "turma", "email", "data", "presenca"];
  columns: string[] = [ "identificacao","nome", "turmaIdentificacao", "emailResponsavel","presenca"];
  @Input() students: Student[] = [];
  @Input() page!: number;
  @Input() pageSize!: number;
  @Output() studentsData = new EventEmitter<Student[]>();
  filteredStudents: Student[] = [];

  constructor(
    private filterService: FilterService
  ) { }

  // ngOnInit(): void {
  //   console.log(this.students);
    
  //   this.students
  //   this.filterService.selectedFilter.subscribe((filter) => {
  //     const filters = Object.keys(filter).reduce((result, key) => {
  //       let value = Number(filter[key]);
  //       (result as any)[this.mapFilters(key)] = isNaN(value) ? filter[key] : value;
  //       return result;
  //     }, {});
      
  //     this.filteredStudents = this.students;
  //     console.log(
  //       'students filtrado', this.filteredStudents,
  //       'students',this.students
  //     );
      
  
  //     this.filteredStudents = this.students.filter(item => {
  //       return Object.keys(filters).every((key) => {
  //         if (Array.isArray(item[key])) {
  //           return (item as any)[key].includes(Number((filters as any)[key]));
  //         } else {
  //           return item[key] === (filters as any)[key];
  //         }
  //       });
  //     });
  //     console.log('filtradoooooooo>>>>', this.filteredStudents);
      
  //     this.studentsData.emit(this.filteredStudents);
  //   });
  // }

  ngOnInit(): void {}

  ngOnChanges(): void {
    console.log(this.students);
    
    this.filterService.selectedFilter.subscribe((filter) => {
      const filters = Object.keys(filter).reduce((result, key) => {
        let value = Number(filter[key]);
        (result as any)[this.mapFilters(key)] = isNaN(value) ? filter[key] : value;
        return result;
      }, {});
      console.log('filters', filters);
      
  
      let filteredStudents = [...this.students]; // Cria uma cópia do array original
      
      filteredStudents = filteredStudents.filter(item => {
        return Object.keys(filters).every((key) => {
          if (Array.isArray(item[key])) {
            return (item as any)[key].includes(Number((filters as any)[key]));
          } else {
            return item[key] === (filters as any)[key];
          }
        });
      });
  
      console.log('filtradoooooooo>>>>', filteredStudents);
  
      this.filteredStudents = filteredStudents;
      this.studentsData.emit(this.filteredStudents);
    });
  }

  mapFilters(key: string): string {
    const mapping: { [key: string]: string } = {
      'disciplinas': 'materiaCodigo',
      'turma': 'turmaIdentificacao',
      'ano': 'turmaAno',
      'matricula': 'identificacao'
    };

    return mapping[key];
  }

  getColumns() {
    return this.columns;
  }

  mapColumns(data: string): string {
    
    const mapping: { [key: string]: string } = { 
      "identificacao": "identificação",
      "nome": "aluno",
      "turmaIdentificacao": "turma",
      "emailResponsavel": "E-mail",
    }

    return mapping[data] || data;
  }

  getRows() {
    return this.students.map(student => Object.values(student));
  }

  changePresence(student: Student, presence: boolean): void {
    student['Presença'] = presence;
  }
}
