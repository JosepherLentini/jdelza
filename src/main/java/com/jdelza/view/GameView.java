package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.model.enemies.Octorock;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.events.*;
import com.jdelza.view.characters.EnemyView;
import com.jdelza.view.characters.OctorockView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.characters.TektiteView;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.GameScreen;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import java.util.Observable;


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
                                case OCTOROK -> newEnemyView = new OctorockView(e.getEnemyMapCoordinates(), e.getPosition());
                                case TEKTITE -> newEnemyView = new TektiteView(e.getEnemyMapCoordinates(), e.getPosition());
                            }
                            //EnemyView newEnemyView = new EnemyView(e.getEnemyMapCoordinates(), e.getPosition());
                            if (newEnemyView != null){
                                newEnemyView.setEnemyViewID(e.getEnemyID());
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
            gamescreen.getPlayer().setTileImageView(gamescreen.getPlayer().getWalkingImage().get(direction)[0]);
            gamescreen.getPlayer().setInjuring(false);

            setChanged();
            notifyObservers(new PlayerCollision(false));
        });

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
            gamescreen.removeWeapon(remove.getDeleteWeapons());

        }else if(arg instanceof PlayerCollision){
            PlayerCollision pc = (PlayerCollision) arg;

            injuring(pc.getPlayerDirection());
        }


    }


}


