import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Student } from '../../interfaces/students';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  apiBaseUrl = 'https://44.205.12.176/api/';

  constructor(
    private http: HttpClient
  ) { }

  sendReport(turma: Student[]) {
    const professor = "P2";

    let buildQuery = turma.map((student) => {
      let dateParts = (student['data'] as string).split('/');
      let formattedDate = `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
      
      return `aluno=${student['identificacao']}&data=${formattedDate}&turmaAno=${student['turmaAno']}&turmaIdentificacao=${student['turmaIdentificacao']}&professor=${professor}&aulaPeriodo=${student['aulaPeriodo']}&aulaMateria=${student['materiaCodigo']}&presente=${student['presenca']}`;
    });

    buildQuery.forEach((query) => {
      this.http.post(`${this.apiBaseUrl}/presencas?${query}`, {}).subscribe((res) => {
      });
    });
  }

  getReport(): any {
    return this.http.get<any[]>(`${this.apiBaseUrl}/presencas`).pipe(
      map((data: any[]) => {
        return data.reduce((groups, item) => {
          const date = new Date(item.data);
          const year = date.getFullYear();
          const month = date.getMonth() + 1; // getMonth() retorna um valor de 0 (Janeiro) a 11 (Dezembro)
          const key = `${year}-${month < 10 ? '0' + month : month}`; // Formata a chave como 'yyyy-mm'
          if (!groups[key]) {
            groups[key] = [];
          }
          groups[key].push(item);
          return groups;
        }, {});
      })
    )
  }
}
