import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  private apiURL = 'http://localhost:8080/api/reportes'
  constructor(private http: HttpClient) { }


  getAllReportes(): Observable<any> {
    return this.http.get(this.apiURL);
  }

  descargarReportePdf(): Observable<Blob> {
    const url = `${this.apiURL}/pdf`;
    return this.http.get(url, { responseType: 'blob' });
  } 
}