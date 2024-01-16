import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Book} from "../book";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class InitService {

  private initUrl = 'http://localhost:8080/initialize';

  constructor(
    private http: HttpClient
  ) { }

  initDB(): Observable<any> {
    return this.http.get<any>(this.initUrl)
  }
}
