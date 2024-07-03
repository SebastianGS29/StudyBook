import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DaysService } from '../../../services/days.service';
import { UtilsService } from '../../../services/utils.service';
import { FirebaseService } from '../../../services/firebase.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  currentMonth: string;
  daysOfWeek: { day: string, date: string, isToday: boolean }[] = [];
  isSuperUser: boolean = false;
 // isAdminUser: boolean = false;
  user: any;

  constructor(
    private router: Router,
    private daysService: DaysService,
    private utilsSvc: UtilsService,
    private firebaseSvc: FirebaseService
  ) {
    const currentDate = new Date();
    const monthNames = [
      "Enero", "Febrero", "Marzo",
      "Abril", "Mayo", "Junio", "Julio",
      "Agosto", "Septiembre", "Octubre",
      "Noviembre", "Diciembre"
    ];
    this.currentMonth = monthNames[currentDate.getMonth()];
  }

  ngOnInit() {
    this.generateWeekDays();
    this.checkUserRole();
  }

  generateWeekDays() {
    const today = new Date();
    const startOfWeek = this.getStartOfWeek(today);

    for (let i = 0; i < 7; i++) {
      const currentDay = new Date(startOfWeek);
      currentDay.setDate(startOfWeek.getDate() + i);

      const optionsDay = { weekday: 'short' } as const;
      const optionsDate = { day: '2-digit' } as const;

      this.daysOfWeek.push({
        day: currentDay.toLocaleDateString('es-ES', optionsDay),
        date: currentDay.toLocaleDateString('es-ES', optionsDate),
        isToday: this.isToday(currentDay)
      });
    }
  }

  getStartOfWeek(date: Date): Date {
    const day = date.getDay(); // 0 (Sunday) to 6 (Saturday)
    const diff = date.getDate() - day + (day === 0 ? -6 : 1); // Adjust when day is Sunday
    return new Date(date.setDate(diff));
  }

  isToday(date: Date): boolean {
    const today = new Date();
    return date.toDateString() === today.toDateString();
  }

  checkUserRole() {
    this.user = this.utilsSvc.getFromLocalStorage('useradmin');
    if (this.user && this.user.rango === 'super') {
      this.isSuperUser = true;
    } 
    //else if (this.user && this.user.rango === 'admin'){
    //this.isAdminUser = true;
    //}
  }

  irAForgotPassword() {
    this.router.navigate(['forgot-password']);
  }

  irARegistrar() {
    this.router.navigate(['register']);
  }
 // irRegistrarEstudiante(){
 //   this.router.navigate(['regiterEstudiante']);
  //}

  showUnauthorizedMessage() {
    alert('Usted no está autorizado para esta acción.');
    this.router.navigate(['main/home']);
  }
}
