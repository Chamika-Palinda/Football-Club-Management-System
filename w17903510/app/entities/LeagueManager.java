package entities;

import java.io.IOException;


public interface LeagueManager {



        void addFootballClub(FootballClub club) throws IOException;
        void deleteFootballClub(String clubName);
        void displayStatisticsFootballClub(String clubName);
        void displayPremierLeagueTable();
        void loadQualifiedClubsSavedData() throws IOException, ClassNotFoundException;
        void loadUnQualifiedClubsSavedData() throws IOException, ClassNotFoundException;
        void saveUnQualifiedClubs() throws IOException;
        void saveQualifiedClubs() throws IOException;
        void addPlayedMatch() throws IOException;
        void updateExistedClub(String strss);
        void saveMatchesToFile() throws IOException;
        void loadSaveFileOfMatches() throws IOException;
        void addUnQualifiedSclFootballClub(SchoolFootballClub club1);
        void addUnQualifiedUniFootballClub(UniversityFootballClub club);
        void displayUnQualifiedClubs();
}
