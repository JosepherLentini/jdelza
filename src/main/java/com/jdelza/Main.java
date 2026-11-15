package com.jdelza;


import com.jdelza.controller.GameController;
import com.jdelza.controller.StartController;
import com.jdelza.controller.UserController;
import com.jdelza.model.GameModel;
import com.jdelza.model.StartModel;
import com.jdelza.model.UserModel;
import com.jdelza.model.characters.Player;
import com.jdelza.model.game_data.GameDataStorage;
import com.jdelza.utils.enums.Directions;
import com.jdelza.view.MainView;
import com.jdelza.view.UserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application{


    public static void main(String[] args) {
        // Avvia l'applicazione JavaFX
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        //Game data
        GameDataStorage gameDataStorage = new GameDataStorage();
        Player player = Player.getPlayerInstance();


        GameModel gameModel = new GameModel(player, gameDataStorage);
        UserModel userModel = new UserModel(gameDataStorage);


        MainView main = new MainView(gameModel);
        //UserView userView = new UserView();

        GameController  gameController  = new GameController(gameModel, main.getGameView());

        //Initializing userController
        UserController userController = new UserController(userModel, main.getUserView());


        StartModel startModel  = new StartModel(main, gameController);
        StartController startController = new StartController(main.getStartView(), gameController, startModel);

        //Observer initialization
        userModel.addObserver(main.getUserView());
        userModel.addObserver(main);

        main.addObserver(gameController);


        StackPane root = main.getMainScreen();
        root.setStyle( "-fx-background-color: black;");


        // Prende l'altezza visibile dello schermo principale (escludendo la barra delle applicazioni)
        double bounds = Screen.getPrimary().getVisualBounds().getHeight();



        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Jdelza");
        stage.setMaximized(true);
        //stage.setResizable(false);
        stage.centerOnScreen();


        stage.show();

        //System.out.println(gameModel.getOverworldMap().getZone(new Coordinates(7,7)).toString());



    }



}





/*

public class Main extends Application {




    public static void main(String[] args) {

        Player zeldo = Player.getPlayerInstance();
        zeldo.setPlayerMapPosition(new Coordinates(7,7));
        zeldo.setPosition(new Coordinates(4,4));

        WorldMap map = new WorldMap(zeldo);

        Zone currentZone = map.getMap()[zeldo.getPlayerMapPosition().getY()][zeldo.getPlayerMapPosition().getX()];
        Zone x = Arrays.stream(map.getMap()).flatMap(Arrays::stream).filter(z->z.getPlayer() != null).findFirst().get();

        System.out.println(map.toString());
        System.out.println(x);

    }
}

*/
