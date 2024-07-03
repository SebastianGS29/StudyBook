import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GestionreservaService {

  private apiURL = 'http://localhost:8080/api/disabledSlots'
  
  constructor(private http: HttpClient) { }

  getAllDisabledSlots(): Observable<any> {
    return this.http.get(this.apiURL);
  }

  updateDisabledSlot(sala: string, date: string, timeSlot: string): Observable<any> {
    const url = `${this.apiURL}/disable/${sala}/${date}/${timeSlot}`;
    return this.http.put(url, {}); // Puedes enviar datos en el cuerpo {} si es necesario
  }
}
