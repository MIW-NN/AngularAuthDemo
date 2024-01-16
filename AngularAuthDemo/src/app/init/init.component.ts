import { Component } from '@angular/core';
import {InitService} from "../Services/init.service";

@Component({
  selector: 'app-init',
  standalone: true,
  imports: [],
  templateUrl: './init.component.html',
  styleUrl: './init.component.css'
})
export class InitComponent {
  initText:string = "initializing Database"

  constructor(
    initService: InitService
  ) {
    initService.initDB().subscribe(s=>{
      this.initText=s.text;
    });
  }
}
