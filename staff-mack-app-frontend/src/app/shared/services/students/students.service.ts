import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentsService {
  // apiUrl = environment.apiBaseUrl;
  students: Subject<any[]> = new Subject<any[]>();

  constructor(
    private http: HttpClient
  ) { }

  getAllStudents(): void {
    this.http.get(`https://44.205.12.176:443/api/alunos`, {
      headers: {
        'Content-Type': 'application/json',
      }
    }).subscribe((students: any) => {
      this.students.next(students);
    });

  }
}
