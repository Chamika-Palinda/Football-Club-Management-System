package controllers;

import entities.*;
import services.*;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleApplication {



    public static final LeagueManager manager = new PremierLeagueManager();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Menu();
    }



//menu of the console application
    public static void Menu() throws IOException, ClassNotFoundException {
        ChoiceLoop:
        while(true)
        {
            System.out.println("Premier League Menu :\n");
            int choice = intValidations("<<<<<<<<<<<<<<<Enter your choice \n"+
                            "               1.Add Football Club \n" +
                            "               2.Delete Football Club \n" +
                            "               3.Display the  statistics of  Club  \n" +
                            "               4.Display the  Premier league table \n" +
                            "               5.Add played matched to the league  \n"+
                            "               6.Load data from files   \n"+
                            "               7.Update Existed Club Details   \n"+
                            "               8.Save Club Data       \n"+
                            "               9.Java Fx GUI Application\n"+
                            "               10.See All Unqualified Clubs\n"+
                            "               11.Angular And Play GUI \n"+
                            "               12.Exit>>>>>>>>>>>>>>>>>>>>>>>>> \n"

                    ,0,12);

            switch (choice)
            {
                case 1:
                    addFootballClub();
                    manager.saveQualifiedClubs();
                    manager.saveUnQualifiedClubs();
                    break;
                case 2:
                    String clubNames = StringValidations("Enter the club name");
                    manager.deleteFootballClub(clubNames);
                    manager.saveQualifiedClubs();
                    manager.saveQualifiedClubs();
                    break;
                case 3:
                    String choices = StringValidations("Enter the club name");
                    manager.displayStatisticsFootballClub(choices);
                    break;
                case 4:
                    manager.displayPremierLeagueTable();
                    continue;
                case 5:
                    manager.addPlayedMatch();
                    manager.saveQualifiedClubs();
                    manager.saveMatchesToFile();
                    break;
                case 6:
                    manager.loadQualifiedClubsSavedData();
                    manager.loadUnQualifiedClubsSavedData();
                    manager.loadSaveFileOfMatches();
                    System.out.println("File is loading ...............\n" +
                                "File loaded successfully \n");
                    break;
                case 7:
                    String clubName = StringValidations("Enter the Club Name You want to Update");
                    manager.updateExistedClub(clubName);
                    continue;
                case 8:
                    manager.saveQualifiedClubs();
                    manager.saveUnQualifiedClubs();
                    manager.saveMatchesToFile();
                    System.out.println("File saved successfully ........................");
                    break;
                case 9:
                    manager.loadQualifiedClubsSavedData();
                    manager.loadSaveFileOfMatches();
                    GUIInterface.myLaunch(GUIInterface.class);
                    continue ;
                case 10:
                    manager.loadUnQualifiedClubsSavedData();
                    manager.displayUnQualifiedClubs();
                    continue;
                case 11:
                    try {
                        String command = "sbt run";
                        Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + command);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    continue;
                case 12:
                    System.out.println("Thank you for using the system \n");
                    break ChoiceLoop;
                default:
                    System.out.println("You selected an invalid Option .please try again \n");
            }
        }
    }



//Method to pass Football clubs to addFootballClub  method
    private static void addFootballClub() throws IOException {

        boolean boolcheck =true;
        while(boolcheck =true) {


            Integer UClubType =intValidations(
                         "      Enter 1. If  Your Club is School Club  \n" +
                             "      Enter 2. If Your Club is University Club  \n" +
                             "      Enter 3. If Your Club is International Club  \n"
                                                ,1,3);

            if(UClubType ==1){
                String USchoolName = StringValidations("Enter the School Name");
                String UClubName = StringValidations("Enter the Club Name");
                String UManagerName = StringValidations("Enter the  Club Manager's name");
                Integer USince = intValidations("Club is plays Since ", 1800, 2020);
                String UClubLocation = StringValidations("Enter the Location Home of the club");
                Integer UClubWins = intValidations("Enter Wins of the Club", 0, 9999);
                Integer UClubDraws = intValidations("Enter Draws of the Club", 0, 9999);
                Integer UClubDefeats = intValidations("Enter Defeats of the Club", 0, 9999);
                Integer UMaxAgePlayer = intValidations("Enter the Minimum age of players in your club", 22, 99);


                    boolcheck =true;
                    SchoolFootballClub club1 = new SchoolFootballClub(UClubName,UManagerName,USince,UClubLocation,UClubWins,UClubDraws,UClubDefeats,UMaxAgePlayer,USchoolName);
                    manager.addUnQualifiedSclFootballClub(club1);
                    System.out.println(club1.getClubName()+" ,This Clubs is Unqualified club please check the Rules and Regulations of Premier league \n");

                return;


            }else if(UClubType ==2){
                String UUniName = StringValidations("Enter the University  Name");
                String UClubName = StringValidations("Enter the Club Name");
                String UManagerName = StringValidations("Enter the  Club Manager's name");
                Integer USince = intValidations("Club is plays Since ", 1800, 2020);
                String UClubLocation = StringValidations("Enter the Location Home of the club");
                Integer UClubWins = intValidations("Enter Wins of the Club", 0, 9999);
                Integer UClubDraws = intValidations("Enter Draws of the Club", 0, 9999);
                Integer UClubDefeats = intValidations("Enter Defeats of the Club", 0, 9999);
                Integer UMaxAgePlayer = intValidations("Enter the Minimum age of players in your club", 22, 99);


                    boolcheck =true;
                    UniversityFootballClub club1 = new UniversityFootballClub(UClubName,UManagerName,USince,UClubLocation,UClubWins,UClubDraws,UClubDefeats,UMaxAgePlayer,UUniName);
                    manager.addUnQualifiedUniFootballClub(club1);
                    System.out.println(club1.getClubName()+" ,This Clubs is Unqualified club please check the Rules and Regulations of Premier league \n");
                    return;


            }else if(UClubType ==3){
                String UClubName = StringValidations("Enter the Club Name");
                String UManagerName = StringValidations("Enter the  Club Manager's name");
                Integer USince = intValidations("Club is plays Since ", 1800, 2020);
                String UClubLocation = StringValidations("Enter the Location Home of the club");
                Integer UClubWins = intValidations("Enter Wins of the Club", 0, 9999);
                Integer UClubDraws = intValidations("Enter Draws of the Club", 0, 9999);
                Integer UClubDefeats = intValidations("Enter Defeats of the Club", 0, 9999);
                Integer UMaxAgePlayer = intValidations("Enter the Minimum age of players in your club", 22, 99);

                    boolcheck =true;
                    FootballClub club1 = new FootballClub(UClubName, UManagerName, USince, UClubLocation, UClubWins, UClubDraws, UClubDefeats,  UMaxAgePlayer);
                    manager.addFootballClub(club1);
                    System.out.println(club1.getClubName()+" is  successfully added to the premier league \n");
                    return;
            }
        }
    }



    //validation method to String validation
    public static String StringValidations(String msg){

        Scanner scans = new Scanner(System.in);
        boolean boolchecks;
        while (boolchecks = true) {
            System.out.println(msg);
            String str = scans.next();

            try {
                if (!(str == null || str.equals(""))) {
                    boolchecks = true;
                }
                if (str.matches("[a-zA-Z]*")) {
                    boolchecks = true;
                    return str;
                }
            } catch (Exception e) {
                System.out.println("Please Enter valid string \n");
                boolchecks = false;
            }
        }
        return null;
    }

    //Integer validation methods
    public static Integer intValidations(String str, int val1, int val2){
        Scanner scans = new Scanner(System.in);

        boolean boolcheck;

        while (boolcheck = true) {
            System.out.println(str);
            String number = scans.next();

            try {
                Integer num =  Integer.parseInt(number);
                if (num >= val1 && num <= val2) {
                    boolcheck=true;
                    return num;
                }else {
                    System.out.println("Please Enter number between "+val1 +" and "+val2);
                    boolcheck = false;
                }
            } catch (Exception e ) {
                System.out.println("Please Enter valid number \n");
                boolcheck = false;
            }
        }
        return null;
    }

    }

