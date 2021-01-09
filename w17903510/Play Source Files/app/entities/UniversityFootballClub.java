package entities;

public class UniversityFootballClub extends FootballClub {

    private String universityName;


    public UniversityFootballClub(String clubName, String managerName, int since, String clubLocation, int clubWins, int clubDraws, int clubDefeats, int maxAgePlayer, String universityName ) {
        super(clubName, managerName, since, clubLocation, clubWins, clubDraws, clubDefeats,maxAgePlayer);
        this.universityName=universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "universityName='" + universityName + '\'' +
                '}'+'\n';
    }
}
