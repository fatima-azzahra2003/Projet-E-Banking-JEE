// src/app/interceptors/app-http.interceptor.ts
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import {catchError, Observable, throwError} from "rxjs";
import { Injectable } from '@angular/core';
@Injectable()
export class appHttpInterceptor implements HttpInterceptor{
  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("*****")
    console.log(request.url);

    if (!request.url.includes("/auth/login") && this.authService.accessToken) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authService.accessToken}`
        }
      });
    }

    return next.handle(request).pipe(
      catchError(err => {
        if (err.status === 401) {
          this.authService.logout();
        }
        return throwError(() => err);
      })
    );
  }}
