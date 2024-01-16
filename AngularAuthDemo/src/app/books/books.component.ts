import { Component, OnInit } from '@angular/core';
import {BooksService} from "../Services/books.service";
import {Book} from "../book";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-book',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './books.component.html',
  styleUrl: './books.component.css'
})
export class BooksComponent {
  books: Book[] = []

  constructor(
    private bookService: BooksService,
  ) {
  }

  ngOnInit() {
    this.bookService.getBooks().subscribe(b =>
      this.books = b
    )
  }

}
