import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilterComponent } from './components/filter/filter.component';
import { SearchComponent } from './components/search/search.component';

@NgModule({
  declarations: [FilterComponent, SearchComponent],
  imports: [
    CommonModule
  ],
  exports: [FilterComponent, SearchComponent]
})
export class SharedModule { }
