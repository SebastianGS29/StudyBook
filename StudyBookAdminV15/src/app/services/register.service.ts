import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private apiUrl = 'http://localhost:8080/api/register';

  constructor(private http: HttpClient) { }

  registerUser(email: string, clave: string, rango: string): Observable<any> {
    const user = { email, clave, rango };
    return this.http.post<any>(this.apiUrl, user, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
