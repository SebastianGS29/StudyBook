import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'main',
    loadChildren: () => import('./pages/main/main.module').then( m => m.MainPageModule)
  },
  {
    path: 'gestion',
    loadChildren: () => import('./pages/main/gestion/gestion.module').then( m => m.GestionPageModule)
  },
  {
    path: 'reportes',
    loadChildren: () => import('./pages/main/reportes/reportes.module').then( m => m.ReportesPageModule)
  },
  {
    path: 'mantenimiento',
    loadChildren: () => import('./pages/main/mantenimiento/mantenimiento.module').then( m => m.MantenimientoPageModule)
  },
  {
    path: 'gestionreserva',
    loadChildren: () => import('./pages/main/gestionreserva/gestionreserva.module').then( m => m.GestionreservaPageModule)
  },
  {
    path: 'forgot-password',
    loadChildren: () => import('./pages/main/forgot-password/forgot-password.module').then(m => m.ForgotPasswordPageModule)
  },
  {
    path: 'register',
    loadChildren: () => import('./pages/main/register/register.module').then(m => m.RegisterPageModule)
  },
];


// agregar aca las demas pages para que funcione URL

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
