import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Router} from "@angular/router";
import {log} from "util";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private router: Router
  ) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const userToken = localStorage.getItem("token");
    let modifiedReq = request

    if (userToken) {
      modifiedReq = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${userToken}`)
      })
    }

    return next.handle(modifiedReq)
      // .pipe(
      // catchError((e: HttpErrorResponse) => {
      //   if (e.status === 401 || e.status === 403) {
      //     console.log("login needed")
      //     // this.router.navigate(['/unauthorized'])
      //   }
      //   return throwError(() => e)
      // }))
  }
}
