package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import entities.FootballClub;
import entities.Match;
import play.libs.Json;
import play.mvc.*;
import services.*;
import utils.*;

import java.io.IOException;
import java.security.Guard;
import java.util.ArrayList;


public class HomeController extends Controller {

    public final PremierLeagueManager manager = new PremierLeagueManager();
    public Result sendClubData(String SortBy) throws IOException {
        manager.loadQualifiedClubsSavedData();
        ArrayList<FootballClub> arraylist= new ArrayList<>();
        try{
             arraylist = manager.footballClubArraylist;
             if(SortBy.equals("sortByWins")){
                 System.out.println("sorted by wins");
                 arraylist.sort(new ClubWinsComparator());
             }
             else if(SortBy.equals("sortByGoals")){
                 System.out.println("sorted by goals");
                 arraylist.sort(new ClubGoalsComparator());
             }else{
                 System.out.println("sorted by points");
                 arraylist.sort(new ClubPtsComparator());
             }
        }catch (Exception e){
            System.out.println("File not found");
        }
        Gson dataClubGson = new Gson();
        String dataGsonString= dataClubGson.toJson(arraylist);
        System.out.println("sucessful"+dataGsonString);
        return ok(dataGsonString);
    }


    public Result sendMatchData() throws IOException{
        manager.loadSaveFileOfMatches();
        ArrayList<Match> arraylist2 = new ArrayList<>();
        try{
            arraylist2= manager.getMatches();

        }catch (Exception e){
            System.out.println("File not found");
        }
        Gson dataMatchGson = new Gson();
        String dataGsonToString = dataMatchGson.toJson(arraylist2);
        System.out.println("successful"+dataGsonToString);
        return ok(dataGsonToString);

    }

    public Result sendMatchRanData() throws IOException{
        manager.loadSaveFileOfMatches();
        ArrayList<Match> arraylist2 = new ArrayList<>();
        try{
            manager.RandomMatch();
            manager.saveMatchesToFile();
            manager.saveQualifiedClubs();
            arraylist2= manager.getMatches();

        }catch (Exception e){
            System.out.println("File not found");
        }
        Gson dataMatchGson = new Gson();
        String dataGsonToString = dataMatchGson.toJson(arraylist2);
        System.out.println("successful"+dataGsonToString);
        return ok(dataGsonToString);

    }


    public Result sendMatchSearchedData(String mergedDate) throws IOException{

        System.out.println(mergedDate);
        String daay= mergedDate.substring(0,2);
        String moonth= mergedDate.substring(2,4);
        String yeear= mergedDate.substring(4);

        int day = Integer.parseInt(daay);
        int month = Integer.parseInt(moonth);
        int year = Integer.parseInt(yeear);

        ArrayList<Match> arraylist2 = new ArrayList<>();
        try{
            arraylist2=GUIInterface.matchFinder(day,month,year);
        }catch (Exception e){
            System.out.println("File not found");
        }
        Gson dataMatchGson = new Gson();
        String dataGsonToString = dataMatchGson.toJson(arraylist2);
        System.out.println("successful"+dataGsonToString);
        return ok(dataGsonToString);

    }




}
