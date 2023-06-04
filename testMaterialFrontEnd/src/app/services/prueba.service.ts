import { Injectable } from "@angular/core";
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Router } from "@angular/router";
import { Prueba } from "../class/Prueba";
import swal from 'sweetalert2';
import { Material } from "../class/Material";
import { formatDate } from "@angular/common";
import { TipoPrueba } from "../class/TipoPrueba";
import { LoginService } from "./login.service";

@Injectable({
    providedIn: 'root'
  })
export class PruebaService {

    private urlEndPoint = "http://localhost:8080/api/rest";
    private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

    constructor(
        private http: HttpClient,
        private router: Router,
        private _loginService:LoginService,
    ) { }

    private isNoAutenticado (e:any):boolean {
      debugger
      if (e.status === 401) {
        if (this._loginService.isAuthenticated()) { // para comprobar si estamos autentificado en angular y en el back expira el token
          this._loginService.logout();
        }
        this.router.navigate(['/login']);
        return true;
      }

      if ( e.status === 403) {//acceso prohibido
        swal.fire('Acceso denegado', 'No tiene acceso', 'warning')
        this.router.navigate(['/materiales']);
        return true;
      }
      return false;
    }

    private addAuthorizationHeader () {
      const token = this._loginService.token;
      debugger
      if (token != null) {
        return this.httpHeaders.append('Authorization', 'Bearer ' + token)
      }
      return this.httpHeaders;
    }

    getTiposPruebas ():Observable<TipoPrueba[]> {
      return this.http.get<TipoPrueba[]>(this.urlEndPoint + "/material/tiposPruebas", {headers:this.addAuthorizationHeader ()});
    }

    getPruebas(id:number):Observable<any> {
      return this.http.get(this.urlEndPoint + "/pruebas" + `/${id}`, {headers:this.addAuthorizationHeader ()}).pipe(
        map( (response : any) =>  {
          (response as Prueba[]).map(prueba => {
            prueba.nombre = prueba.nombre.charAt(0).toUpperCase() + prueba.nombre.substring(1);
            prueba.tiempoEjecucion = prueba.tiempoEjecucion + " horas";
            prueba.fechaCreacion = formatDate(prueba.fechaCreacion, 'dd/MM/yyyy hh:mm', 'es')
            return prueba
          }),
          catchError (e => {
            if (this.isNoAutenticado(e)) {
              return throwError(() => e)
            }
            this.router.navigate(['/materiales'])
            swal.fire('Error', e.error.mensaje, 'error')
            return throwError(() => e)
          })
          return response
        })
      )
  }

  createPrueba(prueba:Prueba):Observable<any> {//->Hay que pasar le el material por parametro
        debugger
        //prueba.personaRealizaPrueba = "Manuel"; //-> pasar cuando se conecte
        return this.http.post<any>(this.urlEndPoint + "/prueba", prueba, {headers:this.addAuthorizationHeader ()}).pipe(
          map( (response:any) => response as any),
          catchError(e => {
            if (this.isNoAutenticado(e)) {
              return throwError(() => e)
            }

            if (e.status == 400) {
              return throwError(() => e)
            }
            this.router.navigate(['/materiales'])
            swal.fire('Error', e.error.mensaje, 'error')
            return throwError(() => e)
          })
        )
    }

    getTotalPruebas():Observable<any> {
      debugger
      return this.http.get(this.urlEndPoint + "/pruebas", {headers:this.addAuthorizationHeader ()}).pipe(
        map( (response : any) =>  {
          console.log(response)
          return (response as Prueba[])
        }),
        catchError(e => {
          this.isNoAutenticado(e);
          return throwError(() => e)
        })
      )
    }

    checkNumber(num:number) {
      return isNaN(num)
    }
}