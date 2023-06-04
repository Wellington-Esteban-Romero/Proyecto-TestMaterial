import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Prueba } from '../../../class/Prueba';
import { PruebaService } from '../../../services/prueba.service';
import swal from 'sweetalert2';
import { MaterialService } from '../../../services/material.service';
import { Material } from '../../../class/Material';
import { TipoPrueba } from 'src/app/class/TipoPrueba';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-form-pruebas',
  templateUrl: './form-pruebas.component.html',
  styleUrls: ['./form-pruebas.component.css']
})
export class FormPruebasComponent implements OnInit {


  titulo:String = "Alta nueva prueba";

  pruebas: Prueba[] = [];

  tiposPruebas: TipoPrueba[] = [];

  material: Material = new Material();

  prueba: Prueba = new Prueba();

  errores:string[] = [];

  constructor (
    private _activatedRoute : ActivatedRoute,
    private _materialService:MaterialService,
    private _loginService : LoginService,
    private _pruebaService:PruebaService,
    ) {}

  ngOnInit(): void {
    this.getMaterial();
    this.getTiposPruebas();
  }

  getMaterial ():void {
    this._activatedRoute.params.subscribe(params => {
      let id = params["id"]
      if (id) {
        this._materialService.getMaterial(id).subscribe(
          material => this.material = material
        )
      }
    })
  }

  getTiposPruebas():void {
    this._pruebaService.getTiposPruebas().subscribe(
      tipos => this.tiposPruebas = tipos
    )
  }

  createPrueba ():void {
    debugger
    this._activatedRoute.params.subscribe(params => {
      let id = params["id"]
        if (id) {
          this.prueba.material = this.material;
          this.prueba.personaRealizaPrueba = this._loginService.usuario.username
          console.log(this.prueba)
          this._pruebaService.createPrueba(this.prueba,)
          .subscribe (
            json => {
                swal.fire('Nueva prueba', `Prueba ${json.prueba.nombre} se ha creado con éxito`, 'success').then((isConfirm) => {
                    location.reload()
                  });
              },
              e => {
                this.errores = e.error.errors as string[]
                console.log("status->"+e.error.status)
                console.log(e.error.errors)
          })
      }
    })
  }

  compararTipoPrueba (o1:TipoPrueba, o2:TipoPrueba):boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }
    return (o1 && o2) === undefined? false : o1.id === o2.id;
  }

}
