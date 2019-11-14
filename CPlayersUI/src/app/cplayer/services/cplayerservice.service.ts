import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cplayer } from '../cplayer';
import { TOKEN_NAME } from '../login/login.component';
import { Router } from '@angular/router';
import { PlayerCount } from '../PlayerCount';
export const USER_NAME = "username";

@Injectable({
  providedIn: 'root'
})
export class CplayerserviceService {

  cplayerApi: String;
  apiKey: String;
  loginEndPoint: string;
  registerEndPoint: string;
  favouritEndPoint: string;
  username: string;
  recommendedEndPoint: string;

  constructor(private httpClient: HttpClient,  private router: Router) {
    this.cplayerApi = 'https://cricapi.com/api/playerFinder?';
    this.apiKey = 'apikey=YZ1xk5fBMqhnIE63yWHAyjBLGJP2';
    this.loginEndPoint= 'http://localhost:7071/api/v1/userservice/login';
    this.registerEndPoint= 'http://localhost:7071/api/v1/userservice/register';
    this.favouritEndPoint= 'http://localhost:7072/api/v1/favoriteservice';
    this.recommendedEndPoint = 'http://localhost:7074/api/v1/playerrecommendationservice'
   }

   getAllPlayerList(): Observable<any> {
    this.cplayerApi = 'https://cricapi.com/api/playerFinder';
    this.apiKey = '?apikey=YZ1xk5fBMqhnIE63yWHAyjBLGJP2&name=Sachin';
    
    const url = `${this.cplayerApi}${this.apiKey}`;

    return this.httpClient.get(url);
  }

  getPlayerDetails(pid: String): Observable<any> {

    this.cplayerApi = 'https://cricapi.com/api/playerStats';
    this.apiKey = '?apikey=YZ1xk5fBMqhnIE63yWHAyjBLGJP2&pid=' + pid;
    const url = `${this.cplayerApi}${this.apiKey}`;
    return this.httpClient.get(url);

  }

  getPlayerList(name: String): Observable<any> {
    this.cplayerApi = 'https://cricapi.com/api/playerFinder';
    this.apiKey = '?apikey=YZ1xk5fBMqhnIE63yWHAyjBLGJP2&name=' + name;
    const url = `${this.cplayerApi}${this.apiKey}`;
    return this.httpClient.get(url);

  }

  loginUser(newUser) {
    const url = this.loginEndPoint;
    sessionStorage.setItem(USER_NAME, newUser.username);
    return this.httpClient.post(url, newUser);
  }
  
  registerUser(newUser) {
    const url = this.registerEndPoint;
    return this.httpClient.post(url, newUser);
  }

  getFavoriteList(): Observable<Cplayer[]> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.favouritEndPoint + "/user/" + this.username + "/player";
    return this.httpClient.get<Cplayer[]>(url);

  }

  addPlayerToFavoriteList(cplayer: Cplayer) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.favouritEndPoint + "/user/" + this.username + "/player";
    return this.httpClient.post(url, cplayer)
  }

  getRecommendedList(): Observable<any> {
    const url = this.recommendedEndPoint + "/list";
    return this.httpClient.get(url);

  }

  logout() {
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
    localStorage.removeItem("token_name");
    localStorage.clear();
    console.log("logged out");
  }
  
}
