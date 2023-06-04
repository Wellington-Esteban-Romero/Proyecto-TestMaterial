import { Component, OnInit } from '@angular/core';
import { MaterialService } from '../../services/material.service';
import { Material } from '../../class/Material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import swal from 'sweetalert2';
import { DatePipe } from '@angular/common';
import { TipoMaterial } from '../../class/TipoMaterial';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit  {

  submissionForm: FormGroup<any> = [] as any;
  submitted=false;
  tiposMateriales: TipoMaterial[] = [];
  materiales: Material[] = [];
  material:Material = new Material();
  titulo: String = "Crear Material";
  errores:string[] = [];
  codigo:String = "";
  fromDate: any;

  constructor (
    private _materialService: MaterialService,
    private _router: Router,
    private _activatedRoute : ActivatedRoute,
    ) {}

  ngOnInit(): void {
    this.getMaterial();
    this.changeTitleForm();
    this.showCodigoMaterial();
  }

  getMaterial():void {
    this._activatedRoute.params.subscribe(params => {
      let id = params["id"]
      if (id) {
        this._materialService.getMaterial(id).subscribe(
          material => this.material = material
        )
      }
      this._materialService.getTiposMateriales().subscribe(
        tipos => this.tiposMateriales = tipos
      )
    })
  }

  createMaterial():void {
    debugger
    this._materialService.createMaterial(this.material)
    .subscribe (
      json => {
        this._router.navigate(['/materiales'])
        swal.fire('Nuevo material', `Material ${json.material.tipo.nombre} se ha creado con éxito`, 'success')
      },
      e => {
        this.errores = e.error.errors as string[]
        console.log("status->"+e.error.status)
        console.log(e.error.errors)
      })
  }

  updateMaterial():void {
    this._activatedRoute.params.subscribe(params => {
      let id = params["id"]
      if (id) {
        this._materialService.updateMaterial(this.material, id)
        .subscribe (
          json => {
            console.log(json)
            this._router.navigate(['/materiales'])
            swal.fire('Actualización material', `Material ${json.material.tipo.nombre} se ha modificado con éxito`, 'success')
          },
          e => {
            this.errores = e.error.errors as string[]
            console.log("status->"+e.error.status)
            console.log(e.error.errors)
          })
      }
    })
  }

  changeTitleForm():void {
    this._activatedRoute.params.subscribe(params => {
      let id = params["id"]
      if (id) {
        this.titulo = "Editar Material";
      }
    })
  }

  showCodigoMaterial() {
    this._activatedRoute.params.subscribe(params => {
      let id = params["id"]
      if (id) {
        this.codigo = id;
      }
    })
  }

  compararTipoMaterial (o1:TipoMaterial, o2:TipoMaterial):boolean {
    if (o1 === undefined && o2 === undefined) {
      return true;
    }
    return (o1 && o2) === undefined? false : o1.id === o2.id;
  }

}
