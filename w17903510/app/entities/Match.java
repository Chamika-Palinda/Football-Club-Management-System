package entities;

import java.io.Serializable;

public class Match implements Serializable {


    private FootballClub team1;
    private FootballClub team2;
    private int team1Score;
    private int team2Score;
    private String team1Name;
    private String team2Name;

    private DateTime date;

    public FootballClub getTeam1(){
        return team1;
    }

    public FootballClub getTeam2(){
        return team2;
    }

    public int  getTeam1Score(){
        return team1Score;
    }

    public int getTeam2Score(){
        return team2Score;
    }

    public DateTime getDate(){
        return date;
    }

    public void setTeam1(FootballClub team1){
        this.team1 =team1;
        this.team1Name=team1.getClubName();
    }

    public void setTeam2(FootballClub team2){
        this.team2 =team2;
        this.team2Name=team2.getClubName();
    }

    public void setTeam1Score(int team1Score){
        this.team1Score=team1Score;
    }
    public void setTeam2Score(int team2Score){
        this.team2Score=team2Score;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }


    public String getTeam1Name(){
        return team1Name;
    }

    public String getTeam2Name(){
        return team2Name;
    }


    public void setTeam1Name(String clubName){
        this.team1Name= team1.getClubName();
    }
    public void setTeam2Name(String clubName){
        this.team2Name= team2.getClubName();
    }

    @Override
    public String toString() {
        return  "{"+
                "Home Club=" + team1Name +
                ", Away Club=" + team2Name+
                ", HomeScore=" + team1Score +
                ", AwayScore=" + team2Score +
                ", date=" + date
                +"}";
    }


}
