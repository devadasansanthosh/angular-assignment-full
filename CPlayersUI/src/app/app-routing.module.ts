import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchplayerComponent } from './cplayer/searchplayer/searchplayer.component'
import { PlayerdetailsComponent } from './cplayer/playerdetails/playerdetails.component';
import { LoginComponent } from './cplayer/login/login.component';
import { RegisterComponent } from './cplayer/register/register.component';
import { FavouritesComponent } from './cplayer/favourites/favourites.component';
import { CplayerserviceService } from './cplayer/services/cplayerservice.service';
import { AuthGuardServiceService } from './cplayer/services/auth-guard-service.service';
import { RecommendedComponent } from './cplayer/recommended/recommended.component';

const routes: Routes = [
  {
    path: "findplayer",
    component: SearchplayerComponent
  },
  {
    path: "details",
    component: PlayerdetailsComponent
  },
  {
    path: "",
    component: LoginComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "favorite",
    component: FavouritesComponent,
    canActivate: [AuthGuardServiceService]
  },
  {
    path: "recommended",
    component: RecommendedComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
