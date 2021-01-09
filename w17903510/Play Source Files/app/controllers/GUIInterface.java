package controllers;

import services.*;
import entities.*;
import utils.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class GUIInterface extends Application {

//Initialize JavaFx Scene Controls
    public AnchorPane MainAnchPane;
    public AnchorPane SecondAnchPane;
    public AnchorPane btnAnch;
    public Pane paneBack;
    public Pane paneShow;
    public Pane panePlayedMatches;
    public ImageView backImgView;
    public ImageView paneShowImgView;
    public ImageView panePlayedImgView;
    public GridPane gridPaneButtons;
    public Button btnShowTblPane;
    public ImageView imageViewBtnToShowPage;
    public GridPane gridShowTblPane;
    public Label lblShowTblPane;
    public TableView premierLeagueTable;
    public TableColumn<FootballClub, String> ClubName;
    public TableColumn<FootballClub, Integer> MP;
    public TableColumn<FootballClub, Integer> W;
    public TableColumn<FootballClub, Integer> D;
    public TableColumn<FootballClub, Integer> L;
    public TableColumn<FootballClub, Integer> GF;
    public TableColumn<FootballClub, Integer> GA;
    public TableColumn<FootballClub, Integer> Pts;
    public MenuBar fileHelpMenuBar;


    public static final PremierLeagueManager newManager = new PremierLeagueManager();

    //method to re run the javafx application
    private static volatile boolean javaFxLaunched = false;
    public static void myLaunch(Class<? extends Application> applicationClass) {
        if (!javaFxLaunched) { // First time
            Platform.setImplicitExit(false);
            new Thread(()-> Application.launch(applicationClass)).start();
            javaFxLaunched = true;
        } else { // Next times
            Platform.runLater(()->{
                try {
                    Application application = applicationClass.newInstance();
                    Stage primaryStage = new Stage();
                    application.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }



    //Method to create Panes
    public Pane paneCreator(String strName, int height, int width){
        Pane pane= new Pane();
        pane.setId(strName);
        pane.setPrefHeight(height);
        pane.setPrefWidth(width);
        return pane;
    }
    //Method to create Buttons
    public Button btnCreator(String strName, int height, int width){
        Button btn = new Button();
        btn.setId(strName);
        btn.setPrefHeight(height);
        btn.setPrefWidth(width);
        return btn;
    }

    //Method to create imageView
    public ImageView imageViewCreator(String path, int fitHeight, int fitWidth, boolean pickOnBounds, boolean preserveRatio ){
        Image img = new Image(path);
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(fitWidth);
        imgView.setFitHeight(fitHeight);
        imgView.setPreserveRatio(preserveRatio);
        imgView.setPickOnBounds(pickOnBounds);

        return imgView;
    }

    //method to add graphics to buttons
    public ImageView btnGraphicCreator(String imgpath, int  height, int width, boolean pickOnBounds, boolean preserveRatio){
        Image image = new Image(imgpath);
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(height);
        imgView.setFitWidth(width);
        imgView.setPickOnBounds(true);
        imgView.setPreserveRatio(true);
        return imgView;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//Section-01 Creating and setting up the all  main components of the GUI

        //setting the title and size of the stage
        primaryStage.setTitle("Football Manager 2020");
        primaryStage.setHeight(650);
        primaryStage.setWidth(835);
        primaryStage.setResizable(false);

        //Creating MainAnchorPane
        MainAnchPane = new AnchorPane();
        MainAnchPane.setId("MainAnchorPane");
        MainAnchPane.setPrefHeight(600);
        MainAnchPane.setPrefWidth(800);

        ///creating SecondAnchor pane
        SecondAnchPane = new AnchorPane();
        SecondAnchPane.setId("SecondAnchPane");
        SecondAnchPane.setPrefHeight(619);
        SecondAnchPane.setPrefWidth(829);


        //creating anchor pane to store buttons
        btnAnch = new AnchorPane();
        btnAnch.setId("btnAnch");
        btnAnch.setPrefHeight(619);
        btnAnch.setPrefWidth(300);


        //Creating Pane for each an every button
        paneBack = paneCreator("paneBack",826,626);
        backImgView = imageViewCreator("entities/Files/Images/Background.png",626,826,true,true);
        paneBack.getChildren().add(backImgView);

        //creating pane and its content in  pane show table
        paneShow = paneCreator("paneShow",626,826);
        paneShowImgView = imageViewCreator("entities/Files/Images/pane backgrounds.png",626,834,true,true);

        //creating pane and its content in pane played matches
        panePlayedMatches =paneCreator("panePlayedMatches",626,826);
        panePlayedImgView = imageViewCreator("entities/Files/Images/pane backgrounds.png",626,834,true,true);
        panePlayedMatches.getChildren().add(panePlayedImgView);

        //creating GridPane that holds the buttons
        gridPaneButtons = new GridPane();
        gridPaneButtons.setPrefHeight(546);
        gridPaneButtons.setPrefWidth(211);
        gridPaneButtons.setLayoutX(-9);
        gridPaneButtons.setLayoutY(100);
        gridPaneButtons.setVgap(100);

///////////////////////-end Section-01

//Section-02 Creating pane to show FootballClubs and  their functionalities(paneShow)

        //Create and setting up main button to show  paneShow
        btnShowTblPane = btnCreator("GridPaneButtons",54,194);
        btnShowTblPane.setText("View Table");
        imageViewBtnToShowPage = btnGraphicCreator("entities/Files/Images/Icons/sort-az.png",50,50,true,true);
        btnShowTblPane.setGraphic(imageViewBtnToShowPage);
        btnShowTblPane.setOnAction( e->{
            paneShow.toFront();
            try {
                newManager.saveMatchesToFile();
                newManager.saveQualifiedClubs();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            premierLeagueTable.getItems().clear();
            ObservableList<FootballClub> clubList =newManager.footballClubArray;

            for (FootballClub club : clubList){
                premierLeagueTable.getItems().add(club);
            }
        });

        //creating grid pane to holds the content of show pane
        gridShowTblPane = new GridPane();
        gridShowTblPane.setLayoutX(249);
        gridShowTblPane.setPrefHeight(620);
        gridShowTblPane.setPrefWidth(548);
        gridShowTblPane.setLayoutY(50);
        gridShowTblPane.setVgap(10);

        //creating label for show pane
        lblShowTblPane= new Label("Premier League Table");
        lblShowTblPane.setId("paneLbls");
        lblShowTblPane.setAlignment(Pos.CENTER);
        lblShowTblPane.setPrefHeight(42);
        lblShowTblPane.setPrefWidth(449);

        //creating table for show pane
        premierLeagueTable = new TableView<>();
        premierLeagueTable.setId("premierLeagueTable");
        premierLeagueTable.setPrefHeight(406);
        premierLeagueTable.setPrefWidth(449);

        //creating columns to store certain stats of clubs
        ClubName = new TableColumn<>("Club Name");
        ClubName.setCellValueFactory(new PropertyValueFactory<FootballClub, String>("clubName"));
        ClubName.setPrefWidth(140);

        MP = new TableColumn<>("MP");
        MP.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("numOfMatches"));
        MP.setPrefWidth(40);

        W = new TableColumn<>("W");
        W.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("clubWins"));
        W.setPrefWidth(40);

        D = new TableColumn<>("D");
        D.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("clubDraws"));
        D.setPrefWidth(40);

        L = new TableColumn<>("L");
        L.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("clubDefeats"));
        L.setPrefWidth(50);

        GF = new TableColumn<>("GF");
        GF.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("seasonGoalsReceived"));
        GF.setPrefWidth(45);

        GA = new TableColumn<>("GA");
        GA.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("seasonGoalsScored"));
        GA.setPrefWidth(50);

        Pts = new TableColumn<>("Pts");
        Pts.setCellValueFactory(new PropertyValueFactory<FootballClub, Integer>("clubPoints"));
        Pts.setPrefWidth(44);


        premierLeagueTable.getColumns().addAll(ClubName,MP,W,D,L,GF,GA,Pts);
        /////end of creating table for show pane

        ObservableList<FootballClub> clubList =newManager.footballClubArray;

        for (FootballClub club : clubList){
            premierLeagueTable.getItems().add(club);

        }
//        creating combobox to store sorting
        ComboBox comboSortBy  = new ComboBox();
        comboSortBy.getItems().addAll("by Goals","by Wins","by Points");
        comboSortBy.setPrefHeight(26);
        comboSortBy.setPrefWidth(100);
        comboSortBy.setPromptText("SortBy");
        comboSortBy.setOnAction(e->{
            if(comboSortBy.getValue()=="by Points"){
                premierLeagueTable.getItems().sort(new ClubPtsComparator());
            }else if(comboSortBy.getValue()== "by Wins"){
                premierLeagueTable.getItems().sort(new ClubWinsComparator());
            }else if(comboSortBy.getValue()== "by Goals"){
                premierLeagueTable.getItems().sort(new ClubGoalsComparator());
            }
        });

        GridPane.setConstraints(comboSortBy,1,1);
        GridPane.setConstraints(premierLeagueTable,0,2);
        GridPane.setConstraints(lblShowTblPane,0,0);
        gridShowTblPane.getChildren().addAll(premierLeagueTable,lblShowTblPane,comboSortBy);
///////////////////////end-Section-02

//Section-03 Creating Pane All the functionalities of played matches Pane
        //creating gridpane to holds the content of playedmatches pane
        GridPane gridPlyMTblPane = new GridPane();
        gridPlyMTblPane.setLayoutX(249);
        gridPlyMTblPane.setPrefHeight(400);
        gridPlyMTblPane.setPrefWidth(548);
        gridPlyMTblPane.setLayoutY(50);
        gridPlyMTblPane.setVgap(10);

        //creating label for playedMatches pane
        Label lblPlyMPane= new Label("Matches played So Far");
        lblPlyMPane.setId("paneLbls");
        lblPlyMPane.setAlignment(Pos.CENTER);
        lblPlyMPane.setPrefHeight(42);
        lblPlyMPane.setPrefWidth(449);

        //creating table for show pane
        TableView playedMatchesTable = new TableView<>();
        playedMatchesTable.setId("Table");
        playedMatchesTable.setPrefHeight(600);
        playedMatchesTable.setPrefWidth(500);

        TableColumn<Match, String> colmatchDate = new TableColumn<>("Match Date");
        colmatchDate.setCellValueFactory(new PropertyValueFactory<Match, String>("date"));
        colmatchDate.setPrefWidth(125);

        TableColumn<Match, String> colHomeClub = new TableColumn<>("Home Club");
        colHomeClub.setCellValueFactory(new PropertyValueFactory<Match, String>("team1Name"));
        colHomeClub.setPrefWidth(125);

        TableColumn<Match, String> colAwayClub = new TableColumn<>("Away Club");
        colAwayClub.setCellValueFactory(new PropertyValueFactory<Match, String>("team2Name"));
        colAwayClub.setPrefWidth(125);

        TableColumn<Match, Integer> colHomeGoals = new TableColumn<>("HG");
        colHomeGoals.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team1Score"));
        colHomeGoals.setPrefWidth(60);

        TableColumn<Match, Integer> colAwayGoals = new TableColumn<>("AG");
        colAwayGoals.setCellValueFactory(new PropertyValueFactory<Match, Integer>("team2Score"));
        colAwayGoals.setPrefWidth(60);


        playedMatchesTable.getColumns().addAll(colmatchDate,colHomeClub,colAwayClub,colHomeGoals,colAwayGoals);
        TextField txtFldMsg = new TextField("Enter Match Date");
        txtFldMsg.setId("txtFld");
        txtFldMsg.setMaxWidth(150);
        TextField txtFldDay = new TextField();
        txtFldDay.setId("txtFld");
        txtFldDay.setMaxWidth(50);
        TextField txtFldMonth = new TextField();
        txtFldMonth.setId("txtFld");
        txtFldMonth.setMaxWidth(50);

        TextField txtFldYear = new TextField();
        txtFldYear.setId("txtFld");
        txtFldYear.setMaxWidth(50);

        Button btnSearch= btnCreator("GridPaneButtons",50,100);
        btnSearch.setText("Search");
        btnSearch.setOnAction(e->{
            int  day= Integer.parseInt(txtFldDay.getText());
            int  month= Integer.parseInt(txtFldMonth.getText());
            int  year= Integer.parseInt(txtFldYear.getText());

            ArrayList<Match> findedMatches= matchFinder(day,month,year);
            if(findedMatches.isEmpty()){
                txtFldMsg.setText("No Match Found!!");
                playedMatchesTable.getItems().clear();
            }else{
                playedMatchesTable.getItems().clear();
                for (Match match : findedMatches){
                    playedMatchesTable.getItems().add(match);
                }
            }
        });
        GridPane grdForMthSve= new GridPane();
        GridPane.setConstraints(txtFldMsg,0,0);
        GridPane.setConstraints(txtFldDay,1,0);
        GridPane.setConstraints(txtFldMonth,2,0);
        GridPane.setConstraints(txtFldYear,3,0);
        GridPane.setConstraints(btnSearch,4,0);
        grdForMthSve.setVgap(100);
        grdForMthSve.getChildren().addAll(txtFldDay,txtFldMonth,txtFldYear,txtFldMsg,btnSearch);



        GridPane.setConstraints(playedMatchesTable,0,2);
        GridPane.setConstraints(grdForMthSve,0,1);
        GridPane.setConstraints(lblShowTblPane,0,0);
        gridPlyMTblPane.setHgap(45);
        gridPlyMTblPane.getChildren().addAll(playedMatchesTable,lblPlyMPane,grdForMthSve);

        Button btnPlayedMatchesPane = btnCreator("GridPaneButtons",54,194);
        btnPlayedMatchesPane.setText("Played Matches");
        ImageView imageViewBtnToPlayedPane = btnGraphicCreator("entities/Files/Images/Icons/match.png",50,50,true,true);
        btnPlayedMatchesPane.setGraphic(imageViewBtnToPlayedPane);
        btnPlayedMatchesPane.setGraphicTextGap(10);
        btnPlayedMatchesPane.setOnAction(e->{
            panePlayedMatches.toFront();
            panePlayedMatches.toFront();
            playedMatchesTable.getItems().clear();

            try {
                newManager.saveMatchesToFile();
                newManager.saveQualifiedClubs();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ArrayList<Match> matchesList =newManager.getMatches();

            for (Match match : matchesList){
                playedMatchesTable.getItems().add(match);
            }
        });
///////////////end-Section-03

//Section-04 Creating all the Components and functions in Matchmaking
        Button btnMatchmaking = btnCreator("GridPaneButtons",54,194);
        btnMatchmaking.setText("Matchmaking");
        ImageView imageViewBtnToMatchmakePane = btnGraphicCreator("entities/Files/Images/Icons/kick-off.png",50,50,true,true);
        btnMatchmaking.setGraphic(imageViewBtnToMatchmakePane);
        btnMatchmaking.setGraphicTextGap(10);
        btnMatchmaking.setOnAction(e->{
            panePlayedMatches.toFront();
            playedMatchesTable.getItems().clear();
            newManager.RandomMatch();
            try {
                newManager.saveMatchesToFile();
                newManager.saveQualifiedClubs();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ArrayList<Match> matchesList =newManager.getMatches();

            for (Match match : matchesList){
                playedMatchesTable.getItems().add(match);
            }

        });

///////////end-Section 04

        //setting up gridPaneButtons properties
        GridPane.setFillHeight(gridPaneButtons,true);
        GridPane.setConstraints(btnShowTblPane,0,0);
        GridPane.setConstraints(btnMatchmaking,0,1);
        GridPane.setConstraints(btnPlayedMatchesPane,0,2);
        GridPane.setFillWidth(gridPaneButtons,true);


        //Creating MenuBar
        fileHelpMenuBar = new MenuBar();
        fileHelpMenuBar.setId("fileHelpMenuBar");
        fileHelpMenuBar.setPrefHeight(26);
        fileHelpMenuBar.setPrefWidth(826);



        panePlayedMatches.getChildren().add(gridPlyMTblPane);
        paneShow.getChildren().addAll(paneShowImgView,gridShowTblPane);
        gridPaneButtons.getChildren().addAll(btnShowTblPane,btnMatchmaking,btnPlayedMatchesPane);
        btnAnch.getChildren().add(gridPaneButtons);
        SecondAnchPane.getChildren().addAll(paneShow,panePlayedMatches,paneBack);
        MainAnchPane.getChildren().addAll(SecondAnchPane,btnAnch,fileHelpMenuBar);


        Scene scene = new Scene(MainAnchPane);
        scene.getStylesheets().add("entities/Files/Stylesheets/Interface.css");
        primaryStage.setScene(scene);

        primaryStage.show();

    }


//Method to Find match
    //parameters
        //day -user entered number of days
        //month -user entered month
        //year -user entered year
     public static  ArrayList<Match> matchFinder(int day, int month, int year){
            ArrayList<Match> matchFindsArrayList= new ArrayList<>();
         for (Match match:newManager.getMatches()){
             if(match.getDate().getDay() ==day && match.getDate().getMonth() == month && match.getDate().getYear() ==year){
                 matchFindsArrayList.add(match);
             }else{
             }
         }
    return matchFindsArrayList;
     }

    public static void main(String[] args) {

   }
}


