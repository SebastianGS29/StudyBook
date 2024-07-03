import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterService } from 'src/app/services/register.service';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage {
  registerForm: FormGroup;

  constructor(private fb: FormBuilder, private registerService: RegisterService, private toastController: ToastController) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      clave: ['', [Validators.required, Validators.minLength(6)]],
      rango: ['', [Validators.required]],
    });
  }

  async presentToast(message: string, color: string) {
    const toast = await this.toastController.create({
      message,
      color,
      duration: 2000
    });
    toast.present();
  }

  register() {
    if (this.registerForm.valid) {
      const { email, clave, rango } = this.registerForm.value;
      this.registerService.registerUser(email, clave, rango).subscribe({
        next: (response) => {
          this.presentToast('Registro de nuevo usuario exitoso', 'success');
        },
        error: (error) => {
          console.error('Error registering user', error);
          this.presentToast('Error al registrar usuario', 'danger');
        }
      });
    }
  }
}
