package entities;

import java.io.Serializable;

public class SportsClub implements Serializable {

    private String clubName;
    private String managerName;
    private int  since;
    private String clubLocation;


    public SportsClub(){

    }
    public SportsClub(String clubName, String managerName, int since, String clubLocation) {
        this.clubName = clubName;
        this.managerName = managerName;
        this.since = since;
        this.clubLocation = clubLocation;
    }

    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    public void setManagerName(String managerName){
        this.managerName= managerName;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }


    public String getClubName(){
        return clubName;
    }

    public String getManagerName(){
        return managerName;
    }

    public int getSince(){
        return since;
    }

    public String getClubLocation(){
        return clubLocation;
    }

    @Override
    public String toString() {
        return "{" +
                "clubName='" + clubName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", since=" + since +
                ", clubLocation='" + clubLocation + '\''+",";
    }
}
