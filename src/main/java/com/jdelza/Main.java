package com.jdelza;

import com.jdelza.utils.Dimensions;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.GameScreen;
import com.jdelza.view.screen.MainScreen;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application{


    public static void main(String[] args) {
        // Avvia l'applicazione JavaFX
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        /*

        AnchorPane root = new AnchorPane(ms);
        AnchorPane.
         */

        Overworld overworld = new Overworld();

        /*
        overworld.setLayoutY(-704*7);
        overworld.setLayoutX(-1024*7);
        */

        MainScreen ms = new MainScreen();

        GameScreen gs = new GameScreen(overworld);


        ms.getChildren().add(gs);
        // StackPane non forza il ridimensionamento del figlio
        StackPane root = new StackPane(ms);



        // Imposta l'allineamento in alto così lo spazio vuoto rimane in basso
        StackPane.setAlignment(ms, Pos.CENTER);
        //ms.getChildren().add(overworld);




        // Prende l'altezza visibile dello schermo principale (escludendo la barra delle applicazioni)
        double bounds = Screen.getPrimary().getVisualBounds().getHeight();


        overworld.setTranslateY(-Dimensions.MAP_HEIGHT.getInt()*7);
        overworld.setTranslateX(-Dimensions.MAP_WIDTH.getInt()*7);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Jdelza");
        stage.setMaximized(true);
        //stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();


        System.out.println(bounds);
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
