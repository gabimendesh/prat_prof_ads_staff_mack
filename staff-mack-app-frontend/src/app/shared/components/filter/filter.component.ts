import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { FilterService } from '../../services/filter/filter.service';

@Component({
  selector: 'staff-mack-filter',
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.scss'
})
export class FilterComponent implements OnInit {
  selectedOptions: { [key: string]: string } = {};
  @Input() filters: any = [];
  @Output() filterChange = new EventEmitter<string>();

  constructor(
    private filterService: FilterService
  ) {}

  ngOnInit(): void {
    this.filterService.filters.subscribe((filters: any) => {
      this.filters = filters;
      for (let filter of this.filters) {
        this.selectedOptions[this.getKeys(filter)[0]] = '';
      }
    });
  }

  getFilterName() {
    return this.filters.map((filter: any) => filter);
  }

  handleOptionChange(filterName: string, selectedOption: string): void {
    this.selectedOptions[filterName] = selectedOption;
    
    this.selectedOptions = Object.keys(this.selectedOptions)
    .filter((key) => this.selectedOptions[key] !== '' && this.selectedOptions[key] !== undefined)
      .reduce((obj, key) => {
        (obj as any)[key] = this.selectedOptions[key];
        return obj;
      }, {});
      
      // send the selected filter to the filter service when all options are selected
      if (Object.keys(this.selectedOptions).length === Object.keys(this.filters).length) {
        this.filterService.selectedFilter.next(this.selectedOptions);
      }

  }
    
  getKeys(obj: object): string[] {
    return Object.keys(obj);
  }

  getOptions(value: string): string {
    const mappingDisciplina: { [key: string]: string } = {
      '101': 'matemática',
      '102': 'português',
      '103': 'artes',
      '104': 'ciências',
      '105': 'história',
      '106': 'geografia',
      '107': 'educação física',
      '108': 'inglês',
    }
  
    return mappingDisciplina[value.toString()] || value;
  }

}
