package com.jdelza.controller;

import com.jdelza.model.GameModel;
import com.jdelza.model.characters.Enemy;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.StartEndStatus;
import com.jdelza.utils.events.*;
import com.jdelza.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {

    private final GameModel gameModel;
    //private final GameView gameView;

    private AnimationTimer gameLoop;

    private boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;


    private long lastMove = 0;
    private final long MOVE_DELAY = 160_000_000; // 200 ms

    private long lastAnimation = 0;
    private final long ANIMATION_DELAY = 100_000_000;

    private long enemyAnimation = 0;
    private final long ENEMY_MOVE_DELAY = 300_000_000;

    private long weaponAnimation = 0;
    private final long WEAPOM_DELAY = 100_000_000;

    private long playerInjureAnimation = 0;
    private final long INJURE_DELAY = 200_000_000;

    private long playerAttackAnimation = 0;
    private final long PLAYER_ATTACK_DELAY = 100_000_000;

    private long swordThrowingAnimation = 0;
    private final long SWORD_THROWING_DELAY = 50_000_000;


    /**s
     * GameController contructor
     * @param gameModel
     */
    public GameController(GameModel gameModel, GameView gameView) {

        this.gameModel = gameModel;

        //Add a gameview as an observer of the model
        gameModel.addObserver(gameView);

        //Game view: on key pressed event
        gameView.getGamescreen().setOnKeyPressed(this::handleKeyPressed);
        gameView.getGamescreen().setOnKeyReleased(this::handleKeyReleased);


        //Game loop created by javafx animation timer
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update(l);

            }
        };
        //gameLoop.start();

    }


    //Get methodss
    public AnimationTimer getGameLoop() {return gameLoop;}


    public void startGame(){

        this.gameLoop.start();
        gameModel.startGame();
    }

    /**
     * Method that is called whe a key is pressed
     * @param ke, type KeyEvent
     */
    public void handleKeyPressed(KeyEvent ke){
        KeyCode code = ke.getCode();

        switch (code) {
            case UP    -> upPressed = true;
            case DOWN  -> downPressed = true;
            case LEFT  -> leftPressed = true;
            case RIGHT -> rightPressed = true;
            case SPACE -> spacePressed = true;
        }

    }



    /**
     * Method that is called when a key is released
     * @param ke
     */
    public void handleKeyReleased(KeyEvent ke){
        List<Enemy> enemies = gameModel.getCurrentMap().getZone(gameModel.getPlayer().getPlayerMapPosition()).getEnemies();


        switch (ke.getCode()) {
            case UP -> upPressed = false;
            case DOWN -> downPressed = false;
            case LEFT -> leftPressed = false;
            case RIGHT -> rightPressed = false;
            case SPACE -> spacePressed = false;
        }

    }

    /**
     * This methods allows the player’s movement to be executed in the view and the model to be updated
     * @param now
     */
    public void movePlayer(long now){


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

    public void playerAttack(){
        if (spacePressed){
            gameModel.playerAttack();
        }
    }


    public void update(long now){


        if ( !gameModel.getCurrentMap().isZoneChanging() && !gameModel.getPlayer().isAttacking()){movePlayer(now); }

        playerAttack();

        if (now - enemyAnimation >= ENEMY_MOVE_DELAY) {
            gameModel.enemyActions();

            enemyAnimation = now;

        }

        if (now - weaponAnimation >= WEAPOM_DELAY){
            gameModel.moveEnemyWeapons();
            weaponAnimation = now;
        }


        if (gameModel.getPlayer().isAttacking()){
            if (now - playerAttackAnimation >= PLAYER_ATTACK_DELAY){

                gameModel.playerAttack();
                playerAttackAnimation = now;
            }

        }


        if(gameModel.getPlayer().isWeaponThrowing()){

            if (now - swordThrowingAnimation >= SWORD_THROWING_DELAY){

                gameModel.throwPlayerWeapon();
                swordThrowingAnimation = now;
            }
        }



    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String stringEvent){
            switch (stringEvent) {
                case "START_GAME" -> startGame();
            }

            }

        if (arg instanceof PlayerTakeDamage pc){
            if (!pc.isPlayerInjuring()){
                gameModel.getPlayer().setHasPlayerInjuring(false);
            }
        }

        if (arg instanceof PlayerAttack){
            gameModel.getPlayer().playerEndAttack();

        }

        if (arg instanceof SwordThrowing st){

            if(st.getStatus() == StartEndStatus.END){

                gameModel.getPlayer().endPlayerWeaponThrowing();
            }else{

                gameModel.getPlayer().setWeaponThrowing(true);
            }
        }

        if (arg instanceof PlayerTakeItem pti){
            if(pti.getStatus() == StartEndStatus.END )  gameModel.getPlayer().setTakingItem(false);
        }

        if(arg instanceof ChangeZone changeZone){
            gameModel.getCurrentMap().setZoneChanging(!(changeZone.getStatus() == StartEndStatus.END));
        }




    }
}
