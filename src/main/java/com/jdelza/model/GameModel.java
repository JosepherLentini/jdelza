package com.jdelza.model;

import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.model.world.WorldMap;
import com.jdelza.utils.events.PlayerMovement;

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


        boolean up =   player.getPosition().getY() == 0 && player.getPlayerMapPosition().getY() == 0 && direction == Directions.UP;
        boolean down = player.getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && player.getPlayerMapPosition().getY() == Dimensions.MAP_ROWS.get()-1 && direction == Directions.DOWN;
        boolean left =  player.getPosition().getX() == 0 && player.getPlayerMapPosition().getX() == 0 && direction == Directions.LEFT;
        boolean right = player.getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && player.getPlayerMapPosition().getX() == Dimensions.MAP_COLUMNS.get()-1 && direction == Directions.RIGHT;

        if(up || down || left || right){
            System.out.println("pup");
        }
        else{
            if (player.getPosition().getY() == 0 && direction == Directions.UP){
                //gameView.changeZone(player.getPosition().getX(), player.getPosition().getY()-1);
                System.out.println("sbatti sopra");
                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }
            else if(player.getPosition().getX() == 0 && direction == Directions.LEFT){
                System.out.println("sbatti sinistra");
                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }
            else if(player.getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && direction == Directions.DOWN){
                System.out.println("sbatti sotto");
                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }
            else if(player.getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && direction == Directions.RIGHT){
                System.out.println("sbatti destra");
                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );

            }
            else{

                Zone currentZone = overworldMap.getZone(player.getPlayerMapPosition());

                Coordinates nextCoordinates  =new Coordinates(
                        player.getPosition().getX() + direction.getX(),
                        player.getPosition().getY() + direction.getY()
                );

                if (currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable()){

                    player.move(direction);
                    setChanged();
                    notifyObservers(new PlayerMovement(false,direction)  );
                }

            }

        }











        //System.out.println("Model: game");




    }



}
