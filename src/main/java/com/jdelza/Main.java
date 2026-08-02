package com.jdelza;


import com.jdelza.controller.GameController;
import com.jdelza.controller.StartController;
import com.jdelza.model.GameModel;
import com.jdelza.view.MainView;
import javafx.application.Application;
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
        MainScreen ms = new MainScreen();

        GameModel model = new GameModel();
        GameView view = new GameView(model);
        GameScreen gs = view.getGamescreen();
        GameController gc = new GameController(model, view);

         ms.getChildren().add(new StartScreen());
        //StackPane non forza il ridimensionamento del figlio
        StackPane root = new StackPane(ms);


        // Imposta l'allineamento in alto così lo spazio vuoto rimane in basso
        StackPane.setAlignment(ms, Pos.CENTER);
        //ms.getChildren().add(overworld);

         */
        GameModel gameModel = new GameModel();
        MainView main = new MainView(gameModel);

        StartController startController = new StartController(main.getStartView());
        GameController  gameController  = new GameController(gameModel, main.getGameView());


        /*
        GameView gameView = new GameView(gameModel);

        StartController startController = new StartController();
        GameController gameController = new GameController(gameModel, gameView);

        main.getMainScreen().getChildren().add(startController.getStartView().getStartScreen());

         */

        StackPane root = main.getMainScreen();

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
