import { Routes } from '@angular/router';
import {BooksComponent} from  "./books/books.component"
import {AuthorsComponent} from "./authors/authors.component";
import {UnauthorizedComponent} from "./unauthorized/unauthorized.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {InitComponent} from "./init/init.component";

export const routes: Routes = [
  {path: 'books', component: BooksComponent},
  {path: 'authors', component:AuthorsComponent},
  {path: 'login', component:LoginComponent},
  {path: 'signup', component:SignupComponent},
  {path: 'unauthorized', component:UnauthorizedComponent},
  {path: 'init', component:InitComponent},
  {path:'', redirectTo:'/books', pathMatch:'full'},
];
