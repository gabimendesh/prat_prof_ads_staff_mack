import { Component, OnInit } from '@angular/core';
import { FilterService } from '../../services/filter/filter.service';
import { ReportService } from '../../services/report/report.service';

@Component({
  selector: 'staff-mack-filter-box',
  templateUrl: './filter-box.component.html',
  styleUrl: './filter-box.component.scss'
})
export class FilterBoxComponent implements OnInit {
  selectedOptions: { [key: string]: string } = {};
  isDisabled = true;

  filters = [
    {
      id: 'semana',
      name: 'semana',
      options: ['1', '2', '3', '4', '5']
    },
    {
      id: 'mes',
      name: 'mês',
      options: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
    },
    {
      id: 'ano',
      name: 'ano',
      options: ['2024']
    },
    {
      id: 'disciplina',
      name: 'disciplina',
      options: ['Matemática', 'Português', 'Ciências', 'História', 'Geografia', 'Inglês', 'Artes', 'Educação Física']
    },
    {
      id: 'turma',
      name: 'turma',
      options: ['T1','T2']
    }
  ];

  constructor(
    private filterService: FilterService,
    private reportService: ReportService
  ) {}

  ngOnInit(): void {
    this.filterService.selectedFilter.subscribe((selectedFilter: any) => {
    });
    for (let filter of this.filters) {
      this.selectedOptions[filter.name] = ''; // Seleciona a primeira opção disponível para cada filtro
    }
  }

  clearFilters(): void {
    for (let filter of this.filters) {
      this.selectedOptions[filter.id] = '';
    }
  }

  handleOptionChange(filterName: string, selectedOption: string): void {
    this.selectedOptions[filterName] = selectedOption;

    this.selectedOptions['disciplina'] = this.getOptions(this.selectedOptions['disciplina']);
    
    // send the selected filter to the filter service when all options are selected
    if (Object.values(this.selectedOptions).every((option) => option !== '')) {
      this.isDisabled = false;
      this.filterService.selectedFilter.next(this.selectedOptions);
    } else {
      this.isDisabled = true;
    }
  }

  sendReport() {
    this.reportService.setReport(this.selectedOptions).subscribe((report: any) => {
      this.reportService.report.next(report);
    });
  }

  getOptions(value: string): string {
    const mappingDisciplina: { [key: string]: string } = {
      'Matemática':'101',
      'Português':'102',
      'Artes':'103',
      'Ciências':'104',
      'História':'105',
      'Geografia':'106',
      'Educação física':'107',
      'Inglês':'108',
    }
  
    return mappingDisciplina[value.toString()] || value;
  }
}
