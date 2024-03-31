import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Student } from '../../interfaces/students';

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
}
