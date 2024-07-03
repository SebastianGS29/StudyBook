import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ForgotPasswordService } from 'src/app/services/forgot-password.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.page.html',
  styleUrls: ['./forgot-password.page.scss'],
})
export class ForgotPasswordPage implements OnInit {
  form: FormGroup;
  emails: string[] = [];
  forgotPasswordSvc = inject(ForgotPasswordService);
  utilsSvc = inject(UtilsService);

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      email: ['', Validators.required],
      newPassword: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit() {
    this.loadEmails();
  }

  async loadEmails() {
    try {
      this.emails = await this.forgotPasswordSvc.getAllEmails();
    } catch (error) {
      console.error('Error loading emails', error);
      this.utilsSvc.presentToast({
        message: 'Error loading emails',
        duration: 2000,
        color: 'danger',
        position: 'middle',
        icon: 'alert-circle-outline'
      });
    }
  }

  async updatePassword() {
    const { email, newPassword } = this.form.value;
    if (this.form.valid) {
      try {
        await this.forgotPasswordSvc.updatePassword(email, newPassword);
        this.utilsSvc.presentToast({
          message: 'Password updated successfully',
          duration: 2000,
          color: 'success',
          position: 'middle',
          icon: 'checkmark-circle-outline'
        });
        this.form.reset();
      } catch (error) {
        console.error('Error updating password', error);
        this.utilsSvc.presentToast({
          message: 'Error updating password',
          duration: 2000,
          color: 'danger',
          position: 'middle',
          icon: 'alert-circle-outline'
        });
      }
    }
  }
}
