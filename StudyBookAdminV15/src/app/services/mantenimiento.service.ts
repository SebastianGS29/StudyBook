import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MantenimientoService {

  private apiURL = 'http://localhost:8080/api/mantenimiento';

  constructor(private http: HttpClient) { }

  getMantenimiento(nombre: string): Observable<any> {
    const url = `${this.apiURL}/${nombre}`;
    return this.http.get<any>(url);
  }

  actualizarMantenimiento(nombre: string, nuevoMantenimiento: any): Observable<any> {
    const url = `${this.apiURL}/${nombre}`;
    return this.http.put<any>(url, nuevoMantenimiento);
  }
}