import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Book} from "../book";

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private booksUrl = 'http://localhost:8080/books';

  constructor(
    private http: HttpClient
  ) { }

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.booksUrl)
  }
}
