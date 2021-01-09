import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent implements OnInit {
  //creating object to receives all the match objects
  matchs: object
  constructor(private _http: HttpService) { }

  //store all the match data from the which are received from backend
  ngOnInit(): void {
    this._http.getMatchData().subscribe(data => {
        this.matchs = data
        console.log(this.matchs);
      }
    );
  }

  //store all the match data from the which are received from backend
  btnRandomMatch() {
    this._http.getRanMatchData().subscribe(data =>{
      this.matchs=data
      console.log(this.matchs);
      this.ngOnInit();
    })
  }

  //store all the match data from the which are received from backend
  SearchByDate():void {
    var date = (<HTMLInputElement>document.getElementById("dateGetter")).value;
    var array = date.split('-'),
      a = array[0], b = array[1], c = array[2];
    console.log(a, b, c);

    this._http.getSearchedMatchData(c, b, a).subscribe(data => {
      this.matchs = data
      console.log(this.matchs);


    })
  }


}
