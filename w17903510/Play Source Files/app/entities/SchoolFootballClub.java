package entities;

public class SchoolFootballClub extends FootballClub {

    private String schoolName;


    public SchoolFootballClub(String clubName, String managerName, int since, String clubLocation, int clubWins, int clubDraws, int clubDefeats, int maxAgePlayer , String schoolName) {
        super(clubName, managerName, since, clubLocation, clubWins, clubDraws, clubDefeats, maxAgePlayer);
        this.schoolName = schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public String toString() {
        return super.toString()+
                "schoolName='" + schoolName + '\'';
    }
}