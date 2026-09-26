package com.jdelza.controller;

import com.jdelza.model.GameModel;
import com.jdelza.model.characters.Enemy;
import com.jdelza.utils.enums.Directions;
import com.jdelza.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class GameController{

    private final GameModel gameModel;
    private final GameView gameView;

    private AnimationTimer gameLoop;

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    private long lastMove = 0;
    private final long MOVE_DELAY = 200_000_000; // 200 ms

    private long lastAnimation = 0;
    private final long ANIMATION_DELAY = 100_000_000;

    private long enemyAnimation = 0;
    private final long ENEMY_MOVE_DELAY = 300_000_000;

    private long weaponAnimation = 0;
    private final long WEAPOM_DELAY = 100_000_000;

    private long playerInjureAnimation = 0;
    private final long INJURE_DELAY = 200_000_000;


    /**s
     * GameController contructor
     * @param gameModel
     * @param gameView
     */
    public GameController(GameModel gameModel, GameView gameView) {

        this.gameModel = gameModel;
        this.gameView = gameView;

        //Add a gameview as an observer of the model
        gameModel.addObserver(gameView);

        //Gameview: on key pressed event
        gameView.getGamescreen().setOnKeyPressed( e -> this.handleKeyPressed(e));
        gameView.getGamescreen().setOnKeyReleased(e -> handleKeyReleased(e));

        //Game loop created by javafx animation timer
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update(l);

            }
        };
        gameLoop.start();

    }


    /**
     * Method that is called whe a key is pressed
     * @param ke
     */
    public void handleKeyPressed(KeyEvent ke){
        KeyCode code = ke.getCode();
        /*
        switch (code) {
            case UP -> movePlayer(Directions.UP);       //gameModel.setPlayerDirection(Directions.UP);
            case DOWN -> movePlayer(Directions.DOWN);   //gameModel.setPlayerDirection(Directions.DOWN);
            case LEFT -> movePlayer(Directions.LEFT);   //gameModel.setPlayerDirection(Directions.LEFT);
            case RIGHT -> movePlayer(Directions.RIGHT); //gameModel.setPlayerDirection(Directions.RIGHT);
        }

         */

        switch (code) {
            case UP    -> upPressed = true;
            case DOWN  -> downPressed = true;
            case LEFT  -> leftPressed = true;
            case RIGHT -> rightPressed = true;
        }

    }



    /**
     * Method that is called when a key is released
     * @param ke
     */
    public void handleKeyReleased(KeyEvent ke){
        List<Enemy> enemies = gameModel.getOverworldMap().getZone(gameModel.getPlayer().getPlayerMapPosition()).getEnemies();


        switch (ke.getCode()) {
            case UP -> upPressed = false;
            case DOWN -> downPressed = false;
            case LEFT -> leftPressed = false;
            case RIGHT -> rightPressed = false;
        }

    }

    /**
     * This methods allows the player’s movement to be executed in the view and the model to be updated
     * @param now
     */
    public void movePlayer(long now){
        if (!gameView.getGamescreen().isPlayerMoving()){
            if (upPressed && now - lastMove >= MOVE_DELAY) {
                gameModel.movePlayer(Directions.UP);
                lastMove = now;
            } else if (downPressed && now - lastMove >= MOVE_DELAY) {
                gameModel.movePlayer(Directions.DOWN);
                lastMove = now;

            } else if (leftPressed && now - lastMove >= MOVE_DELAY) {
                gameModel.movePlayer(Directions.LEFT);
                lastMove = now;
            } else if (rightPressed && now - lastMove >= MOVE_DELAY) {
                gameModel.movePlayer(Directions.RIGHT);
                lastMove = now;
            }
        }


        if (upPressed && now - lastAnimation >= ANIMATION_DELAY && !gameModel.getPlayer().isHasPlayerInjuring()){
            gameView.getGamescreen().getPlayer().changeSprite(Directions.UP);
            lastAnimation = now;
        }
        else if(downPressed && now - lastAnimation >= ANIMATION_DELAY && !gameModel.getPlayer().isHasPlayerInjuring()){
            gameView.getGamescreen().getPlayer().changeSprite(Directions.DOWN);
            lastAnimation  = now;

        } else if (leftPressed && now - lastAnimation >= ANIMATION_DELAY && !gameModel.getPlayer().isHasPlayerInjuring()) {
            gameView.getGamescreen().getPlayer().changeSprite(Directions.LEFT);
            lastAnimation  = now;
        } else if (rightPressed && now - lastAnimation >= ANIMATION_DELAY && !gameModel.getPlayer().isHasPlayerInjuring()) {
            gameView.getGamescreen().getPlayer().changeSprite(Directions.RIGHT);
            lastAnimation  = now;
        }


    }


    public void update(long now){
        if (!gameView.getGamescreen().isZoneChanging()){movePlayer(now);}



        if (gameView.getGamescreen().getPlayer().isInjuring()){

            if (now - playerInjureAnimation >= INJURE_DELAY) {
                gameView.getGamescreen().getPlayer().playerInjured();
                playerInjureAnimation = now;

            }

        }

        if (now - enemyAnimation >= ENEMY_MOVE_DELAY) {
            gameModel.enemyActions();

            enemyAnimation = now;
            gameView.getGamescreen().changeEnemySprite();
        }


        if (now - weaponAnimation >= WEAPOM_DELAY){
            gameModel.moveWeapon();
            weaponAnimation = now;
        }






    }


}
