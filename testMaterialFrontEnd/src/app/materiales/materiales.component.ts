import { Component, OnInit } from '@angular/core';
import { Material } from './../class/Material';
import { MaterialService } from '../services/material.service';
import { ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { DetallesService } from '../services/detalles.service';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-materiales',
  templateUrl: './materiales.component.html',
  styleUrls: ['./materiales.component.css']
})
export class MaterialesComponent implements OnInit {

  materiales: Material[] = [];
  refMaterial: Material = new Material();
  paginador: any

  constructor (
    private _materialServices: MaterialService,
    private _detalleService: DetallesService,
    public _loginService: LoginService,
    private activatedRoute: ActivatedRoute,
  ) {

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params  => {
      let page = parseInt( params['page'])
      if (isNaN(page))
        page = 0;
      this.getMateriales(page)
    }) 
  }

  getMateriales(page:number):void {
    this._materialServices.getMateriales(page).subscribe(
      response => {
        this.materiales = response.content as Material[]
        this.paginador = response
      }
    )
  }

  deleteMaterial(material:Material): void {
    swal.fire({
      title: '¿Desea eliminar?',
      text: `¿Está seguro que desea eliminar el material con código ${material.id} -  ${material.tipo.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      buttonsStyling: true,
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this._materialServices.deleteMaterial(material.id).subscribe(
          json => {
            this.materiales = this.materiales.filter ( item => item !== material )
            swal.fire(
              'Eliminado!',
              `El material ${material.tipo.nombre} fue eliminado.` ,
              'success'
            )
          }
        )
      }
    })
  }

  showModalDetalle(material: Material) {
    this.refMaterial = material;
    this._detalleService.openModal()
  }
}
