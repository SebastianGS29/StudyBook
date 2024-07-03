import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { users } from 'src/app/model/user.model'; 

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {
  private baseUrl = environment.apiUrl + '/api/useradmin'; 

  constructor(private http: HttpClient) { }

  getUserByEmail(email: string) {
    return this.http.get<users>(`${this.baseUrl}/${email}`).toPromise();
  }

  saveUser(user: users) {
    return this.http.post(this.baseUrl, user).toPromise();
  }

  sendRecoveryEmail(email: string) {
    return this.http.post(`${this.baseUrl}/forgot-password`, { email }).toPromise();
  }
}
