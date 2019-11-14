//import { DetailsData } from "./DetailsData";

export class PlayerDetails {
    pid: string;
    profile: String;
    imageURL: String;


    battingStyle: string;
    bowlingStyle: String;
    majorTeams: String;

    currentAge: string;
    born: String;
    fullName: String;

    name: string;
    country: String;
    playingRole: String;
    //data: DetailsData;

    constructor() {
        this.pid = undefined;
        this.profile = undefined;
        this.imageURL = undefined;


        this.battingStyle = undefined;
        this.bowlingStyle = undefined;
        this.majorTeams = undefined;

        this.currentAge = undefined;
        this.born = undefined;
        this.fullName = undefined;

        this.name = undefined;
        this.country = undefined;
        this.playingRole = undefined;
        //this.data = undefined;
    }
}