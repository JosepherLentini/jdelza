package com.jdelza.view;

import com.jdelza.model.GameModel;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.events.PlayerMovement;
import com.jdelza.view.characters.EnemyView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.GameScreen;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class GameView implements Observer {


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

        gamescreen.getPlayer().setTranslateX(gamescreen.getPlayer().getTileWidth()  * gameModel.getPlayer().getPosition().getX());
        gamescreen.getPlayer().setTranslateY(gamescreen.getPlayer().getTileHeight() * gameModel.getPlayer().getPosition().getY());


        Arrays.stream(gameModel.getOverworldMap().getMap())
                .toList()
                .stream()
                .flatMap(Arrays::stream)
                .map(z->z.getEnemies())
                .flatMap(List::stream).forEach(
                        e-> {
                            EnemyView newEnemyView = new EnemyView(e.getEnemyMapCoordinates(), e.getPosition());
                            newEnemyView.setEnemyViewID(e.getEnemyID());
                            gamescreen.addEnemy(newEnemyView);
                        }
                );

        gamescreen.placeEnemies();



    }


    //Get methods
    public GameScreen getGamescreen() {return gamescreen;}


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof PlayerMovement){
            PlayerMovement pm = (PlayerMovement) arg;
            gamescreen.movePlayer(pm);


        }
    }


}
