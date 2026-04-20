import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Estudiante } from './estudiante.model';

@Injectable({ providedIn: 'root' })
export class EstudianteService {

  private url = 'http://localhost:8080/estudiantes';

  constructor(private http: HttpClient) {}

  listar(): Observable<Estudiante[]> {
    return this.http.get<Estudiante[]>(this.url);
  }

  guardar(e: Estudiante): Observable<Estudiante> {
    return this.http.post<Estudiante>(this.url, e);
  }

  actualizar(id: number, e: Estudiante): Observable<Estudiante> {
    return this.http.put<Estudiante>(`${this.url}/${id}`, e);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}