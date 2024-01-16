import {Component} from '@angular/core';
import {FormsModule} from "@angular/forms";
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
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  user: User = {}
  warnText: string = "";

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  signup() {
    if ((!this.user.username || this.user.username == "") ||
      (!this.user.password || this.user.password == "")) {
      this.warnText = "name and password needed";
      return;
    }

    this.authService.signup(this.user).pipe(
      catchError((e: HttpErrorResponse) => {
        console.log(e)
        if (e.status===401)
        this.warnText = "incorrect username or password";
        return throwError(() => e)
      })
    ).subscribe(d=>{
      this.router.navigate(["/login"])
    })
  }
}
