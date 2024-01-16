import {Component, OnInit} from '@angular/core';
import {Author} from "../author";
import {AuthorService} from "../Services/author.service";
import {NgForOf} from "@angular/common";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {routes} from "../app.routes";

@Component({
  selector: 'app-author',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './authors.component.html',
  styleUrl: './authors.component.css'
})
export class AuthorsComponent {
  authors: Author[] = []

  constructor(
    private authorService: AuthorService,
    private router: Router
  ) {

  }

  ngOnInit() {
    this.authorService.getAuthors()

      .pipe(
        catchError((e: HttpErrorResponse) => {
          console.log(e)
          if (e.status === 401 || e.status === 403)
            this.router.navigate(["/unauthorized"])
          return throwError(() => e)
        }))

      .subscribe(a =>
        this.authors = a
      )
  }

}
