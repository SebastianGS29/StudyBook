import { Component, inject, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { UtilsService } from 'src/app/services/utils.service';
import { MantenimientoService } from 'src/app/services/mantenimiento.service';

@Component({
  selector: 'app-mantenimiento',
  templateUrl: './mantenimiento.page.html',
  styleUrls: ['./mantenimiento.page.scss'],
})
export class MantenimientoPage implements OnDestroy {
  form: FormGroup;
  numbers: number[] = [];
  utilsSvc = inject(UtilsService);
  availableOptions = ['Disponible', 'Ocupado'];
  salaSubscription: Subscription;
  isResetting = false; // Indicador de restablecimiento

  constructor(private formBuilder: FormBuilder, private mantenimientoService: MantenimientoService) {
    this.form = this.formBuilder.group({
      encargado: ['', Validators.required],
      idsala: ['', [Validators.required, Validators.min(1), Validators.max(11)]],
      computadora: ['', [Validators.required, Validators.min(0), Validators.max(1)]],
      pizarra: ['', [Validators.required, Validators.min(0), Validators.max(5)]],
      mesa: ['', [Validators.required, Validators.min(0), Validators.max(3)]],
      sillas: ['', [Validators.required, Validators.min(0), Validators.max(6)]],
      plumon: ['', [Validators.required, Validators.min(0), Validators.max(5)]],
      mota: ['', [Validators.required, Validators.min(0), Validators.max(3)]],
      detalle: ['', Validators.required],
    });

    for (let i = 1; i <= 11; i++) {
      this.numbers.push(i);
    }

    // Escuchar cambios en el campo idsala
    this.form.get('idsala').valueChanges.subscribe((value) => {
      if (value && !this.isResetting) { // Solo cargar datos si no estamos restableciendo
        console.log(`Cambio detectado en idsala: ${value}`);
        this.loadSalaData(value.toString());
      }
    });
  }

  loadSalaData(idsala: string) {
    // Cancelar la suscripción anterior si existe
    if (this.salaSubscription) {
      this.salaSubscription.unsubscribe();
    }

    // Obtener los datos de la sala desde la API y actualizar el formulario
    this.salaSubscription = this.mantenimientoService.getMantenimiento(idsala).subscribe(data => {
      if (data) {
        console.log(`Datos de sala cargados: `, data);
        this.form.patchValue(data);
      }
    });
  }

  async submit() {
    if (this.form.valid) {
      const idsala = this.form.value.idsala.toString();
      const nombre = `Sala ${idsala}`;

      // Incluir nombre en los datos actualizados
      const updatedData = {
        ...this.form.value,
        nombre: nombre
      };

      try {
        // Desactivar la suscripción antes de actualizar
        if (this.salaSubscription) {
          this.salaSubscription.unsubscribe();
        }

        // Actualizar los datos en la API, asegurándose de pasar el idsala correcto
        await this.mantenimientoService.actualizarMantenimiento(idsala, updatedData).toPromise();
        console.log('Datos actualizados exitosamente en la API');
        this.resetForm(idsala); // Restablece el formulario después de actualizar, conservando el idsala
      } catch (error) {
        console.error('Error al actualizar los datos en la API:', error);
      }
    } else {
      console.error('Formulario no válido');
    }
  }

  resetForm(idsala: string) {
    console.log('Restableciendo el formulario...');
    this.isResetting = true; // Indicador de restablecimiento

    this.form.reset(); // Restablece el estado del formulario
    this.form.markAsPristine(); // Marca el formulario como no modificado
    this.form.markAsUntouched(); // Marca el formulario como no tocado

    // Inicializa algún valor por defecto después de resetear, manteniendo idsala
    this.form.setValue({
      encargado: '',
      idsala: idsala,
      computadora: '',
      pizarra: '',
      mesa: '',
      sillas: '',
      plumon: '',
      mota: '',
      detalle: ''
    });

    console.log('Formulario restablecido:', this.form.value);
    this.isResetting = false; // Restablecimiento completado
  }

  ngOnDestroy() {
    if (this.salaSubscription) {
      this.salaSubscription.unsubscribe();
    }
  }
}