import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cplayer } from '../cplayer';
import { CplayerserviceService } from '../services/cplayerservice.service';

@Component({
  selector: 'app-searchplayer',
  templateUrl: './searchplayer.component.html',
  styleUrls: ['./searchplayer.component.css']
})
export class SearchplayerComponent implements OnInit {

  cplayers: Array<Cplayer> = [];
  searchPlayers: Array<Cplayer> = [];
  id: number;
  playername: string;
  playerObj: Cplayer;
  statusCode: number;
  errorStatus: string;

  constructor(private routes: ActivatedRoute, private cplayerservice: CplayerserviceService) {
    this.cplayers = [];
   }

  ngOnInit() {
    this.cplayerservice.getAllPlayerList()
      .subscribe(player => {
        console.log(player);
        const data = player['data'];

        this.id = 0;
        data.forEach(element => {

          this.id++;
          this.playerObj = new Cplayer();

          this.playerObj.fullName = element["fullName"];
          this.playerObj.pid = element["pid"];
          this.playerObj.name = element["name"];


          this.cplayers.push(this.playerObj);
          
        });
      });
  }

  onKey(event: any) {

    if (event.keyCode == 13) {
      this.cplayers = [];
      this.playername = event.target.value;
      console.log('playername', this.playername);

      this.cplayerservice.getPlayerList(this.playername)
        .subscribe(player => {
          console.log(player);
          const data = player['data'];

          this.id = 0;
          data.forEach(element => {

            this.id++;
            this.playerObj = new Cplayer();

            this.playerObj.fullName = element["fullName"];
            this.playerObj.pid = element["pid"];
            this.playerObj.name = element["name"];


            this.cplayers.push(this.playerObj);
            this.searchPlayers.push(this.playerObj);
          });
        });
    }

  }
  addToFavoriteList(cplayer) {
    this.cplayerservice.addPlayerToFavoriteList(cplayer).subscribe(
      data => {

        console.log(data);
        },
      error => {

        this.errorStatus = `${error.status}`;
        const errorMsg = `${error.error.message}`;
        this.statusCode = parseInt(this.errorStatus, 10);
        
      }
    )
  }

}
