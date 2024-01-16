import {Author} from "./author";
import {Copy} from "./copy";

export interface Book{
  bookId:number,
  title: string,
  authors: Author[],
  copies: Copy[],

}
