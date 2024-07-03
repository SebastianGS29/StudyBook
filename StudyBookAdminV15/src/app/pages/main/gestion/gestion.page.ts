import { Component, OnInit, inject } from '@angular/core';
import { GestionService } from 'src/app/services/gestion.service'; // Importa el servicio GestionService desde su ubicación
import { UtilsService } from 'src/app/services/utils.service'; // Importa el servicio UtilsService desde su ubicación

@Component({
  selector: 'app-gestion', // Selector del componente
  templateUrl: './gestion.page.html', // Ruta del archivo HTML asociado al componente
  styleUrls: ['./gestion.page.scss'], // Rutas de los archivos de estilos asociados al componente
})
export class GestionPage implements OnInit {

  mantenimiento: any[] = []; // Arreglo para almacenar datos de mantenimiento de las salas
  selectedSala: string; // Almacena el ID de la sala seleccionada
  datosSala: any; // Almacena los datos de una sala seleccionada
  datosSalaItems: any[] = []; // Arreglo para almacenar los datos de los elementos de una sala seleccionada

  utilsSvc = inject(UtilsService); // Variable para inyectar el servicio UtilsService

  constructor(private gestionService: GestionService) { } // Constructor del componente que inyecta el servicio GestionService

  ngOnInit() {
    this.loadSalas(); // Método que carga las salas al inicializar el componente
  }

  async loadSalas() {
    const loading = await this.utilsSvc.loading(); // Muestra un spinner de carga utilizando el servicio UtilsService
    await loading.present(); // Espera a que se presente el spinner
    this.gestionService.getGestionSalas().subscribe(data => { // Obtiene las salas utilizando el servicio GestionService
      this.mantenimiento = data; // Almacena los datos de mantenimiento de las salas
      loading.dismiss(); // Cierra el spinner de carga
      
      this.mantenimiento = data.sort((a, b) => { // Ordena las salas por número, extrayendo el número del nombre de la sala
        const numA = parseInt(a.nombre.split(' ')[1], 10);
        const numB = parseInt(b.nombre.split(' ')[1], 10);
        return numA - numB; // Devuelve el resultado de la comparación
      });
      if (this.mantenimiento.length > 0) { // Si hay salas disponibles
        this.selectedSala = this.mantenimiento[0].id; // Selecciona la primera sala
        this.cambiarSala(); // Actualiza los datos de la sala seleccionada
      }
    });
  }

  cambiarSala() {
    if (this.selectedSala) { // Si hay una sala seleccionada
      this.gestionService.getSalaByNombre(this.selectedSala).subscribe(datosSala => { // Obtiene los datos de la sala seleccionada
        this.datosSala = datosSala; // Almacena los datos de la sala
        this.actualizarDatosSalaItems(); // Actualiza los elementos de la sala seleccionada
      });
    }
  }

  actualizarDatosSalaItems() {
    // Actualiza los elementos de la sala seleccionada con sus respectivos valores y iconos
    this.datosSalaItems = [
      { titulo: 'Encargado', valor: this.datosSala.encargado, icon: '../assets/assetsmari/icon/jefe.png' },
      { titulo: 'Computadora', valor: this.datosSala.computadora, icon: '../assets/assetsmari/icon/Compu.png' },
      { titulo: 'Pizarra', valor: this.datosSala.pizarra, icon: '../assets/assetsmari/icon/pizarra.png' },
      { titulo: 'Mesa', valor: this.datosSala.mesa, icon: '../assets/assetsmari/icon/mesa.png' },
      { titulo: 'Mota', valor: this.datosSala.mota, icon: '../assets/assetsmari/icon/mota.png' },
      { titulo: 'Plumones', valor: this.datosSala.plumon, icon: '../assets/assetsmari/icon/plumones.png' },
      { titulo: 'Sillas', valor: this.datosSala.sillas, icon: '../assets/assetsmari/icon/silla.png' }
    ];
  }

  compareSala(a: any, b: any): number {
    // Función para comparar salas por número (utilizada en la ordenación)
    const numA = parseInt(a.nombre.split(' ')[1], 10); // Extrae el número del nombre de la sala
    const numB = parseInt(b.nombre.split(' ')[1], 10);
    return numA - numB; // Devuelve el resultado de la comparación
  }
}
