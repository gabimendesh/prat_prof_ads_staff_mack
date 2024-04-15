import { Component, OnInit } from '@angular/core';
import { FilterService } from '../../services/filter/filter.service';

@Component({
  selector: 'staff-mack-filter-box',
  templateUrl: './filter-box.component.html',
  styleUrl: './filter-box.component.scss'
})
export class FilterBoxComponent implements OnInit {
  selectedOptions: { [key: string]: string } = {};

  filters = [
    {
      id: 'semana',
      name: 'semana',
      options: ['Semana 1', 'Semana 2', 'Semana 3', 'Semana 4']
    },
    {
      id: 'mes',
      name: 'mês',
      options: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro']
    },
    {
      id: 'ano',
      name: 'ano',
      options: ['2021', '2022', '2023', '2024']
    },
    {
      id: 'disciplina',
      name: 'disciplina',
      options: ['Matemática', 'Português', 'Ciências', 'História', 'Geografia', 'Inglês', 'Artes', 'Educação Física']
    },
    {
      id: 'turma',
      name: 'turma',
      options: ['1A', '1B', '1C', '1D', '1E', '2A', '2B', '2C', '2D', '2E', '3A', '3B', '3C', '3D', '3E', '4A', '4B', '4C', '4D', '4E', '5A', '5B', '5C', '5D', '5E']
    }
  ];

  constructor(
    private filterService: FilterService
  ) {}

  ngOnInit(): void {
    this.filterService.selectedFilter.subscribe((selectedFilter: any) => {
      console.log(selectedFilter);
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
}
