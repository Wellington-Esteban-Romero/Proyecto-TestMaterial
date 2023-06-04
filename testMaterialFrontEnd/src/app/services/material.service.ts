import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Material } from '../class/Material';
import { TipoMaterial } from '../class/TipoMaterial';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  private urlEndPoint = "http://localhost:8080/api/rest"
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

      if (token != null) {
        return this.httpHeaders.append('Authorization', 'Bearer ' + token)
      }
      return this.httpHeaders;
    }

    getTotalMateriales():Observable<any> {
      debugger
      return this.http.get(this.urlEndPoint + "/materiales", {headers:this.addAuthorizationHeader ()}).pipe(
        map( (response : any) =>  {
          return (response as Material[])
        }),
        catchError(e => {
          this.isNoAutenticado(e);
          return throwError(() => e)
        })
      )
    }

    getMateriales(page:number):Observable<any> {
        return this.http.get(this.urlEndPoint + "/materiales/page" + `/${page}`, {headers:this.addAuthorizationHeader ()}).pipe(
          map( (response : any) =>  {
            (response.content as Material[]).map(material => {
              material.tipo.nombre = material.tipo.nombre.charAt(0).toUpperCase() + material.tipo.nombre.substring(1)
              material.fabricante =  material.fabricante.charAt(0).toUpperCase() +  material.fabricante.substring(1)
              return material
            })
            return response
          }),
          catchError(e => {
            this.isNoAutenticado(e);
            return throwError(() => e)
          })
        )
    }

    getMaterial (id:number):Observable<Material> {
      return this.http.get<Material>(this.urlEndPoint + "/material" + `/${id}`,{headers:this.addAuthorizationHeader ()}).pipe(
        map((response : Material) => {
          return response
        }),
        catchError (e => {
          if (this.isNoAutenticado(e)) {
            return throwError(() => e)
          }
          this.router.navigate(['/materiales'])
          swal.fire('Error', e.error.mensaje, 'error')
          return throwError(() => e)
        })
      )
    }

    createMaterial(material:Material):Observable<any> {
      debugger
      return this.http.post<any>(this.urlEndPoint + "/material", material, {headers:this.addAuthorizationHeader ()}).pipe(
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

    updateMaterial(material:Material, id:number):Observable<any> {
      debugger
      return this.http.put<any>(this.urlEndPoint + "/material" + `/${id}`, material, {headers:this.addAuthorizationHeader ()}).pipe(
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

    deleteMaterial(id:number):Observable<any> {
      return this.http.delete<any>(this.urlEndPoint + "/material" + `/${id}`, {headers:this.addAuthorizationHeader ()}).pipe(
        map( (response:any) => response as any),
        catchError(e => {
          if (this.isNoAutenticado(e)) {
            return throwError(() => e)
          }

          this.router.navigate(['/materiales'])
          swal.fire('Error', e.error.mensaje, 'error')
          return throwError(() => e)
        })
      )
    }

    getTiposMateriales ():Observable<TipoMaterial[]> {
      return this.http.get<TipoMaterial[]>(this.urlEndPoint + "/material/tiposMateriales", {headers:this.addAuthorizationHeader ()}).pipe(
        catchError(e => {
          this.isNoAutenticado(e);
          return throwError(() => e) 
        })
      );
    }

    checkNumber(num:number) {
      return isNaN(num)
    }


  }
