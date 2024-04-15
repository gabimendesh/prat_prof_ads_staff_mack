import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivateFn, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../../auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  return true;
};

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
    ) {}

    canActivate(
      next: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (!this.authService.isAuthenticated()) {
        this.router.navigate(['/login']);
        return false;
      } else {
        return true;
      }
    }
}
