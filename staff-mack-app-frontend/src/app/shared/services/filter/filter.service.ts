import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilterService {
  selectedFilter: Subject<{ [key: string]: string }> = new Subject<any>();

  constructor() { }
}
