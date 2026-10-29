package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.StartEndStatus;
import com.jdelza.utils.events.*;
import com.jdelza.utils.interfaces.EnvironmentSwitcher;
import com.jdelza.view.characters.EnemyView;
import com.jdelza.view.characters.OctorockView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.characters.TektiteView;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.GameScreen;
import com.jdelza.view.weapons.SwordView;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.*;
import java.util.List;


public class GameView extends Observable implements Observer{


    private GameScreen gamescreen;
    private GameModel gameModel;

    private PlayerView player;
    /**
     * GameView constructor
     */
    public GameView(GameModel gameModel) {

        this.player = new PlayerView();

        this.gamescreen = new GameScreen(new Overworld(), player, gameModel.getPlayer());
        gamescreen.getOverworld().setLayoutX(-gameModel.getPlayer().getPlayerMapPosition().getX() * Dimensions.MAP_WIDTH.get());
        gamescreen.getOverworld().setLayoutY(-gameModel.getPlayer().getPlayerMapPosition().getY() * Dimensions.MAP_HEIGHT.get());

        //gamescreen.addEnemy(new EnemyView(new Coordinates(7,7),new Coordinates(5,5)));

        this.gameModel = gameModel;
        addObserver(gameModel);

        gamescreen.getPlayer().setTranslateX(gamescreen.getPlayer().getTileWidth()  * gameModel.getPlayer().getPosition().getX());
        gamescreen.getPlayer().setTranslateY(gamescreen.getPlayer().getTileHeight() * gameModel.getPlayer().getPosition().getY());


        Arrays.stream(gameModel.getCurrentMap().getMap())
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
            playerViewAttack(pa.getPlayerWeapon(), pa.isThrowWeapon());

        }else if (arg instanceof SwordThrowing) {
            SwordThrowing s = (SwordThrowing) arg;
            if (s.getStatus() == StartEndStatus.END){

                testEnd();

            }else{

                test(s.getDirection());
            }

        }else if(arg instanceof EnemyDeath){
            EnemyDeath ed = (EnemyDeath)arg;
            gamescreen.removeEnemies(ed.getDeathEnemy());
        }


        if (arg instanceof EnvironmentSwitcher env){
            switch (env.getEnvironmentType()){
                case SHOP -> {
                    EnterTheShop esh = (EnterTheShop) env;
                    gamescreen.enterTheShopView(esh.getPlayerEntryPosition(), esh.getNpcType(), esh.getItemType());
                }
                case DUNGEON -> {}
                case OVERWORLD -> {

                    EnterOverworld eov = (EnterOverworld) env;
                    gamescreen.getOverworldWrapper().getChildren().remove(gamescreen.getCurrentEnvironment());
                    gamescreen.getOverworldWrapper().getChildren().add(gamescreen.getOverworld());
                    //gamescreen.setOverworldPosition(eov.getPlayerEntryPosition());
                    gamescreen.enterOverworld(eov.getPlayerEntryPosition());
                }
            }
        }


    }


}


