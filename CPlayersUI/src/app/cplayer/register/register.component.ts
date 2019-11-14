import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { Router } from '@angular/router';
import { CplayerserviceService } from '../services/cplayerservice.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User;
  constructor(private cplayerService: CplayerserviceService, private router: Router) {
      this.user= new User();
     }

  ngOnInit() {
  }

  register() {
    console.log(this.user);
    this.cplayerService.registerUser(this.user)
      .subscribe(data => {
        this.router.navigate(["/login"]);

      },
        error => {
          console.log(error);
        })
  }

}
