import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Student } from '../../interfaces/students';
import { FilterService } from '../../services/filter/filter.service';
import { ReportService } from '../../services/report/report.service';
import { concatMap, from, map, switchMap, toArray } from 'rxjs';

@Component({
  selector: 'staff-mack-table',
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent implements OnInit {

  columnsStudent: string[] = ["identificacao", "nome", "turmaIdentificacao", "emailResponsavel", "data", "presenca"];
  columnsReport: string[] = ["alunoIdentificacao","turmaIdentificacao", "turmaAno", "professor", "disciplina", "data", "periodo","frequencia"];

  @Input() data: any = [];
  @Input() page!: number;
  @Input() pageSize!: number;
  @Input() type!: string;
  @Output() studentsData = new EventEmitter<Student[]>();
  @Output() tableData = new EventEmitter<Student[]>();
  filteredStudents: Student[] = [];
  filteredReport: any[] = [];

  constructor(
    private filterService: FilterService,
    private reportReportService: ReportService
  ) { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    
    this.reportReportService.report.pipe(
      switchMap((data) => {
        return from(data).pipe(
          concatMap((item: any) => {
            return this.reportReportService.getProfessorById(item[4]).pipe(
              map((professor: any) => {
                return {
                  alunoIdentificacao: item[1],
                  turmaIdentificacao: item[2],
                  turmaAno: item[3].toString(),
                  professor: professor.nome,
                  disciplina: item[5],
                  periodo: item[7],
                  data: item[6],
                  frequencia: `${item[8]}%`
                };
              })
            );
          }),
          toArray()
        );
      })
    ).subscribe((filteredReport) => {
      this.filteredReport = filteredReport;
    });

    this.filterService.selectedFilter.subscribe((filter) => {

      const filters = Object.keys(filter).reduce((result, key) => {
        if (key !== 'aulaPeriodo') {
          let value = Number(filter[key]);
          (result as any)[this.mapFilters(key)] = isNaN(value) ? filter[key] : value;
        }
        if (key === 'datas') {
          (result as any) = filter[key];
        }
        return result;
      }, {});


      if (filter['datas']) {
        this.filteredStudents = this.data[0][filters as string]
        this.studentsData.emit(this.filteredStudents);

      } else {
        let filteredStudents = [...this.data]; // Cria uma cópia do array original

        filteredStudents = filteredStudents.filter(item => {
          return Object.keys(filters).every((key) => {
            if (Array.isArray(item[key])) {
              return (item as any)[key].includes(Number((filters as any)[key]));
            } else {
              return item[key] === (filters as any)[key];
            }
          });
        }).map(student => {
          const currentDate = new Date();
          const formattedDate = currentDate.toLocaleDateString('pt-BR');
          return {
            ...student,
            aluno: student['identificacao'],
            aulaMateria: (filters as any)['materiaCodigo'].toString(),
            aulaPeriodo: (filter as any)['aulaPeriodo'],
            data: formattedDate,
            professor: "P2",
            turmaAno: student['turmaAno'],
            turmaIdentificacao: student['turmaIdentificacao'],
          };
        });
        this.filteredStudents = filteredStudents;
        this.studentsData.emit(this.filteredStudents);
      }
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

  getColumns(type: string) {
    return type === 'students' ? this.columnsStudent : this.columnsReport;
  }

  mapColumns(data: string): string {
    const mapping: { [key: string]: string } = {
      "identificacao": "identificação",
      "nome": "aluno",
      "turmaIdentificacao": "turma",
      "emailResponsavel": "E-mail",
      "alunoIdentificacao": "aluno",
      "turmaAno": "ano",
      "professor": "professor",
      "disciplina": "disciplina",
      "periodo": "período",
      "frequencia": "frequência"
    }

    return mapping[data] || data;
  }

  changePresence(student: Student, presence: boolean, column: string): void {
    let studentReport = this.filteredStudents.find(item => {
      return item['identificacao'] === student['identificacao'];
    });

    if (studentReport) {
      studentReport[column] = presence;
      // Verifica se todos os estudantes têm a propriedade 'presenca' preenchida
      if (this.filteredStudents.every(student => student.hasOwnProperty(column))) {
        this.tableData.emit(this.filteredStudents);
      }
    }
  }
}
