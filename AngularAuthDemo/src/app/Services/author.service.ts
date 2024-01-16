import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Author} from "../author";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  private authorsUrl = 'http://localhost:8080/authors';

  constructor(
    private http: HttpClient
  ) { }

  getAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(this.authorsUrl)
  }
}
