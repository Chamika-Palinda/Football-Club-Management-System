package utils;
//Custom comparator class to compare the club points and sort for the descending order
import java.util.Comparator;
import entities.*;


public class ClubPtsComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub club1,FootballClub club2){


        if(club1.getClubPoints() > club2.getClubPoints()){
            return  -1;
        }else{
            if(club1.getClubPoints() < club2.getClubPoints()){
            return 1;
            }
            else{
                int goalDiff = club1.getSeasonGoalsScored() - club1.getSeasonGoalsReceived();
                int goalDiff1 = club2.getSeasonGoalsScored() - club2.getSeasonGoalsReceived();
                if(goalDiff > goalDiff1)
                {
                    return -1;
                }
                else{
                    if(goalDiff < goalDiff1){
                        return 1;
                    }else{
                        return 0;
                    }

                }
            }
        }

    }




}
