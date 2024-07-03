import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {
  private baseUrl = environment.apiUrl + '/api/useradmin';

  constructor(private http: HttpClient) { }

  getAllEmails() {
    return this.http.get<string[]>(`${this.baseUrl}/emails`).toPromise();
  }

  updatePassword(email: string, newPassword: string) {
    return this.http.put(`${environment.apiUrl}/api/update`, { email, clave: newPassword }, { responseType: 'text' }).toPromise();
  }
}
