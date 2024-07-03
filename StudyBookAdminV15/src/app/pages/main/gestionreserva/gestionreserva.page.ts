import { Component, OnInit } from '@angular/core';
import { GestionreservaService } from 'src/app/services/gestionreserva.service';

@Component({
  selector: 'app-gestionreserva',
  templateUrl: './gestionreserva.page.html',
  styleUrls: ['./gestionreserva.page.scss'],
})
export class GestionreservaPage implements OnInit {

  disabledSlots: any[] = [];

  constructor(private gestionreservaService: GestionreservaService) { }

  ngOnInit(): void {
    this.getAllDisabledSlots();
  }

  getAllDisabledSlots(): void {
    this.gestionreservaService.getAllDisabledSlots().subscribe((data) => {
      this.disabledSlots = data;
    });
  }

  updateSlot(index: number): void {
    // Suponiendo que tienes una lista de slots deshabilitados y 'i' es el índice del slot a actualizar
    const disabledSlot = this.disabledSlots[index];

    // Llama al servicio para actualizar el slot deshabilitado
    this.gestionreservaService.updateDisabledSlot(disabledSlot.sala, disabledSlot.date, disabledSlot.timeSlot)
      .subscribe(
        response => {
          console.log('Slot actualizado correctamente:', response);
          // Aquí puedes manejar la respuesta del backend según sea necesario
          
          // Por ejemplo, podrías actualizar la lista de slots deshabilitados después de actualizar uno
          // this.disabledSlots[index] = response; // Si deseas actualizar el slot en la lista con los nuevos datos
        },
        error => {
          console.error('Error al actualizar el slot:', error);
          // Aquí puedes manejar el error que se produzca
        }
      );
  }
}