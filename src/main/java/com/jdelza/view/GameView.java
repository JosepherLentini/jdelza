package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.utils.enums.Directions;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.GameScreen;

import java.util.Observable;
import java.util.Observer;

public class GameView implements Observer {


    private GameScreen gamescreen;
    private GameModel gameModel;
    /**
     * GameView constructor
     */
    public GameView(GameModel gameModel) {

        this.gamescreen = new GameScreen(new Overworld(), new PlayerView());
        this.gameModel = gameModel;

        gamescreen.getPlayer().setTranslateX(gamescreen.getPlayer().getPlayerWidth()*gameModel.getPlayer().getPosition().getX());
        gamescreen.getPlayer().setTranslateY(gamescreen.getPlayer().getPlayerHeight()*gameModel.getPlayer().getPosition().getY());


    }


    //Get methods
    public GameScreen getGamescreen() {return gamescreen;}


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Directions){
            gamescreen.movePlayer((Directions) arg);
        }
    }
}
