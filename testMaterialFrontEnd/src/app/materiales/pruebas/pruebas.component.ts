import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PruebaService } from '../../services/prueba.service';
import { Prueba } from '../../class/Prueba';

@Component({
  selector: 'app-pruebas',
  templateUrl: './pruebas.component.html',
  styleUrls: ['./pruebas.component.css']
})
export class PruebasComponent implements OnInit {

  pruebas:Prueba[] = [];

  codigo: number = 0;

  constructor (
    private _pruebaService: PruebaService,
    private _activatedRoute : ActivatedRoute
    ) {}

  ngOnInit(): void {
    this.getPruebas();
  }

  getPruebas():void {
    this._activatedRoute.params.subscribe(params => {
      let id = params["id"]
      if (id) {
        this._pruebaService.getPruebas(id).subscribe (response => {
          this.pruebas = response as Prueba[]
          this.codigo = id;
        })
      }
    })
  }

}
