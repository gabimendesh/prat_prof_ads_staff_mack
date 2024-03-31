import { Component, Input, OnInit } from '@angular/core';
import { studentsMock } from '../../shared/mocks/students-mock/students-mock';
import { Student } from '../../shared/interfaces/students';
import { FilterService } from '../../shared/services/filter/filter.service';
import { StudentsService } from '../../shared/services/students/students.service';
import { Filter } from '../../shared/interfaces/filter';
import { ReportService } from '../../shared/services/report/report.service';

@Component({
  selector: 'staff-mack-presence-control-page',
  templateUrl: './presence-control-page.component.html',
  styleUrl: './presence-control-page.component.scss'
})
export class PresenceControlPageComponent implements OnInit {
  filters!: any[];
  pageSize = 10;
  page = 1;
  students: Student[] = [];
  studentsFiltered: Student[] = [];
  @Input() filter!: string;
  tableData: Student[] = [];

  constructor(
    private studentsService: StudentsService,
    private filterService: FilterService,
    private reportService: ReportService
  ) {}

  ngOnInit(): void {
    this.studentsService.getAllStudents();
    
    this.studentsService.students.subscribe((students: Student[]) => {
      this.students = students;
      this.filters = this.filterService.getFilters(this.students, 'students');
      this.filterService.filters.next(this.filters);
    });
  }
  
  get totalPages(): number {
    return Math.ceil(this.studentsFiltered.length / this.pageSize);
  }

  get pageNumbers(): number[] {
    return Array(this.totalPages).fill(0).map((x, i) => i + 1);
  }

  sendReport() {
    this.reportService.sendReport(this.tableData);
  }
}
