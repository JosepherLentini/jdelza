package com.jdelza.controller;

import com.jdelza.model.GameModel;
import com.jdelza.model.enums.Directions;
import com.jdelza.view.GameView;
import com.jdelza.view.screen.GameScreen;
import javafx.scene.input.KeyEvent;

public class GameController {

    private final GameModel gameModel;
    private final GameView gameView;

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

        gameModel.addObserver(gameView);
    }

    public void movePlayer(Directions direction){
        gameModel.movePlayer(direction);
    }

    public void handleKeyPressed(KeyEvent ke){

        System.out.println(ke.getCode());

        switch(ke.getCode()){
            case UP: movePlayer(Directions.UP); break;
            case DOWN:  movePlayer(Directions.DOWN); break;
            case LEFT:  movePlayer(Directions.LEFT); break;
            case RIGHT: movePlayer(Directions.RIGHT); break;
        }

    }




}
