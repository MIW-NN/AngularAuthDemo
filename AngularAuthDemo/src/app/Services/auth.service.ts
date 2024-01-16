import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Author} from "../author";
import {HttpClient} from "@angular/common/http";
import {User} from "../user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:8080/login';
  private signupUrl = 'http://localhost:8080/signup';

  constructor(
      private http: HttpClient
  ) {
  }

  public login(user: User): Observable<any> {
    return this.http.post<string>(this.loginUrl, user, {responseType: 'text' as 'json'})
  }

  public static isLoggedIn() {
    let token = localStorage.getItem("token");

    return token !== null && token !== "";

  }

  public logout() {
    localStorage.setItem("token", "")
  }

  public signup(user: User): Observable<any> {
    return this.http.post<string>(this.signupUrl, user, {responseType: 'text' as 'json'})
  }
}
