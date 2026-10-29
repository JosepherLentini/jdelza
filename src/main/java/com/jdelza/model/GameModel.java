package com.jdelza.model;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.model.world.*;
import com.jdelza.utils.enums.*;
import com.jdelza.utils.events.*;
import com.jdelza.utils.interfaces.Environment;
import com.jdelza.view.overworld.Overworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecations")
public class GameModel extends Observable implements Observer {

    private Player player;
    private GameMap currentMap;
    private WorldMap overworld;



    public GameModel(Player player) throws IOException {
        this.player = player;
        this.overworld = new WorldMap();
        this.currentMap = overworld;


    }

    //Get methods
    public Player getPlayer() {return player;}
    public GameMap getCurrentMap() {return currentMap;}

    /**
     * This function calls the view to launch the game screen
     */
    public void startGame(){setChanged();notifyObservers("START_GAME");}

    /*
    //Player actions
    public void movePlayer(Directions direction){


        //System.out.println(currentMap.getZone(player.getPlayerMapPosition()));


        if (player.isPlayerAtTheBorderOfTheMap(direction, currentMap) && player.hasPlayerShouldReturnToOverworld(currentMap, direction)){


            setChanged();
            notifyObservers(new SwitchEnvironment(player.getOverworldReturnPoint(), EnvironmentType.OVERWORLD));

            player.setPlayerMapPosition(player.getOverworldReturnPoint().getMapCoordinates());
            player.setPosition(player.getOverworldReturnPoint().getZoneCoordinates());

            this.currentMap = overworld;

        } else if (!player.isPlayerAtTheBorderOfTheMap(direction, currentMap)){

            if (player.shouldPlayerChangeZone(direction)){
                player.changeZone(direction);
                setChanged();
                notifyObservers(new PlayerMovement(true,direction)  );
            }else{

                Zone currentZone = currentMap.getZone(player.getPlayerMapPosition());

                Environment env = player.getEnvironment(currentMap.getZone(player.getPlayerMapPosition()), direction);

                Coordinates nextCoordinates = new Coordinates(
                        player.getPosition().getX() + direction.getX(),
                        player.getPosition().getY() + direction.getY()
                );

                if (env != null){
                    setChanged();
                    notifyObservers(new SwitchEnvironment(env.getEntryPortal(), env.getEnvironmentType()));

                    if (currentMap instanceof WorldMap){

                        player.setOverworldReturnPoint(new Portal(player.getPlayerMapPosition(), player.getPosition()));
                    }

                    //this.currentMap = new ShopMap(new Coordinates(4,1));
                    player.setPlayerMapPosition(env.getEntryPortal().getMapCoordinates());
                    player.setPosition(env.getEntryPortal().getZoneCoordinates());

                    this.currentMap = (GameMap) env;



                }


                else if ( currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable()){

                    boolean collide = player.hasPlayerCollidedWithEnemy(currentMap.getZone(player.getPlayerMapPosition()), direction);

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

        System.out.println(player.getPlayerMapPosition() + " " + player.getPosition() + " " + player.getPlayerDirection());

    }

     */

    public void movePlayer(Directions direction){
        Environment env = player.getEnvironment(currentMap.getZone(player.getPlayerMapPosition()), direction);



        //Start take item part




        //End take item part



        if (player.hasPlayerShouldReturnToOverworld(currentMap, direction) && player.isPlayerAtTheBorderOfTheMap(direction, currentMap)){
                returnToOverworld();

        }else if (env != null){
            enterEnvironment(env);
        }
        else if(player.shouldPlayerChangeZone(direction)){
            switchZone(direction);
        }
        else{
            playerWalk(direction);
        }
        player.takeItem(currentMap, direction);


    }

    /**
     * This function is called when player should come back to overworld
     */
    private void returnToOverworld(){
        System.out.println("usciamo va!");
            setChanged();

            notifyObservers(new EnterOverworld(overworld.getEntryPosition()));

            player.setPlayerMapPosition(overworld.getEntryPosition().getZoneCoordinates());
            player.setPosition(overworld.getEntryPosition().getCellCoordinates());

            this.currentMap = overworld;

    }

    /**
     * This function is called when player should go in to another environment
     * @param env
     */
    private void enterEnvironment(Environment env){
        setChanged();

        //If player go from overworld to another environment
        if (currentMap instanceof WorldMap wm){
            //set player position before entry in an environment
            wm.setEntryPosition(new Position(player.getPlayerMapPosition(), player.getPosition()));
        }

        switch (env.getEnvironmentType()){
            case SHOP -> notifyObservers(new EnterTheShop((ShopMap) env));
            case OVERWORLD -> {notifyObservers(new EnterOverworld(env.getEntryPosition()));}
        }

        /*
        if (currentMap instanceof WorldMap) {

            player.setOverworldReturnPoint(new Position(player.getPlayerMapPosition(), player.getPosition()));
        }

         */

        //this.currentMap = new ShopMap(new Coordinates(4,1));
        player.setPlayerMapPosition(env.getEntryPosition().getZoneCoordinates());
        player.setPosition(env.getEntryPosition().getCellCoordinates());

        this.currentMap = (GameMap) env;
    }

    /**
     * This function is called when player should change zone
     * @param direction
     */
    private void switchZone(Directions direction){

            player.changeZone(direction);
            setChanged();
            notifyObservers(new PlayerMovement(true, direction));

    }

    /**
     * The player moves to the next square according to the direction given in the input
     * @param direction direction pressed
     */
    private void playerWalk(Directions direction) {
        Zone currentZone = currentMap.getZone(player.getPlayerMapPosition());
        Coordinates nextCoordinates = new Coordinates(
                player.getPosition().getX() + direction.getX(),
                player.getPosition().getY() + direction.getY()
        );

        if (currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable()) {

            boolean collide = player.hasPlayerCollidedWithEnemy(currentMap.getZone(player.getPlayerMapPosition()), direction);

            if (collide) {
                //Player has collided
                player.takeDamage(DamageType.LIGHT);

                setChanged();
                notifyObservers(new PlayerCollision(direction));
            }

            player.move(direction);


            setChanged();
            notifyObservers(new PlayerMovement(false, direction));
        }


    }
    
    /**
     * This function controls the enemy’s attack and movement actions
     */
    public void enemyActions(){
        //Set the same zone of the player
        Zone currentPlayerZone = currentMap.getZone(getPlayer().getPlayerMapPosition());

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

                getCurrentMap().getZone(player.getPlayerMapPosition()).getEnemies().removeAll(removeEnemy);

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
        Zone currentPlayerZone = currentMap.getZone(getPlayer().getPlayerMapPosition());

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
                currentMap.getZone(getPlayer().getPlayerMapPosition()).setWeapons(playerSameZoneWeapons);

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
                player.initializeAttackWithWeapon(player.getPlayerDirection());

                //Store the enemy that was hit and killed in the variable
                Enemy removeEnemy = player.killedEnemy(currentMap);

                //if an enemy is dead
                if (removeEnemy != null){
                    //remove enemy from current zone enemies list
                    getCurrentMap().getZone(player.getPlayerMapPosition()).getEnemies().remove(removeEnemy);

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
        Zone currentPlayerZone = currentMap.getZone(getPlayer().getPlayerMapPosition());

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
        System.out.println(player.getPlayerWeapon().getPosition());

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
            getCurrentMap().getZone(player.getPlayerMapPosition()).getEnemies().removeAll(killedEnemy);

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
