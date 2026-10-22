package com.jdelza.model;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.model.world.Zone;
import com.jdelza.utils.enums.*;
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


        if (!player.isPlayerAtTheBorderOfTheMap(direction)){
            if (player.shouldPlayerChangeZone(direction)){
                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }else{


                Zone currentZone = overworldMap.getZone(player.getPlayerMapPosition());

                Coordinates nextCoordinates = new Coordinates(
                        player.getPosition().getX() + direction.getX(),
                        player.getPosition().getY() + direction.getY()
                );

                if ( currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable()){

                    boolean collide = player.hasPlayerCollidedWithEnemy(overworldMap.getZone(player.getPlayerMapPosition()), direction);

                    if (collide && !player.isHasPlayerInjuring()){
                        //Player has collided
                        player.takeDamage(DamageType.LIGHT);

                        setChanged();
                        notifyObservers(new PlayerCollision(direction));
                    }

                    player.move(direction);

                    setChanged();
                    notifyObservers(new PlayerMovement(false,direction)  );
                }


            }
        }



        //if(up || down || left || right){return;}
        /*
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

         */



    }

    /**
     * This function controls the enemy’s attack and movement actions
     */
    public void enemyActions(){
        //Set the same zone of the player
        Zone currentPlayerZone = overworldMap.getZone(getPlayer().getPlayerMapPosition());

        //Enemies in the zone
        List<Enemy> playerSameZoneEnemies = currentPlayerZone.getEnemies();


        //if there is some enemies in the zone
        if (!playerSameZoneEnemies.isEmpty()) {

            //Create empty list for enemy to delete
            List<Enemy> removeEnemy = new ArrayList<>();

            //for each enemy
            for (Enemy enemy : playerSameZoneEnemies) {

                //has enemy collided with enemy?
                boolean enemyCollisionWithPlayer = enemy.hasEnemyCollidedWithEnemy(player);

                //switch enemy current action
                switch (enemy.getEnemyAction()){

                    //if enemy is walking
                    case WALK -> {
                        //if enemy collided with player
                        if( enemyCollisionWithPlayer && !player.isHasPlayerInjuring()){

                            //player will lose some life points
                            player.takeDamage(enemy.getDamageType());

                            //Show player collision
                            setChanged();
                            notifyObservers(new PlayerCollision(player.getPlayerDirection()));
                        }

                        //if enemy collided with player weapon
                        if(enemy.collisionWithPlayerWeapon(player) && player.isWeaponThrowing()){

                            //enemy lose some of his life points
                            enemy.takeDamage(player.getPlayerWeapon().getAttackPoints());

                            //if enemy lose all his life points
                            if (enemy.getLifePoints() <= 0){

                                //add to remove enemy list
                                removeEnemy.add(enemy);
                            }

                            //Show weapon collision on the view
                            setChanged();
                            notifyObservers(new SwordThrowing(player.getPlayerWeapon().getWeaponDirection(), StartEndStatus.END));
                        }
                        //move enemy to the next position
                        enemy.move(currentPlayerZone);

                        //Notify observer
                        setChanged();
                        notifyObservers(new EnemyMovement(playerSameZoneEnemies));

                    }

                    case ATTACK -> {
                        //if enemy has a weapon
                        if (enemy.hasWeapon()){
                            Weapon newWeapon = new Weapon(
                                    new Coordinates(
                                            enemy.getPosition().getX()+enemy.getEnemyDirection().getX(),
                                            enemy.getPosition().getY()+enemy.getEnemyDirection().getY()
                                    ),
                                    enemy.getWeaponType(),
                                    enemy.getEnemyDirection()

                            );

                            //Set weapon map coordinates
                            newWeapon.setWeaponMapPosition(enemy.getEnemyMapCoordinates());
                            //Add weapon to the map
                            currentPlayerZone.addWeapon(newWeapon.setWeaponID());

                            //Show the attack
                            setChanged();
                            notifyObservers(newWeapon);
                        }

                        //remove ATTACK status from the enemy
                        enemy.resetEnemyAttack();

                    }
                }


            }



            if (!removeEnemy.isEmpty()){

                getOverworldMap().getZone(player.getPlayerMapPosition()).getEnemies().removeAll(removeEnemy);

                setChanged();
                notifyObservers(new EnemyDeath(removeEnemy.getFirst()));
            }


        }
    }

    /**
     * This function allows you to manage the movement of weapons
     * launched by the enemy and to handle their collisions
     */
    public void moveEnemyWeapons(){
        //Set the same zone of the player
        Zone currentPlayerZone = overworldMap.getZone(getPlayer().getPlayerMapPosition());

        //Enemies in the zone
        List<Weapon> playerSameZoneWeapons = currentPlayerZone.getWeapons();

        //Accumulate there the weapon need to delete
        List<Weapon> deleteWeapons = new ArrayList<>();

        //If zone has some weapons
        if (!playerSameZoneWeapons.isEmpty()) {

            //For each weapon in the zone
            for (Weapon weapon : playerSameZoneWeapons){

                //Has weapon collided with player?
                boolean weaponCollided = weapon.hasCollidedWithPlayer(player);

                //If weapon collided and player has not injuring
                if(weaponCollided && !player.isHasPlayerInjuring()){

                    //Hat to weapon to delete list
                    deleteWeapons.add(weapon);

                    //Player damage
                    player.takeDamage(weapon.getWeaponType().getDamageType());


                    //Notify the changes to the view
                    setChanged();
                    notifyObservers(new PlayerCollision(player.getPlayerDirection()));


                }
                //Else if the unit still has moves left to make
                else if (weapon.getWeaponRange() > 0) {
                    //move the weapon
                    weapon.moveWeapon(currentPlayerZone);

                    //Show weapon movement
                    setChanged();
                    notifyObservers(new Attack(playerSameZoneWeapons));
                }
                //weapon collided or weapon has finished the steps
                else{
                    //Add to the list of weapons to be removed
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

    /**
     * This method is triggered when you wish to initiate the player’s
     * attack action and carries out all the consequences thereof
     */
    public void playerAttack(){

        //If player has all lives throwWeapon will set to true, else false.
        boolean throwWeapon = player.getLifes() == 3.0;

        //Initialize the attack
        player.initializeAttackWithWeapon(player.getPlayerDirection(), throwWeapon);

        //Store the enemy that was hit and killed in the variable
        Enemy removeEnemy = player.killedEnemy(overworldMap);

        //if an enemy is dead
        if (removeEnemy != null){
            //remove enemy from current zone enemies list
            getOverworldMap().getZone(player.getPlayerMapPosition()).getEnemies().remove(removeEnemy);

            //Remove enemy from the view
            setChanged();
            notifyObservers(new EnemyDeath(removeEnemy));
        }


        //Create PlayerAttack event
        PlayerAttack attack =
                new PlayerAttack(
                        player.getPlayerWeapon(),
                        throwWeapon,
                        StartEndStatus.START
                );

        //View the attack movement
        setChanged();
        notifyObservers(attack);


    }

    /**
     * This function handles the movement and collisions of
     * the player’s weapon once it has been thrown
     */
    public void throwPlayerWeapon(){

        //player current zone
        Zone currentPlayerZone = overworldMap.getZone(getPlayer().getPlayerMapPosition());

        //next player weapon coordinates
        int nextX = player.getPlayerWeapon().getPosition().getX() + player.getPlayerWeapon().getWeaponDirection().getX();
        int nextY = player.getPlayerWeapon().getPosition().getY() + player.getPlayerWeapon().getWeaponDirection().getY();

        //next weapon coordinates
        Coordinates nextPlayerWeaponCoordinates = new Coordinates(
                nextX,
                nextY
        );


        //Store the enemy that was hitted and killed
        List<Enemy> killedEnemy = new ArrayList<>();

        //for each enemy in the current zone
        currentPlayerZone.getEnemies().forEach(
                (e)-> {

                    //if player weapon hit an enemy
                    if (player.getPlayerWeapon().getPosition().equals(e.getPosition())) {
                        System.out.println(e.getEnemyID());

                        //the enemy will lose life points
                        e.takeDamage(player.getPlayerWeapon().getAttackPoints());

                        //if enemy lost all lives
                        if (e.getLifePoints() <= 0) killedEnemy.add(e);

                        //The weapon throw ends
                        //player.endPlayerWeaponThrowing();

                        //Show weapon collision on the view
                        setChanged();
                        notifyObservers(new SwordThrowing(player.getPlayerWeapon().getWeaponDirection(), StartEndStatus.END));

                    }

                }

        );

        //If an enemy is killed
        if (!killedEnemy.isEmpty()){
            //remove killed enemy from the zone
            getOverworldMap().getZone(player.getPlayerMapPosition()).getEnemies().removeAll(killedEnemy);

            //Remove enemy from the view
            setChanged();
            notifyObservers(new EnemyDeath(killedEnemy.getFirst()));
        }


       //System.out.println(player.getPlayerWeapon().getPosition() + " " + nextPlayerWeaponCoordinates);

        /*
         If no enemy at the weapon’s current coordinates is hit, the next line of code is executed
         */

        //If, at the next coordinate, the weapon is within the zone
        if (nextX > -1 && nextX < Dimensions.ZONE_COLUMNS.get()  && nextY > -1 && nextY < Dimensions.ZONE_ROWS.get()){

            //Move the player weapon
            player.swordThrowing(nextPlayerWeaponCoordinates);

            //Show weapon movement
            setChanged();
            notifyObservers(new SwordThrowing(player.getPlayerWeapon().getWeaponDirection(), StartEndStatus.START));

        }
        //weapon collide
        else{
            //Show collision
            setChanged();
            notifyObservers(new SwordThrowing(player.getPlayerWeapon().getWeaponDirection(), StartEndStatus.END));
        }




    }




    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof PlayerCollision pc){
            if (!pc.isPlayerInjuring()){
                player.setHasPlayerInjuring(false);
            }
        }

        if (arg instanceof PlayerAttack){
            PlayerAttack pa = (PlayerAttack) arg;
            player.playerEndAttack();
            if(pa.isThrowWeapon()){
                player.setWeaponThrowing(true);
            }
        }

        if (arg instanceof SwordThrowing){
            SwordThrowing st = (SwordThrowing) arg;

            if(st.getStatus() == StartEndStatus.END){

                player.endPlayerWeaponThrowing();
            }


        }



    }
}
