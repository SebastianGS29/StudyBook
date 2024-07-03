import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GestionpenalizacionService {

  private apiURL = 'http://localhost:8080/api/disabledUser'

  constructor(private http: HttpClient) { }

  getAllDisabledUser(): Observable<any> {
    return this.http.get(this.apiURL);
  }

  updateDisabledUserByEmail(email: string): Observable<any> {
    let params = new HttpParams().set('email', email);
    return this.http.put(`${this.apiURL}/enable`, {}, { params: params, responseType: 'text' });
  }
}
