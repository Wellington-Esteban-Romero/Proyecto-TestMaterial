import { Component, AfterViewInit, ElementRef, ViewChild,OnInit  } from '@angular/core';
import { Chart } from 'chart.js';
import { MaterialService } from '../services/material.service';
import { TipoMaterial } from '../class/TipoMaterial';
import { Prueba } from '../class/Prueba';

@Component({
  selector: 'app-doughnutchart',
  templateUrl: './doughnutchart.component.html',
  styleUrls: ['./doughnutchart.component.css']
})
export class DoughNutChartComponent implements AfterViewInit, OnInit {

  @ViewChild('doughnutCanvas') doughnutCanvas: ElementRef | undefined;

  doughnutChart: any;
  tiposMateriales: TipoMaterial[] = []
  totalMateriales:number=0;
  arrayPrueba: Prueba[] = [];
  totalPruebas:number = 0;
  arrayCoste: number[] = [];
  costeTotal:number = 0;

  ngOnInit(): void {}

  ngAfterViewInit() {
    this._materialService.getTotalMateriales().subscribe(
      material => {
        this.totalMateriales = material.length;
        this.arrayPrueba = material.map((p:any)=>p.pruebas);
        this.arrayPrueba = this.arrayPrueba.flat()
        this.totalPruebas = this.arrayPrueba.length;
        this.arrayCoste = this.arrayPrueba.map((p:Prueba)=> p.coste)
        if (this.arrayCoste.length == 0) {
          this.arrayCoste = [0]
        }
        this.costeTotal = this.arrayCoste.reduce ((total:number, num:number) => total + Math.round(num))
        this.doughnutChartMethod()
    }
    )
  }

  constructor(
    private _materialService:MaterialService
  ) {}

  doughnutChartMethod() {
    this.doughnutChart = new Chart(this.doughnutCanvas?.nativeElement, {
      type: 'doughnut',
      data: {
        labels: ["Materiales", "Pruebas"],
        datasets: [
          {
            label: '# Total: ',
            data: [this.totalMateriales, this.totalPruebas],
            backgroundColor: [
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 99, 132, 0.2)',
            ],
            hoverBackgroundColor: [
              '#36A2EB',
              '#FF6384',
            ],
          },
        ],
      },
    });
  }

}
