import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  accessToken!: string ;
  isAuthenticated: boolean = false;
  roles: any;
  username: any;
  password: any;


  constructor(private http: HttpClient , private router: Router) {

  }

  public login(username: string, password: string) {
    let options = {
      headers: new HttpHeaders().set("Content-Type", "application/x-www-form-urlencoded")
    };
    let paras = new HttpParams().set("username", username).set("password", password);
    return this.http.post("http://localhost:8085/auth/login", paras, options);

  }

  public loadProfile(data: any) {
    this.isAuthenticated = true;
    this.accessToken = data["access-token"];
    let decodeJwt:any=jwtDecode(this.accessToken);
    this.roles = decodeJwt.scope;
    this.username = decodeJwt.sub;
    window.localStorage.setItem("access_token", this.accessToken);
  }

  logout() {
    this.isAuthenticated= false;
    this.accessToken ="";
    this.username= undefined;
    this.roles= undefined;
    window.localStorage.removeItem("access_token");
    this.router.navigateByUrl("/login");
  }

  loadJwtTokenFromLocalStorage() {
    let token= window.localStorage.getItem("access_token");
    if (token != null) {
      this.loadProfile({"access-token": token});
      this.router.navigateByUrl("/admin/customers");
    }
  }
}
