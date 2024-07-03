import { Component, OnInit, inject } from '@angular/core';
import { UtilsService } from 'src/app/services/utils.service';
import { ReportesService } from 'src/app/services/reportes.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.page.html',
  styleUrls: ['./reportes.page.scss'],
})
export class ReportesPage implements OnInit {
  reservations: any[] = [];

  utilsSvc = inject(UtilsService)


  constructor(private reportesService: ReportesService) {
  }

  async ngOnInit() {
    this.loadReportes();
  }

  async loadReportes() {
    const loading = await this.utilsSvc.loading();
    await loading.present();
    this.reportesService.getAllReportes().subscribe(data => {
      this.reservations = data;
      loading.dismiss();
    },
      error => {
        console.error('Aqui hay un error!', error);
        loading.dismiss();
      });
  }

  descargarPdf() {
    this.reportesService.descargarReportePdf().subscribe(response => {
      const blob = new Blob([response], { type: 'application/pdf' });
      saveAs(blob, 'reporte.pdf');
    });
  }
}