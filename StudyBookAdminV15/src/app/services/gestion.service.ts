import { HttpClient } from '@angular/common/http'; // Importa el módulo HttpClient para hacer solicitudes HTTP
import { Injectable } from '@angular/core'; // Importa el decorador Injectable para permitir la inyección de dependencias
import { Observable } from 'rxjs'; // Importa Observable de la biblioteca RxJS para manejar datos asincrónicos

@Injectable({
  providedIn: 'root' // Declara que este servicio está disponible en toda la aplicación
})
export class GestionService {

  private apiURL = 'http://localhost:8080/api/gestionsalas'; // URL base de la API

  constructor(private http: HttpClient) { } // Constructor del servicio que inyecta el módulo HttpClient

  // Método para obtener todas las salas de gestión
  getGestionSalas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiURL); // Realiza una solicitud GET a la API para obtener las salas
  }
  
  // Método para obtener los detalles de una sala por su nombre
  getSalaByNombre(nombre: string): Observable<any> {
    const url = `${this.apiURL}/${nombre}`; // Construye la URL completa para obtener los detalles de la sala por su nombre
    return this.http.get<any>(url); // Realiza una solicitud GET a la API para obtener los detalles de la sala
  }
}
