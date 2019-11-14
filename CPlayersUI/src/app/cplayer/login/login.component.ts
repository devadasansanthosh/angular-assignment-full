import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { CplayerserviceService } from '../services/cplayerservice.service';
import { Router } from '@angular/router';
export const TOKEN_NAME = "token_name";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;

  constructor(private cplayerService: CplayerserviceService,
     private router: Router) {
      this.user= new User();
     }

  ngOnInit() {
  }

  login() {
    console.log("User----->"+this.user.username);
    this.cplayerService.loginUser(this.user).subscribe(data => {
      console.log("data--->"+data.toString);
      if (data) {
        localStorage.setItem(TOKEN_NAME, data["token"]);
        this.router.navigate(["/findplayer"]);
      }
    },
      error => {
        if (error.status === 404) {
          const errorMsg = error.error.message;
        }
      }
    )
  }
}
