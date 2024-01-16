import {Component} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {BooksService} from "../Services/books.service";
import {AuthService} from "../Services/auth.service";
import {User} from "../user";
import {NgIf} from "@angular/common";
import {Router} from "@angular/router";
import {catchError, throwError} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user: User = {}
  warnText: string = "";

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  login() {
    if ((!this.user.username || this.user.username == "") ||
      (!this.user.password || this.user.password == "")) {
      this.warnText = "name and password needed";
      return;
    }

    this.authService.login(this.user).pipe(
      catchError((e: HttpErrorResponse) => {
        console.log(e)
        if (e.status===401)
        this.warnText = "incorrect username or password";
        return throwError(() => e)
      })
    ).subscribe(s => {
        if (s) {
          console.log(s)
          localStorage.setItem("token", s);
          this.router.navigate(["/"]);
        }
      }
    )

  }
}
