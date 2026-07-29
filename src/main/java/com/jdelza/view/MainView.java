package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.utils.events.ChangeScreen;
import com.jdelza.view.screen.MainScreen;
import javafx.geometry.Pos;

import java.util.Observable;
import java.util.Observer;

public class MainView implements Observer{

    //Wrapper of all section components
    private MainScreen mainScreen;

    //Model
    private GameModel gameModel;

    //Views
    private GameView gameView;
    private StartView startView;


    public MainView(GameModel gameModel) {

        this.mainScreen = new MainScreen();
        mainScreen.setAlignment(Pos.CENTER);

        this.gameView = new GameView(gameModel);
        this.startView = new StartView();

        mainScreen.getChildren().add(startView.getStartScreen());

        startView.addObserver(this);

    }

    //get screen methods
    public MainScreen getMainScreen() {
        return mainScreen;
    }

    //Get views methods
    public GameView getGameView() {return gameView;}
    public StartView getStartView() {return startView;}

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof StartView){
            if (arg instanceof ChangeScreen){
                System.out.println(((ChangeScreen) arg).getScreen());

                mainScreen.getChildren().setAll(gameView.getGamescreen());
            }
        }
    }
}
