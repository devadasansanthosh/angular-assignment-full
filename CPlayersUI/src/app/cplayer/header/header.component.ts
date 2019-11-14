import { Component, OnInit } from '@angular/core';
import { CplayerserviceService } from '../services/cplayerservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private cplayerService: CplayerserviceService, private router: Router) { }

  ngOnInit() {
  }
  logout() {
    this.cplayerService.logout();
   this.router.navigate(['/login']);
 }

}
