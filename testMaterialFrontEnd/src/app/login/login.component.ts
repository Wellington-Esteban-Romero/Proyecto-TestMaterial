import { Component, Input, OnInit, } from '@angular/core';
import { Usuario } from '../class/Usuario';
import swal from 'sweetalert2';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() error: string = "";
  titulo: string = 'Iniciar Sesión en TestMaterial';
  usuario:Usuario;

  constructor (
    private _loginService:LoginService,
    private _router:Router
  ) {
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
    debugger
    if (this._loginService.isAuthenticated()) {
      swal.fire('Login', `Hola ${this.usuario.username}, ya estás autentificado!`, 'info')
      this._router.navigate(['/dashboard'])
    }
  }

  login():void {
    debugger
    console.log(this.usuario)

    if (this.usuario.username === undefined || this.usuario.password === undefined) {
      swal.fire('Error Login', 'Usuario o contraseña incorrecta', 'error')
      return;
    }

    this._loginService.login(this.usuario).subscribe (response => {
      console.log(response)
      let playload = JSON.parse(atob(response.token.split(".")[1]));
      this._loginService.guardarUsuario(response.username, response.authorities)
      this._loginService.guardarToken(response.token);
      this._router.navigate(['/dashboard'])
      swal.fire('Login', `Hola ${playload.sub}, inicio de sesión correcto`, 'success')
    },
    err => {
      if (err.status === 400 || err.status === 401) {
        swal.fire('Error Login', 'Usuario o contraseña incorrecta', 'error')
      }
    })

  }
}
