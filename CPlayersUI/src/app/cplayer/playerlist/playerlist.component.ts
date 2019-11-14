import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Cplayer } from '../cplayer';
import { MatDialog } from '@angular/material';
import { PlayerdetailsComponent } from '../playerdetails/playerdetails.component';

@Component({
  selector: 'app-playerlist',
  templateUrl: './playerlist.component.html',
  styleUrls: ['./playerlist.component.css']
})
export class PlayerlistComponent implements OnInit {

  @Input()
  cplayer: Cplayer;

  @Input()
  favoritedata: boolean;

  @Output()
  addToFavoriteList = new EventEmitter();

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }
  getDetails() {
    const dialogRef = this.dialog.open(PlayerdetailsComponent, {
      height: '75vh',
      width: '60vw',
      data: { pid: this.cplayer.pid }
    });
  }

  addToFavorite(cplayer: Cplayer) {
    this.addToFavoriteList.emit(cplayer);
  }
}
