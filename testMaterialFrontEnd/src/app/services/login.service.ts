import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Usuario } from '../class/Usuario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

    private urlEndPoint = "http://localhost:8080/api/rest";
    _usuario: Usuario | undefined | null;
    private _token:any;

    constructor (
        private http:HttpClient
    ) {

    }

    login (usuario:Usuario):Observable<any> {
        const httpHeaders = new HttpHeaders({'Content-Type':'application/json'})
       return this.http.post(this.urlEndPoint + '/login', usuario, {headers:httpHeaders})
    }

    logout ():void {
        this._token = null;
        this._usuario = null
        sessionStorage.removeItem('token')
        sessionStorage.removeItem('usuario')
    }

    guardarUsuario(username:string, authorities:[]):void {
        this._usuario = new Usuario()
        this._usuario.username = username;
        this._usuario.roles = authorities;

        sessionStorage.setItem('usuario', JSON.stringify(this._usuario))
    }

    guardarToken (token:string):void {
        this._token = token;
        sessionStorage.setItem('token', this._token)
    }

    public get usuario ():any {
        if (this._usuario) {
            return this._usuario
        } else if (this._usuario == null && sessionStorage.getItem('usuario') != null) {
            this._usuario = JSON.parse(sessionStorage.getItem('usuario') || '') as Usuario;
            return this._usuario;
        }
        return new Usuario();
    }

    public get token ():any {
        if (this._token) {
            return this._token
        } else if (this._token == null && sessionStorage.getItem('token') != null) {
            this._token = sessionStorage.getItem('token')
            return this._token;
        }
        return null;
    }

    getUserToken (accessToken:string):any {
        if (accessToken != null) {
           return JSON.parse(atob(accessToken.split(".")[1]));
        }
        return null;
    }

    isAuthenticated (): boolean {
        let playload = this.getUserToken(this.token);
        if (playload && playload.sub && playload.sub.length > 0) {
            return true
        }
        return false;
    }

    hasRole(role:string):boolean {
        if (this.usuario.roles.map((a:any)=>a.authority).includes(role)) {
            return true;
        }
        return false;
    }

}
