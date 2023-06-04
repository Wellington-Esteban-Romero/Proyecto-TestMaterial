import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/services/login.service';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard  {

  constructor (
    private _loginService:LoginService,
    private _router:Router,
  ) {

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    debugger
      let role = route.data['role'] as string;
      if (this._loginService.hasRole(role)) {
        console.log(role)
        return true;
      }
      swal.fire('Acceso denegado', 'No tiene acceso', 'warning')
      this._router.navigate(['/materiales']);
    return false;
  }
  
}
