import { Component, OnInit } from '@angular/core';
import {HttpService} from "../http.service";
@Component({
  selector: 'app-club',
  templateUrl: './club.component.html',
  styleUrls: ['./club.component.css']
})
export class ClubComponent implements OnInit {
  clubs: object;
  keyToSort:String ="sortByPoints";
  constructor(private _http: HttpService) {
  }

  ngOnInit(): void {
    this._http.getPreTabData(this.keyToSort).subscribe(data => {
        this.clubs = data
        console.log(this.clubs);
      }
    );
  }
//creates the method to renew the value,
  //when clicking sortByPoints button
  sortByPts():void{
    this.keyToSort='sortByPoints';
    this.ngOnInit();
  }
  //creates the method to renew the value,
  //when clicking sortByWins button
  sortByWins():void{
    this.keyToSort='sortByWins';
    this.ngOnInit();
  }
  //creates the method to renew the value,
  //when clicking sortByGoals button
  sortByGoals():void{
    this.keyToSort='sortByGoals';
    this.ngOnInit();
  }



}
