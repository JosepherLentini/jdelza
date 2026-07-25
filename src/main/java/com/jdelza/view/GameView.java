package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.model.enums.Directions;
import com.jdelza.utils.Events;
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
    public GameView(GameModel gamemodel) {

        this.gamescreen = new GameScreen(new Overworld(), new PlayerView());
        this.gameModel = gamemodel;

        gamescreen.getPlayer().setTranslateX(gamescreen.getPlayer().getPlayerWidth()*gamemodel.getPlayer().getPosition().getX());
        gamescreen.getPlayer().setTranslateY(gamescreen.getPlayer().getPlayerHeight()*gamemodel.getPlayer().getPosition().getY());


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
