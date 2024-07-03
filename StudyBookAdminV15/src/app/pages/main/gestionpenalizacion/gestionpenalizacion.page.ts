import { Component, OnInit } from '@angular/core';
import { GestionpenalizacionService } from 'src/app/services/gestionpenalizacion.service';

@Component({
  selector: 'app-gestionpenalizacion',
  templateUrl: './gestionpenalizacion.page.html',
  styleUrls: ['./gestionpenalizacion.page.scss'],
})
export class GestionpenalizacionPage implements OnInit {

  disabledUser: any[] = [];

  constructor(private gestionpenalizacionService: GestionpenalizacionService) { }

  ngOnInit() {
    this.getAllDisabledUser();
  }
  getAllDisabledUser(): void {
    this.gestionpenalizacionService.getAllDisabledUser().subscribe((data) => {
      this.disabledUser = data;
    });
  }

  enableUser(email: string): void {
    this.gestionpenalizacionService.updateDisabledUserByEmail(email).subscribe(
      (response: any) => {
        console.log('User enabled', response);
        // Remove the user from the local list
        this.disabledUser = this.disabledUser.filter(user => user.email !== email);
      },
      (error) => {
        console.error('Error enabling user', error);
      }
    );
  }

}
