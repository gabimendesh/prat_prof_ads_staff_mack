import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilterComponent } from './components/filter/filter.component';
import { SearchComponent } from './components/search/search.component';
import { TableComponent } from './components/table/table.component';
import { TableColumnComponent } from './components/table/components/table-column/table-column.component';
import { TableRowComponent } from './components/table/components/table-row/table-row.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FilterBoxComponent } from './components/filter-box/filter-box.component';

@NgModule({
  declarations: [
    FilterComponent,
    SearchComponent,
    TableComponent,
    TableColumnComponent,
    TableRowComponent,
    PaginationComponent,
    FilterBoxComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [
    FilterComponent,
    SearchComponent,
    TableComponent,
    TableColumnComponent,
    TableRowComponent,
    PaginationComponent,
    FilterBoxComponent
  ]
})
export class SharedModule { }
