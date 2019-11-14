import { Component, OnInit } from '@angular/core';
import { Cplayer } from '../cplayer';
import { CplayerserviceService } from '../services/cplayerservice.service';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {
  cplayers: Array<Cplayer>;
  favoritedata: boolean = true;
  constructor(private cplayerService: CplayerserviceService) { }

  ngOnInit() {
    this.cplayerService.getFavoriteList().subscribe(data => {
      this.cplayers = data;
    });
  }

}
