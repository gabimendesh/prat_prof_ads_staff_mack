import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FilterService {
  // apiUrl = environment.apiBaseUrl;
  selectedFilter: Subject<{ [key: string]: string }> = new Subject<any>();
  filters: Subject<any[]> = new Subject<any[]>();

  constructor(
    private http: HttpClient
  ) { }

  getData(): Observable<any> {
    return this.http.get(`$https://44.205.12.176:8080/api/alunos`,
      {
        headers: {
          'Content-Type': 'application/json',
        }
      }
    )
  }

  getFilters(data: any[]): any[] {
    const disciplinas = [...new Set(data.flatMap(item => item.materiaCodigo))];
    const turmas = [...new Set(data.map(item => item.turmaIdentificacao))];
    const anos = [...new Set(data.map(item => item.turmaAno))];
    
    return [
      { disciplinas: disciplinas },
      { turma: turmas },
      { ano: anos },
      { aulaPeriodo: [1, 2]}
    ];
  }


}
