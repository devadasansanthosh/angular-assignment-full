import { Component, OnInit, Inject } from '@angular/core';
import { PlayerDetails } from '../PlayerDetails';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Cplayer } from '../cplayer';
import { ActivatedRoute } from '@angular/router';
import { CplayerserviceService } from '../services/cplayerservice.service';
import { deserialize } from 'json-typescript-mapper';

@Component({
  selector: 'app-playerdetails',
  templateUrl: './playerdetails.component.html',
  styleUrls: ['./playerdetails.component.css']
})
export class PlayerdetailsComponent implements OnInit {

  playerDetails: PlayerDetails;
  pid: String;

  constructor(private cplayerService: CplayerserviceService
    , private routes: ActivatedRoute,
    public dialogRef: MatDialogRef<PlayerdetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public cplayer: Cplayer) {
      this.pid = this.cplayer.pid;
     }

  ngOnInit() {

    this.cplayerService.getPlayerDetails(this.pid)
      .subscribe(data1 => {
        this.playerDetails = deserialize(PlayerDetails, data1);
      });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
