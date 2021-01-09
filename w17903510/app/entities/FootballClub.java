package entities;

import java.io.Serializable;

public class FootballClub extends SportsClub implements Serializable {

    private int clubWins;
    private int clubDraws;
    private int clubDefeats;
    private int seasonGoalsScored;
    private int seasonGoalsReceived;
    private int numOfMatches;
    private int clubPoints;
    private int maxAgePlayer;


    public FootballClub() {
    }

    public FootballClub(String clubName, String managerName, int since, String clubLocation, int clubWins, int clubDraws, int clubDefeats, int maxAgePlayer) {
        super(clubName, managerName, since, clubLocation);
        this.clubWins= clubWins;
        this.clubDraws= clubDraws;
        this.clubDefeats= clubDefeats;
        this.maxAgePlayer=maxAgePlayer;
        this.numOfMatches=clubDefeats+clubDraws+clubWins;
        this.clubPoints = clubWins*3+clubDraws*1;
    }




        public void setClubWins(int clubWins) {
        this.clubWins = clubWins;
    }

    public void setClubDraws(int clubDraws) {
        this.clubDraws = clubDraws;
    }

    public void setClubDefeats(int clubDefeats) {
        this.clubDefeats = clubDefeats;
    }

    public void setSeasonGoalsScored(int seasonGoalsScored) {
        this.seasonGoalsScored = seasonGoalsScored;
    }


    public void setSeasonGoalsReceived(int seasonGoalsReceived) {
        this.seasonGoalsReceived = seasonGoalsReceived;
    }



    public void setNumOfMatches(int numOfMatches) {
        this.numOfMatches = numOfMatches;
    }


    public void setMaxAgePlayer(int maxAgePlayer){
        this.maxAgePlayer=maxAgePlayer;
    }

    public void setClubPoints(int clubPoints) {
        this.clubPoints = clubPoints;
    }

    public int getClubWins() {
        return clubWins;
    }

    public int getClubDraws() {
        return clubDraws;
    }

    public int getClubDefeats() {
        return clubDefeats;
    }

    public int getSeasonGoalsScored() {
        return seasonGoalsScored;
    }

    public int getSeasonGoalsReceived() {
        return seasonGoalsReceived;
    }

    public int getNumOfMatches() {
         return numOfMatches=clubDefeats+clubDraws+clubWins;
    }

    public int getClubPoints() {
        return clubPoints;
    }



    public int getMaxAgePlayer(){
        return maxAgePlayer;
    }

    @Override
    public String toString() {
        return
               super.toString()+
                "clubWins=" + clubWins +
                ", clubDraws=" + clubDraws +
                ", clubDefeats=" + clubDefeats +
                ", seasonGoalsScored=" + seasonGoalsScored +
                ", seasonGoalsReceived=" + seasonGoalsReceived +
                ", numOfMatches=" + numOfMatches +
                ", clubPoints=" + clubPoints +
                ", maxAgePlayer="+maxAgePlayer+
                '}'+"\n";
    }


    public String stats(){

         return  "===================================================\n" +
                 "  Club Name=" + getClubName() +
                "\n ClubWins=" + clubWins +
                "\n ClubDraws=" + clubDraws +
                "\n ClubDefeats=" + clubDefeats +
                "\n NumOfMatches=" + numOfMatches+
                "====================================================\n";
    }
}
