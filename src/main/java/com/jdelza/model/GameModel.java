package com.jdelza.model;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.Action;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.model.world.WorldMap;
import com.jdelza.utils.events.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecations")
public class GameModel extends Observable implements Observer {

    private Player player;
    private WorldMap overworldMap;


    public GameModel() throws IOException {
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

        boolean collide = player.collidedWithEnemy(overworldMap.getZone(player.getPlayerMapPosition()), direction);


        boolean up    = player.getPosition().getY() == 0 && player.getPlayerMapPosition().getY() == 0 && direction == Directions.UP;
        boolean down  = player.getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && player.getPlayerMapPosition().getY() == Dimensions.MAP_ROWS.get()-1 && direction == Directions.DOWN;
        boolean left  = player.getPosition().getX() == 0 && player.getPlayerMapPosition().getX() == 0 && direction == Directions.LEFT;
        boolean right = player.getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && player.getPlayerMapPosition().getX() == Dimensions.MAP_COLUMNS.get()-1 && direction == Directions.RIGHT;

        if(up || down || left || right){return;}
        else{
            if (player.getPosition().getY() == 0 && direction == Directions.UP){

                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }
            else if(player.getPosition().getX() == 0 && direction == Directions.LEFT){

                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }
            else if(player.getPosition().getY() == Dimensions.ZONE_ROWS.get()-1 && direction == Directions.DOWN){

                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }
            else if(player.getPosition().getX() == Dimensions.ZONE_COLUMNS.get()-1 && direction == Directions.RIGHT){

                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );

            }
            else{

                Zone currentZone = overworldMap.getZone(player.getPlayerMapPosition());

                Coordinates nextCoordinates = new Coordinates(
                        player.getPosition().getX() + direction.getX(),
                        player.getPosition().getY() + direction.getY()
                );

                if ( currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable()){

                    if (collide && !player.isHasPlayerInjuring()){
                        //Player has collided
                        player.setHasPlayerInjuring(true);
                        setChanged();
                        notifyObservers(new PlayerCollision(direction));
                    }

                    player.move(direction);

                    setChanged();
                    notifyObservers(new PlayerMovement(false,direction)  );
                }

            }

        }

    }


    //Enemy actions
    public void enemyActions(){
        //Set the same zone of the player
        Zone currentPlayerZone = overworldMap.getZone(getPlayer().getPlayerMapPosition());

        //Enemies in the zone
        List<Enemy> playerSameZoneEnemies = currentPlayerZone.getEnemies();

        if (!playerSameZoneEnemies.isEmpty()) {
            for (Enemy enemy : playerSameZoneEnemies) {

                //If action enemy is WALK
                if (enemy.getEnemyAction() == Action.WALK){

                    if(enemy.collisionWithPlayer(player) && !player.isHasPlayerInjuring()){
                        setChanged();
                        notifyObservers(new PlayerCollision(player.getPlayerDirection()));
                    }
                    enemy.move(currentPlayerZone);
                    //Notify observer
                    setChanged();
                    notifyObservers(new EnemyMovement(playerSameZoneEnemies));
                    /*
                    if(enemy.getSteps() > 0){

                        //Move the enemy
                        enemy.move(currentPlayerZone);

                        //Notify observer
                        setChanged();
                        notifyObservers(new EnemyMovement(playerSameZoneEnemies));
                    }
                    else{
                        enemy.chooseAndSetAnAction();
                    }

                     */
                }

                else if (enemy.getEnemyAction() == Action.ATTACK){

                    //Attacco
                    //System.out.println(enemy.getEnemyAction());
                    if (enemy.hasWeapon()){
                        Weapon newWeapon = new Weapon(
                                new Coordinates(
                                        enemy.getPosition().getX()+enemy.getEnemyDirection().getX(),
                                        enemy.getPosition().getY()+enemy.getEnemyDirection().getY()
                                ),
                                enemy.getWeaponType(),
                                enemy.getEnemyDirection()

                        );


                        newWeapon.setWeaponMapPosition(enemy.getEnemyMapCoordinates());
                        currentPlayerZone.addWeapon(newWeapon.setWeaponID());

                        setChanged();
                        notifyObservers(newWeapon);
                    }





                    //For Octotock use function "endOctorockAttack".
                    //enemy.setEnemyAction(Action.WALK);
                    //enemy.chooseandSetNuberOfSteps();
                    //enemy.chooseAndSetDirection();
                    enemy.resetEnemyAttack();


                }



            }
        }
    }

    //Weapon actions
    public void moveWeapon(){
        //Set the same zone of the player
        Zone currentPlayerZone = overworldMap.getZone(getPlayer().getPlayerMapPosition());

        //Enemies in the zone
        List<Weapon> playerSameZoneWeapons = currentPlayerZone.getWeapons();

        //Accumulate there the weapon need to delete
        List<Weapon> deleteWeapons = new ArrayList<>();


        if (!playerSameZoneWeapons.isEmpty()) {
            //System.out.println(playerSameZoneWeapons.size());

            for (Weapon weapon : playerSameZoneWeapons){

                boolean weaponCollided = weapon.hasCollidedWithPlayer(player);

                //System.out.println("move "+weapon.getWeaponRange());

                if(weaponCollided && !player.isHasPlayerInjuring()){
                    deleteWeapons.add(weapon);

                    setChanged();
                    notifyObservers(new PlayerCollision(player.getPlayerDirection()));


                }else if (weapon.getWeaponRange() > 0) {
                    weapon.moveWeapon(currentPlayerZone);

                    setChanged();
                    notifyObservers(new Attack(playerSameZoneWeapons));
                }

                else{
                    deleteWeapons.add(weapon);

                }


            }

            //If there are weapon to delete
            if (!deleteWeapons.isEmpty()){
                //Remove them
                playerSameZoneWeapons.removeAll(deleteWeapons);
                //Update the weapon list of the current zone
                overworldMap.getZone(getPlayer().getPlayerMapPosition()).setWeapons(playerSameZoneWeapons);

                //Notify observer
                setChanged();
                notifyObservers(new RemoveWeapons(deleteWeapons));
            }


        }


    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof PlayerCollision pc){
            if (!pc.isPlayerInjuring()){
                player.setHasPlayerInjuring(false);
            }
        }
    }
}
