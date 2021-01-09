package utils;

import  entities.*;
//Custom comparator class to compare the club points and sort for the descending order
import java.util.Comparator;

public class ClubGoalsComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub club1,FootballClub club2) {


        if (club1.getSeasonGoalsScored() > club2.getSeasonGoalsScored()) {
            return -1;
        } else {
            if (club1.getSeasonGoalsScored() < club2.getSeasonGoalsScored()) {
                return 1;
            }
        }
        return 0;

    }
}
