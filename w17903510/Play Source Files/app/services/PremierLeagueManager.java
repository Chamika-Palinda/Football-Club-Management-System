package services;

import utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.*;
import java.io.*;
import java.util.*;
import controllers.*;

public class PremierLeagueManager implements LeagueManager {

    public static final ArrayList<FootballClub> footballClubArraylist = new ArrayList<>();
    public static final ObservableList<FootballClub> footballClubArray = FXCollections.observableList(footballClubArraylist);
    public static final ArrayList<FootballClub> UnQualifiedFootballClubArray = new ArrayList<>();
    public static final ArrayList<Match> matches = new ArrayList<>();
    public Scanner scans = new Scanner(System.in);
    public boolean checks = false;


    //Method to add football club to premier league
    @Override
    public void addFootballClub(FootballClub club) {
        if (footballClubArray.contains(club)) {
            System.out.println("This club is already in the League");
        } else {
            footballClubArray.add(club);
        }

    }


    //Method to add school football club to UnQualified Arraylist;
    @Override
    public void addUnQualifiedSclFootballClub(SchoolFootballClub club) {
        if (UnQualifiedFootballClubArray.contains(club)) {
            System.out.println("This club is already in the League");
        } else {
            UnQualifiedFootballClubArray.add(club);
        }

    }

    //Method to add Uni football club to UnQualified Arraylist
    @Override
    public void addUnQualifiedUniFootballClub(UniversityFootballClub club) {
        if (UnQualifiedFootballClubArray.contains(club)) {
            System.out.println("This club is already in the League");
        } else {
            UnQualifiedFootballClubArray.add(club);
        }

    }



    //Method to delete football club
    @Override
    public void deleteFootballClub(String clubName) {


        for (FootballClub club : footballClubArray) {
            if (club.getClubName().equals(clubName)) {
                System.out.println("Football club which name was " + clubName + " is removed from the league");
                footballClubArray.remove(club);
                break;
            } else {
                System.out.println("There is a no such club in League, Please Recheck!!!");
                continue;
            }
        }

    }

    //Method to display certain statistics of chose football club
    @Override
    public void displayStatisticsFootballClub(String clubName) {

        for (FootballClub club : footballClubArray) {
            if (club.getClubName().equals(clubName)) {
                System.out.println(club.stats());
                return;
            }
        }
        System.out.println("Please recheck the club name");
    }

    @Override
    public void displayPremierLeagueTable() {

        //calling the collections sorting and used Custom comparator
        Collections.sort(footballClubArray, new ClubPtsComparator());

        String leftAlignFormat = "|   %-13s|   %-7d|   %-7d|   %-10d|   %-8d|   %-8d|%n";
        System.out.format("+---------------- PREMIER LEAGUE TABLE --+----------+------------+-----------+%n");
        System.out.format("+----------------+----------+----------+-------------+-----------+-----------+%n");
        System.out.format("|   Club Name    |   plays  |   wins   |    draws    |  defeats  |club points|%n");
        System.out.format("+----------------+----------+----------+-------------+-----------+-----------+%n");


        for (FootballClub items : footballClubArray) {
            System.out.format(leftAlignFormat, items.getClubName(), items.getNumOfMatches(), items.getClubWins(), items.getClubDraws(), items.getClubDefeats(), items.getClubPoints());
        }
        System.out.format("+----------------+----------+----------+-------------+-----------+-----------+%n");

    }

    @Override
    public void displayUnQualifiedClubs() {

        //calling the collections sorting and used Custom comparator
        String leftAlignFormat = "|   %-25s|  Unqualified             |%n";
        System.out.format("+----------------Unqualified Clubs(School Clubs & University Clubs)-----------+%n");
        System.out.format("+----------------+----------++-------------+-----------++%n");
        System.out.format("|   Club Name                |          Club Status     |%n");
        System.out.format("+----------------+----------++-------------+-----------++%n");


        for (FootballClub items : UnQualifiedFootballClubArray) {
            System.out.format(leftAlignFormat,items.getClubName());
        }
        System.out.format("+----------------+----------++-------------+-----------++%n");

    }


    //Method for creating random matches
    public void RandomMatch(){

        int result1 = randInt(0, footballClubArray.size());
        int result2 =randInt(0, footballClubArray.size());

        if(result1!=result2){

            int result3 =randInt(1,31);
            int result4 =randInt(1,12);
            int result5 =randInt(2019,2021);
            int result6 =randInt(0,10);
            int result7 =randInt(0,10);

            FootballClub home = footballClubArray.get(result1);
            FootballClub away = footballClubArray.get(result2);

            int homeGoals = -1;
            try {
                homeGoals = result6;
            } catch (Exception e) {
                System.out.println("");
            }

            if(homeGoals ==-1){
                System.out.println("You have to  enter valid number");
                return;
            }

            int awayGoals = -1;
            try {
                awayGoals = result7;
            } catch (Exception e) {
                System.out.println("");
            }

            if(awayGoals ==-1){
                System.out.println("You have to enter valid number");
                return;
            }

            DateTime date = new  DateTime(result3,result4,result5);
            Match match = new Match();
            match.setDate(date);
            match.setTeam1(home);
            match.setTeam2(away);
            match.setTeam1Score(homeGoals);
            match.setTeam2Score(awayGoals);
            match.setTeam1Name(home.getClubName());
            match.setTeam2Name(away.getClubName());
            matches.add(match);
            home.setNumOfMatches(home.getNumOfMatches()+1);
            away.setNumOfMatches(away.getNumOfMatches()+1);
            home.setSeasonGoalsScored(home.getSeasonGoalsScored() + homeGoals);
            away.setSeasonGoalsScored(away.getSeasonGoalsScored() + awayGoals);
            home.setSeasonGoalsReceived(home.getSeasonGoalsReceived() + awayGoals);
            away.setSeasonGoalsReceived(away.getSeasonGoalsReceived() + homeGoals);


            if (homeGoals > awayGoals) {
                home.setClubPoints(home.getClubPoints() + 3);
                home.setClubWins(home.getClubWins() + 1);
                away.setClubDefeats(away.getClubDefeats() + 1);
            } else if (homeGoals < awayGoals) {
                away.setClubPoints(away.getClubPoints() + 3);
                away.setClubWins(away.getClubWins() + 1);
                home.setClubDefeats(home.getClubDefeats() + 1);
            } else {
                home.setClubPoints(home.getClubPoints() + 1);
                away.setClubPoints(away.getClubPoints() + 1);
                home.setClubDraws(home.getClubDraws() + 1);
                away.setClubDraws(away.getClubDraws() + 1);
            }


        }else{

        }




    }
    // Method to adding a played match
    @Override
    public void addPlayedMatch() {
        System.out.println("Enter Date(format dd-mm-yyyy): ");

        int day = ConsoleApplication.intValidations("Enter the day of the match:", 0, 31);
        int month = ConsoleApplication.intValidations("Enter the month of the match:", 0, 12);
        int year = ConsoleApplication.intValidations("Enter the year of the match:", 2000, 2022);

        DateTime date = new DateTime(day, month, year);

        System.out.println("Enter Home Team");
        String line = scans.next();
        FootballClub home = null;

        for (FootballClub club : footballClubArray) {
            if (club.getClubName().equals(line))
                home = club;
        }
        if (home == null) {
            System.out.println("NO such club in league");
            return;
        }

        System.out.println("Enter Away Team: ");
        line = scans.next();
        FootballClub away = null;
        for (FootballClub club : footballClubArray) {
            if (club.getClubName().equals(line))
                away = club;
        }

        if (away == null) {
            System.out.println("NO such club in league");
            return;
        }
        System.out.println("Enter Home team Goals: ");
        line = scans.next();
        int homeGoals = -1;
        try {
            homeGoals = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("");
        }

        if(homeGoals ==-1){
            System.out.println("You have enter valid number");
            return;
        }

        System.out.println("Enter Away team Goals: ");
        line = scans.next();
        int awayGoals = -1;
        try {
            awayGoals = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("");
        }

        if(awayGoals ==-1){
            System.out.println("You have enter valid number");
            return;
        }

        Match match = new Match();
        match.setDate(date);
        match.setTeam1(home);
        match.setTeam2(away);
        match.setTeam1Score(homeGoals);
        match.setTeam2Score(awayGoals);
        match.setTeam1Name(home.getClubName());
        match.setTeam2Name(away.getClubName());
        matches.add(match);
        home.setNumOfMatches(home.getNumOfMatches()+1);
        away.setNumOfMatches(away.getNumOfMatches()+1);
        home.setSeasonGoalsScored(home.getSeasonGoalsScored() + homeGoals);
        away.setSeasonGoalsScored(away.getSeasonGoalsScored() + awayGoals);
        home.setSeasonGoalsReceived(home.getSeasonGoalsReceived() + awayGoals);
        away.setSeasonGoalsReceived(away.getSeasonGoalsReceived() + homeGoals);


        if (homeGoals > awayGoals) {
            home.setClubPoints(home.getClubPoints() + 3);
            home.setClubWins(home.getClubWins() + 1);
            away.setClubDefeats(away.getClubDefeats() + 1);
        } else if (homeGoals < awayGoals) {
            away.setClubPoints(away.getClubPoints() + 3);
            away.setClubWins(away.getClubWins() + 1);
            home.setClubDefeats(home.getClubDefeats() + 1);
        } else {
            home.setClubPoints(home.getClubPoints() + 1);
            away.setClubPoints(away.getClubPoints() + 1);
            home.setClubDraws(home.getClubDraws() + 1);
            away.setClubDraws(away.getClubDraws() + 1);
        }
    }

    //Method to Update existing football club details
    @Override
    public void updateExistedClub(String clubName) {
        for (FootballClub clubs:footballClubArray) {
            if(clubs.getClubName().equals(clubName)){


                String UManagerName = ConsoleApplication.StringValidations("Enter the  Club Manager's name");
                Integer USince = ConsoleApplication.intValidations("Club is plays Since ", 1900, 2020);
                String UClubLocation = ConsoleApplication.StringValidations("Enter the Location Home of the club");
                Integer UClubWins = ConsoleApplication.intValidations("Enter Wins of the Club", 10, 9999);
                Integer UClubDraws = ConsoleApplication.intValidations("Enter Draws of the Club", 0, 9999);
                Integer UClubDefeats = ConsoleApplication.intValidations("Enter Defeats of the Club", 0, 9999);

                clubs.setManagerName(UManagerName);
                clubs.setSince(USince);
                clubs.setClubLocation(UClubLocation);
                clubs.setClubWins(UClubWins);
                clubs.setClubDraws(UClubDraws);
                clubs.setClubDefeats(UClubDefeats);
                clubs.setNumOfMatches(UClubDefeats+UClubDraws+UClubWins);
                clubs.setClubPoints(UClubWins*3+UClubDraws*1);

            }else{
                System.out.println("There is no such club in this league");
            }

        }

    }

    //Method to load saved file which is saved using serializable
    @Override
    public void loadQualifiedClubsSavedData( ) throws IOException {

        footballClubArray.clear();
        FileInputStream fin = new FileInputStream("E:/Workspaces/Workstations/Play Framework Workstation/New folder (4)/Angula-Play Boilerplate/w17903510/app/ClubData.txt");
        ObjectInputStream objin = new ObjectInputStream(fin);

        while(true){
            try{
                FootballClub fbc = (FootballClub) objin.readObject();
                footballClubArray.add(fbc);

            }catch(IOException | ClassNotFoundException e){
                break;
            }
        }

         if(footballClubArray.size()>0){
              checks =true;
         }

        System.out.println("Your Saved Data is Loaded Successfully ");
        System.out.println("===================================================================== \n");

    }

    @Override
    public void loadUnQualifiedClubsSavedData() throws IOException, ClassNotFoundException {
        UnQualifiedFootballClubArray.clear();
        FileInputStream fin = new FileInputStream("E:/Workspaces/Workstations/Play Framework Workstation/New folder (4)/Angula-Play Boilerplate/w17903510/app/UnQualifiedClubData.txt");
        ObjectInputStream objin = new ObjectInputStream(fin);

        while(true){
            try{

                FootballClub fbc = (FootballClub) objin.readObject();
                UnQualifiedFootballClubArray.add(fbc);

            }catch(IOException | ClassNotFoundException e){
                break;
            }
        }

        if(UnQualifiedFootballClubArray.size()>0){
            checks =true;
        }

        System.out.println("Your Saved Data is Loaded Successfully ");
        System.out.println("===================================================================== \n");
    }


    @Override
    public void saveUnQualifiedClubs() throws IOException {
        File file = new File("E:/Workspaces/Workstations/Play Framework Workstation/New folder (4)/Angula-Play Boilerplate/w17903510/app/UnQualifiedClubData.txt");
        FileOutputStream fOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);

        Iterator itr = UnQualifiedFootballClubArray.iterator();

        while(itr.hasNext()){
            FootballClub fbc=(FootballClub) itr.next();
            objOut.writeObject(fbc);
        }

        objOut.close();
        fOut.close();

    }

    //Method to save the club Data
    @Override
    public void saveQualifiedClubs() throws IOException
    {

        File file = new File("ClubData.txt");
        FileOutputStream fOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);

        Iterator itr = footballClubArray.iterator();

        while(itr.hasNext()){
            FootballClub fbc=(FootballClub) itr.next();
            objOut.writeObject(fbc);
        }

        objOut.close();
        fOut.close();

    }

    @Override
    public void saveMatchesToFile() throws IOException
    {

        File file = new File("E:/Workspaces/Workstations/Play Framework Workstation/New folder (4)/Angula-Play Boilerplate/w17903510/app/MatchData.txt");
        FileOutputStream fOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);

        Iterator itr = matches.iterator();

        while(itr.hasNext()){
            Match mtch=(Match) itr.next();
            objOut.writeObject(mtch);
        }

        objOut.close();
        fOut.close();

    }

    @Override
    public void loadSaveFileOfMatches() throws IOException {

        matches.clear();
        FileInputStream fin = new FileInputStream("E:/Workspaces/Workstations/Play Framework Workstation/New folder (4)/Angula-Play Boilerplate/w17903510/app/MatchData.txt");
        ObjectInputStream objin = new ObjectInputStream(fin);

        while(true){
            try{

                Match mtch = (Match) objin.readObject();
                matches.add(mtch);

            }catch(IOException | ClassNotFoundException e){
                break;
            }
        }

    }

    //method to randon int generator
    public int randInt(int min,int max){
        Random r = new Random();
        int result = r.nextInt(max-min) + min;
        return result;
    }

// Method to return array list of matches
    public ArrayList<Match> getMatches(){ return matches;}


}
