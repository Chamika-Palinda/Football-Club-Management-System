package utils;//Custom comparator class to compare the club points and sort for the descending order
import java.util.Comparator;

import  entities.*;
public class ClubWinsComparator implements Comparator<FootballClub> {

    @Override
    public int compare(FootballClub club1, FootballClub club2) {


        if (club1.getClubWins() > club2.getClubWins()) {
            return -1;
        } else {
            if (club1.getClubWins() < club2.getClubWins()) {
                return 1;
            }
        }

    return 0;
    }




}
