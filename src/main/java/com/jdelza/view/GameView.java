package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.model.enemies.Octorock;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.StartEndStatus;
import com.jdelza.utils.events.*;
import com.jdelza.view.characters.EnemyView;
import com.jdelza.view.characters.OctorockView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.characters.TektiteView;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.GameScreen;
import com.jdelza.view.weapons.RockView;
import com.jdelza.view.weapons.SwordView;
import com.jdelza.view.weapons.WeaponView;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class GameView extends Observable implements Observer{


    private GameScreen gamescreen;
    private GameModel gameModel;
    /**
     * GameView constructor
     */
    public GameView(GameModel gameModel) {

        this.gamescreen = new GameScreen(new Overworld(), new PlayerView(), gameModel.getPlayer());
        gamescreen.getOverworld().setLayoutX(-gameModel.getPlayer().getPlayerMapPosition().getX() * Dimensions.MAP_WIDTH.get());
        gamescreen.getOverworld().setLayoutY(-gameModel.getPlayer().getPlayerMapPosition().getY() * Dimensions.MAP_HEIGHT.get());

        //gamescreen.addEnemy(new EnemyView(new Coordinates(7,7),new Coordinates(5,5)));

        this.gameModel = gameModel;
        addObserver(gameModel);

        gamescreen.getPlayer().setTranslateX(gamescreen.getPlayer().getTileWidth()  * gameModel.getPlayer().getPosition().getX());
        gamescreen.getPlayer().setTranslateY(gamescreen.getPlayer().getTileHeight() * gameModel.getPlayer().getPosition().getY());


        Arrays.stream(gameModel.getOverworldMap().getMap())
                .toList()
                .stream()
                .flatMap(Arrays::stream)
                .map(z->z.getEnemies())
                .flatMap(List::stream).forEach(
                        e-> {
                            //System.out.println("add " + e.getEnemyID());
                            EnemyView newEnemyView = null;
                            //!!!FACTORY PATTERN MAYBE!!!!
                            switch(e.getEnemyType()){
                                case OCTOROK -> newEnemyView = new OctorockView(e.getEnemyID());
                                case TEKTITE -> newEnemyView = new TektiteView(e.getEnemyID());
                            }
                            //EnemyView newEnemyView = new EnemyView(e.getEnemyMapCoordinates(), e.getPosition());
                            if (newEnemyView != null){
                                gamescreen.addAndPlaceEnemy(newEnemyView, e.getEnemyMapCoordinates(), e.getPosition());
                            }



                        }
                );

        //gamescreen.placeEnemies();

    }


    //Get methods
    public GameScreen getGamescreen() {return gamescreen;}

    public void injuring(Directions direction){
        gamescreen.getPlayer().setInjuring(true);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(gamescreen.getPlayer());

        //Setting transition duration
        transition.setDuration(Duration.millis(2000));

        //Start transition
        transition.play();

        //When transition ends
        transition.setOnFinished(e->{
            gamescreen.getPlayer().setTileImageView(gamescreen.getPlayer().getWalkingImage().get(gamescreen.getPlayer().getPlayerDirection())[0]);
            gamescreen.getPlayer().setInjuring(false);

            setChanged();
            notifyObservers(new PlayerCollision(false));
        });

    }

    /*
    public void playerViewAttack(Directions direction, boolean throwWeapon){

        int index = gamescreen.getPlayer().getAttackImageIndex();

        if (index == 0 && throwWeapon){gamescreen.getPlayer().setThrowingWeapon(true);}

        if (index < 3){
            gamescreen.getPlayer().attack(direction);

        }else{
            //System.out.println("Siamo fuori");

            if (gamescreen.getPlayer().isThrowingWeapon()){

            }

            gamescreen.getPlayer().endAttack();

            setChanged();
            notifyObservers("END_ATTACK");
        }

    }

     */

    public void playerViewAttack(Weapon weapon, boolean throwWeapon){

        int index = gamescreen.getPlayer().getAttackImageIndex();

        //System.out.println(throwWeapon);

        if (index < 3){
            gamescreen.getPlayer().attack(weapon.getWeaponDirection());

        }else{
            //System.out.println("Siamo fuori");
            if (throwWeapon){
                addPlayerWeaponToGameScreen(weapon);
            }

            gamescreen.getPlayer().endAttack();

            setChanged();
            notifyObservers(new PlayerAttack(weapon, throwWeapon, StartEndStatus.END));
        }

    }



    /*
    public void playerThrowWeapon(Weapon playerWeapon){


        int index = gamescreen.getPlayer().getAttackImageIndex();

        if (index < 3){

            gamescreen.getPlayer().throwWeapon(playerWeapon.getWeaponDirection());

            if (gamescreen.getOverworld().getChildren().stream().filter(n-> n instanceof WeaponView).noneMatch(w->((WeaponView) w).getWeaponID().equals("PLAYER_WEAPON"))){

                addPlayerWeaponToGameScreen(playerWeapon.getWeaponDirection(), playerWeapon.getPosition(), playerWeapon.getWeaponMapPosition());
            }


        }else{
            //System.out.println("Siamo fuori");
            gamescreen.getPlayer().endThrowAttack();

            setChanged();
            notifyObservers("END_ATTACK");
        }



    }

     */


    public void addPlayerWeaponToGameScreen(Weapon weapon){
        int x = weapon.getWeaponMapPosition().getX()*Dimensions.ZONE_COLUMNS.get()+weapon.getPosition().getX();
        int y = weapon.getWeaponMapPosition().getY()*Dimensions.ZONE_ROWS.get()+weapon.getPosition().getY();

        gamescreen.getPlayer().getThrowingWeapon().setTranslateX(Dimensions.TILE_WIDTH.get()*x);
        gamescreen.getPlayer().getThrowingWeapon().setTranslateY(Dimensions.TILE_HEIGT.get()*y);
        gamescreen.getPlayer().getThrowingWeapon().setWeaponDirection(weapon.getWeaponDirection());
        gamescreen.getPlayer().getThrowingWeapon().initializeThrowingImage(weapon.getWeaponDirection());
        gamescreen.getOverworld().getChildren().add(gamescreen.getPlayer().getThrowingWeapon());
    }



    public void test(Directions direction){


        gamescreen.getPlayer().getThrowingWeapon().swordThrowingToggleImage(direction);



        TranslateTransition transition = new TranslateTransition();
        transition.setNode(gamescreen.getPlayer().getThrowingWeapon());

        //Setting transition duration
        transition.setDuration(Duration.millis(100));

        switch (direction){
            //Move the player in the indicated direction; translate the player by a certain distance
            case UP:    transition.setByY(-gamescreen.getPlayer().getThrowingWeapon().getHeight());break;
            case DOWN:  transition.setByY(+gamescreen.getPlayer().getThrowingWeapon().getHeight());break;
            case LEFT:  transition.setByX(-gamescreen.getPlayer().getThrowingWeapon().getWidth());break;
            case RIGHT: transition.setByX(+gamescreen.getPlayer().getThrowingWeapon().getWidth());break;
        }

        //Start transition
        transition.play();

        //When transition ends
        transition.setOnFinished(e->{});



    }

    public void testEnd(){
        gamescreen.getPlayer().getPlayerSword().setThrowing(false);

        /*
        Timer timer = new Timer();
        TimerTask collisionTask = new TimerTask() {

            int count = 1;
            String path  ="file:///C:/Users/Giuseppe Lentini/Downloads/DEATH_";


            @Override
            public void run() {

                Platform.runLater(()->{

                    gamescreen.getPlayer().getThrowingWeapon().changeSwordImage(new Image(path+count+".png"));
                    count++;

                    //System.out.println(path+count+".png");

                    if (count>= 5) {

                        gamescreen.getOverworld().getChildren().remove(gamescreen.getPlayer().getThrowingWeapon());

                        gamescreen.getPlayer().getThrowingWeapon().setLayoutY(0);
                        gamescreen.getPlayer().getThrowingWeapon().setLayoutX(0);
                        gamescreen.getPlayer().getThrowingWeapon().setTranslateX(0);
                        gamescreen.getPlayer().getThrowingWeapon().setTranslateY(0);

                        timer.cancel();
                    }
                });

                }

        };


        timer.scheduleAtFixedRate(collisionTask, 0, 100);


         */



        gamescreen.getPlayer().getThrowingWeapon().getChildren().remove(gamescreen.getPlayer().getThrowingWeapon().getSwordImageView());
        gamescreen.getPlayer().getThrowingWeapon().swordCollision(gamescreen.getOverworld(), gamescreen.getPlayer().getThrowingWeapon());


        setChanged();
        notifyObservers(new SwordThrowing(Directions.DOWN, StartEndStatus.END));


    }



    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof PlayerMovement){
            PlayerMovement pm = (PlayerMovement) arg;
            gamescreen.movePlayer(pm);
        }
        else if (arg instanceof EnemyMovement){
            EnemyMovement em = (EnemyMovement)arg;
            gamescreen.moveEnemies(em.getEnemies());

        }
        else if (arg instanceof Attack){
            Attack a = (Attack) arg;
            gamescreen.moveWeapons(a.getWeapons());
        }

        else if(arg instanceof Weapon){
            Weapon w = (Weapon) arg;
            gamescreen.addWeapon(w);

        } else if (arg instanceof RemoveWeapons) {
            RemoveWeapons remove = (RemoveWeapons)arg;
            gamescreen.removeEnemyWeapon(remove.getDeleteWeapons());

        }else if(arg instanceof PlayerCollision){
            PlayerCollision pc = (PlayerCollision) arg;
            injuring(pc.getPlayerDirection());

        }else if(arg instanceof PlayerAttack){

            PlayerAttack pa = (PlayerAttack)arg;

            //System.out.println(pa.getPlayerWeapon());

            playerViewAttack(pa.getPlayerWeapon(), pa.isThrowWeapon());
            /*
            if (pa.isThrowWeapon()){
                playerThrowWeapon(pa.getPlayerWeapon());

                //System.out.println("lancia");
            }else{
                playerViewAttack(pa.getAttackDirection());

                //System.out.println("non lancia");
            }

            */

        }else if (arg instanceof SwordThrowing) {
            SwordThrowing s = (SwordThrowing) arg;
            if (s.getStatus() == StartEndStatus.END){
                //System.out.println("end throw");

                //gamescreen.getPlayer().endWeaponThrowing();
                testEnd();
            }else{

                test(s.getDirection());
            }

        }else if(arg instanceof EnemyDeath){
            EnemyDeath ed = (EnemyDeath)arg;
            System.out.println("gameview rimuovi!");
            gamescreen.removeEnemies(ed.getDeathEnemy());
        }


    }


}


