import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpService {


  private getPreTab = '/api/clubs';
  private getPlyTab = 'api/matchs';
  private getRanPlyMatch='api/matchs/random';
  private getSearchedData='api/matchs/search';
  constructor(private http: HttpClient) {

  }

 /**
  * Makes request from frontend to backend to get data of premier league table
  * **/
  getPreTabData(keyToSort:String) {
  let string =this.getPreTab+'/'+keyToSort;
  console.log(string)
    return this.http.get(string);
  }

  /**
   * Method to Makes request from frontend to backend to get data of Match data
   * **/
  getMatchData(){
    return this.http.get(this.getPlyTab);
  }
  /**
   *  Method to Makes request from frontend to backend to get data of getRandomGenerated match data table
   * **/
  getRanMatchData(){
    return this.http.get(this.getRanPlyMatch);
  }

  /**
   * Method to  Makes request from frontend to backend to get data of searched  match data
   * **/
  getSearchedMatchData(c: String, b: String, a: String) {
    return this.http.get(this.getSearchedData+'/'+c+b+a);
  }
}
