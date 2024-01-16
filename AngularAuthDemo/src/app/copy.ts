import {Book} from "./book";

export interface Copy{
  copyId: number,
  available: boolean,
  book: Book
}
