import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { GestionreservaPageRoutingModule } from './gestionreserva-routing.module';

import { GestionreservaPage } from './gestionreserva.page';
import { SharedModule } from 'src/app/shared/shared.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    GestionreservaPageRoutingModule,
    SharedModule,
    HttpClientModule
  ],
  declarations: [GestionreservaPage]
})
export class GestionreservaPageModule {}
