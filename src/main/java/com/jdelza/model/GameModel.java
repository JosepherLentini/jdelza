package com.jdelza.model;

import com.jdelza.utils.enums.characters.Player;
import com.jdelza.utils.enums.Directions;
import com.jdelza.model.world.WorldMap;

import java.util.Observable;

@SuppressWarnings("deprecations")
public class GameModel extends Observable {

    private Player player;
    private WorldMap overworldMap;


    public GameModel() {
        this.player = Player.getPlayerInstance();
        this.overworldMap = new WorldMap(
                this.player
        );




    }

    //Get methods
    public Player getPlayer() {return player;}
    public WorldMap getOverworldMap() {return overworldMap;}


    //Player actions
    public void movePlayer(Directions direction){

            player.move(direction);
            setChanged();
            notifyObservers(direction);


    }



}
