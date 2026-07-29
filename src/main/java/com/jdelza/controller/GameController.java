package com.jdelza.controller;

import com.jdelza.model.GameModel;
import com.jdelza.utils.enums.Directions;
import com.jdelza.view.GameView;
import javafx.scene.input.KeyEvent;

public class GameController {

    private final GameModel gameModel;
    private final GameView gameView;

    /**
     * GameController contructor
     * @param gameModel
     * @param gameView
     */
    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

        //Add a game view as an observer of the model
        gameModel.addObserver(gameView);


        //Gameview: on key pressed event
        gameView.getGamescreen().setOnKeyPressed(e-> this.handleKeyPressed(e));
    }

    /**
     * This method allow muove the player in the direction provided in input
     * @param direction
     */
    public void movePlayer(Directions direction){
        gameModel.movePlayer(direction);
    }

    /**
     *
     * @param ke
     */
    public void handleKeyPressed(KeyEvent ke){

        System.out.println(ke.getCode());

        switch(ke.getCode()){
            case UP:    movePlayer(Directions.UP); break;
            case DOWN:  movePlayer(Directions.DOWN); break;
            case LEFT:  movePlayer(Directions.LEFT); break;
            case RIGHT: movePlayer(Directions.RIGHT); break;
        }

    }




}
