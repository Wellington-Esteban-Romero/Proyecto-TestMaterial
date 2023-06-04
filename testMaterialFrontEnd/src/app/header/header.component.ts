import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username:string = "";

  constructor (
   public _loginService:LoginService,
   private _router:Router
  ) {

  }

  ngOnInit(): void {
  }

  logout(): void {
    this.username = this._loginService.usuario.username;
    this._loginService.logout();
    swal.fire('Cerrar sesión', `Hola ${this.username}, has cerrado sesión con éxito!` , 'info');
    this._router.navigate(['/login']);
  }
}
