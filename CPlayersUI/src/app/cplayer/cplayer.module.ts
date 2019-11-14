import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { MatToolbarModule, MatSidenavModule, MatIconModule,  MatListModule, MatFormFieldModule, MatInputModule, MatCardModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { SearchplayerComponent } from './searchplayer/searchplayer.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { PlayerlistComponent } from './playerlist/playerlist.component';
import { CplayerserviceService } from './services/cplayerservice.service';
import { PlayerdetailsComponent } from './playerdetails/playerdetails.component';
import { MatDialogModule } from '@angular/material/dialog';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { FavouritesComponent } from './favourites/favourites.component';
import { RecommendedComponent } from './recommended/recommended.component';
import {MatButtonModule} from '@angular/material/button';

@NgModule({
  declarations: [HeaderComponent, SearchplayerComponent, PlayerlistComponent, PlayerdetailsComponent, LoginComponent, RegisterComponent, FavouritesComponent, RecommendedComponent],
  imports: [
    CommonModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MatListModule,
    MatSidenavModule,
    MatIconModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatDialogModule,
    FormsModule,
    MatButtonModule
  ],
  exports: [
    HeaderComponent,
    SearchplayerComponent,
    PlayerlistComponent,
    PlayerdetailsComponent
    ],
  providers: [
    CplayerserviceService
    ],
})
export class CplayerModule { }
