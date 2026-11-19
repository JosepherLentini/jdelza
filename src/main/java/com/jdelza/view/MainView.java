package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.view.screen.MainScreen;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;

public class MainView extends Observable implements Observer{

    //Wrapper of all section components
    private MainScreen mainScreen;

    //Model
    private GameModel gameModel;

    //Views
    private GameView gameView;
    private StartView startView;
    private UserView userView;


    public MainView(GameModel gameModel) {


        this.mainScreen = new MainScreen();
        mainScreen.setAlignment(Pos.CENTER);

        this.gameView = new GameView(gameModel);
        this.startView = new StartView();
        this.userView = new UserView();

        mainScreen.getChildren().add(startView.getStartScreen());


        gameModel.addObserver(this);

    }

    //get screen methods
    public MainScreen getMainScreen() {
        return mainScreen;
    }

    //Get views methods
    public GameView getGameView() {return gameView;}
    public StartView getStartView() {return startView;}
    public UserView getUserView() {return userView;}



    public void switchToStoryScene(){

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(startView.getStartScreen());

        startView.getStartScreen().stopTitleBackgroundAnimation();
        startView.getStartScreen().setStartImageViewImage(new Image("com/jdelza/view/assets/start_data/START_PRESSED.png"));

        transition.setOnFinished(actionEvent -> {

            //System.out.println("finito");
            startView.getStartScreen().goToStorySection();

            setChanged();
            notifyObservers("STORY");
        }

        );
        transition.play();

    }

    public void goFromStorySectionToUserSection(){




    }


    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof String stringEvent){
            switch (stringEvent){
                case "START_GAME" -> {
                    mainScreen.getChildren().setAll(gameView.getGamescreen());
                    //gameView.getGamescreen().requestFocus();
                    setChanged();
                    notifyObservers("START_GAME");

                }
                case "STORY" -> switchToStoryScene();
                case "USER" -> mainScreen.getChildren().setAll(userView.getUserScreen());
            }
        }

        /*
        if (o instanceof StartView){
            if (arg instanceof ChangeScreen){
                System.out.println(((ChangeScreen) arg).getScreen());

                mainScreen.getChildren().setAll(gameView.getGamescreen());
            }
        }

         */
    }
}
