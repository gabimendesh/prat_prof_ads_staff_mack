import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Student } from '../../interfaces/students';
import { FilterService } from '../../services/filter/filter.service';

@Component({
  selector: 'staff-mack-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent implements OnInit {

  columns: string[] = ["aluno", "matricula", "turma", "email", "data", "presenca"];
  @Input() students: Student[] = [];
  @Input() page!: number;
  @Input() pageSize!: number;
  @Output() studentsData = new EventEmitter<Student[]>();
  filteredStudents: Student[] = [];

  constructor(
    private filterService: FilterService
  ) { }

  ngOnInit(): void {

  
  }

  ngOnChanges(): void {
 
    this.filterService.selectedFilter.subscribe((filter) => {
      console.log('filtros para tabela', filter);
    
      // Filter your data based on the selected filter
      this.filteredStudents = this.students.filter(item => {
        return Object.keys(filter).every((key) => {
          console.log(filter[key], item[key]);
    
          // Check if the filter value is an array
          if (Array.isArray(item[key])) {
            console.log('filtro é um array Disciplinas', filter[key]);
            
            // If the filter value is an array, check if the item property is included in the array
            return (item as any)[key].includes((filter as any)[key]);
          } else {
            // If the filter value is not an array, check if the item property equals the filter value
            return item[key] === filter[key];
          }
        });
      });
      this.studentsData.emit(this.filteredStudents);
    
      console.log('filtradoooooooo>>>>', this.filteredStudents);
    });
  }

  getColumns() {
    return this.columns;
  }

  getRows() {
    return this.students.map(student => Object.values(student));
  }

  changePresence(student: Student, presence: boolean): void {
    student['Presença'] = presence;
  }
}
