import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilterComponent } from './components/filter/filter.component';
import { SearchComponent } from './components/search/search.component';
import { TableComponent } from './components/table/table.component';
import { TableColumnComponent } from './components/table/components/table-column/table-column.component';
import { TableRowComponent } from './components/table/components/table-row/table-row.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    FilterComponent,
    SearchComponent,
    TableComponent,
    TableColumnComponent,
    TableRowComponent,
    PaginationComponent,
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    FilterComponent,
    SearchComponent,
    TableComponent,
    TableColumnComponent,
    TableRowComponent,
    PaginationComponent
  ]
})
export class SharedModule { }
