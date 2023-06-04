import { Component, Input, OnChanges, OnInit } from '@angular/core';

@Component({
  selector: 'app-paginador',
  templateUrl: './paginador.component.html',
  styleUrls: ['./paginador.component.css']
})
export class PaginadorComponent implements OnInit, OnChanges {

  @Input() paginador: any

  paginas: Array<number> = [];

  desde: number = -1;

  hasta: number = -1;

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges() {
    this.initPaginador()
  }

  private initPaginador() {
    this.desde = Math.min(Math.max(1, this.paginador.number-4), this.paginador.totalPages-5)
    this.hasta = Math.max(Math.min(this.paginador.totalPages, this.paginador.number+4), 6)

    if (this.paginador.totalPages > 6) {
      this.paginas = new Array(this.hasta-this.desde+1)
          .fill(0)
          .map((_value, key) => key + this.desde)
    } else {
       this.paginas = new Array(this.paginador.totalPages)
          .fill(0)
          .map((_value, key) => key + 1)
    }
  }

}
