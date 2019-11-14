import { Component, OnInit } from '@angular/core';
import { PlayerCount } from '../PlayerCount';
import { CplayerserviceService } from '../services/cplayerservice.service';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {
  favoritedata: boolean = true;
  players: Array<PlayerCount>;
  constructor(private cplayerService: CplayerserviceService) { }

  ngOnInit() {
    this.cplayerService.getRecommendedList().subscribe(data => {

      this.players = data;
    });
  }


}
