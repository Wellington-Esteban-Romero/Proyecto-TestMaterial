import { Component, Input, OnInit } from '@angular/core';
import { Material } from '../../class/Material';
import { DetallesService } from '../../services/detalles.service';

@Component({
  selector: 'app-detalles',
  templateUrl: './detalles.component.html',
  styleUrls: ['./detalles.component.css']
})
export class DetallesComponent implements OnInit {

  titulo = "Detalle Material";

  @Input() material: Material = new Material;

  constructor(
    public _detallesService: DetallesService
  ) { 
    console.log(this.material)
  }

  ngOnInit(): void {
    if (Object.keys(this.material).length > 0) {
      this.material.fechaAdquisicion = this.material.fechaAdquisicion.substring(0,1).toUpperCase() + this.material.fechaAdquisicion.substring(1).toLocaleLowerCase();
      console.log(this.material)
    }
  }

  closeModal() {
    this._detallesService.closeModal();
  }

}
