package com.jdelza.model;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.model.game_data.GameDataStorage;
import com.jdelza.model.items.Item;
import com.jdelza.model.items.Rupie;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.model.world.*;
import com.jdelza.utils.enums.*;
import com.jdelza.utils.events.*;
import com.jdelza.utils.interfaces.Buyable;
import com.jdelza.utils.interfaces.Environment;
import com.jdelza.utils.interfaces.Storable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;


public class GameModel extends Observable {

    private final Player player;
    private GameMap currentMap;
    private final WorldMap overworld;
    private final GameDataStorage storage;


    public GameModel(Player player, GameDataStorage storage) throws IOException {
        this.player = player;
        this.storage = storage;

        this.overworld = storage.getSelectedPlayerData().getWorldMap();
        this.currentMap = overworld;


    }

    //Get methods
    public Player getPlayer() {return player;}
    public GameMap getCurrentMap() {return currentMap;}

    /**
     * This function calls the view to launch the game screen
     */
    public void startGame(){


        setChanged();
        notifyObservers("START_GAME");


    }

    public void movePlayer(Directions direction){
        player.setPlayerDirection(direction);

        Environment env = currentMap.getEnvironment(player, direction);


        if (currentMap.hasPlayerShouldReturnToOverworld(player) && currentMap.isPlayerAtTheBorderOfTheMap(direction, player)){
            returnToOverworld(direction);

        }
        else if(currentMap.hasPlayerShouldReturnToOverworld(player)){

            returnToOverworld(direction);



        }else if (env != null){
            enterEnvironment(env, direction);

        }
        else if(currentMap.shouldPlayerChangeZone(direction, player) || currentMap.shouldPlayerChangeDungeonZone(direction,player)){
            switchZone(direction);
        }
        else{
            playerWalk(direction);
        }

        playerTakeItem();
        playerBuyItem(direction);


    }



    /**
     * This function is called when player should come back to overworld
     */
    private void returnToOverworld(Directions direction){

            setChanged();

            notifyObservers(new EnterOverworld(overworld, direction));

            player.setPlayerMapPosition(overworld.getEntryPosition().getZoneCoordinates());
            player.setPosition(overworld.getEntryPosition().getCellCoordinates());

            this.currentMap = overworld;

    }

    /**
     * This function is called when player should go in to another environment
     * @param env next environment
     */
    private void enterEnvironment(Environment env, Directions direction){

        setChanged();

        //If player go from overworld to another environment
        if (currentMap instanceof WorldMap wm){
            //set player position before entry in an environment
            wm.setEntryPosition(new Position(player.getPlayerMapPosition(), player.getPosition()));
        }

        switch (env.getEnvironmentType()){
            case SHOP -> notifyObservers(new EnterTheShop((ShopMap) env, direction));
            case OVERWORLD -> notifyObservers(new EnterOverworld((WorldMap) env, direction));
            case DUNGEON -> notifyObservers(new EnterDungeon((DungeonMap)env, direction));
        }


        //this.currentMap = new ShopMap(new Coordinates(4,1));
        player.setPlayerMapPosition(env.getEntryPosition().getZoneCoordinates());
        player.setPosition(env.getEntryPosition().getCellCoordinates());


        this.currentMap = (GameMap) env;


    }

    /**
     * This function is called when player should change zone
     * @param direction next zone
     */
    private void switchZone(Directions direction){

        //Before zone changing, remove all items in the zone
        Arrays.stream(currentMap.getZone(player.getPlayerMapPosition()).getZone()).flatMap(Arrays::stream).forEach(t->{

            if(t.getItem() != null){
                Position currentItemPosition = new Position(player.getPlayerMapPosition(), t.getPosition());

                setChanged();
                notifyObservers(new DeleteItems(currentItemPosition));
            }

        });


        //Now thi zone can be changed
            currentMap.setZoneChanging(true);
            player.changeZone(direction, (Environment) currentMap);
            setChanged();
            //notifyObservers(new PlayerMovement(true, direction));
            notifyObservers(new ChangeZone(player.getPlayerMapPosition(),direction, StartEndStatus.START));
    }

    /*
    public void playerTakeItem(Directions direction){
        Zone currentZone = currentMap.getZone(new Coordinates(player.getPlayerMapPosition().getX(), player.getPlayerMapPosition().getY()));

        Item itemInTheZone = currentZone.itemInTheZoneCell(player, direction);

        System.out.println("item:" + itemInTheZone);

        if (itemInTheZone != null && currentMap instanceof ShopMap sm){
            player.takeItem(itemInTheZone);
            sm.removeItem();

            setChanged();
            notifyObservers(new PlayerTakeItem(itemInTheZone.getItemType(), StartEndStatus.START));

        }else if (itemInTheZone != null){

            player.takeItem(itemInTheZone);
            //currentZone.removeItem(player, direction);

            setChanged();
            notifyObservers(new PlayerTakeItem(itemInTheZone.getItemType(), StartEndStatus.START));
        }

    }

     */
    /*
    public void playerTakeItem(Directions direction){
        Zone currentZone = currentMap.getZone(new Coordinates(player.getPlayerMapPosition().getX(), player.getPlayerMapPosition().getY()));

        //System.out.println("item:" + itemInTheZone);

        Position currentPlayerPosition = new Position(getPlayer().getPlayerMapPosition(), getPlayer().getPosition());

        if(currentMap instanceof ShopMap sm){
            Item itemInTheZone = currentZone.itemInTheShopCell(player, direction);
            if (itemInTheZone != null){
                player.takeItem(itemInTheZone);
                sm.removeItem();

                setChanged();
                notifyObservers(new PlayerTakeItem(itemInTheZone.getItemType(), StartEndStatus.START, true));
            }
        }else {
            Item itemInTheSameCell = currentZone.itemInTheSamePlayerCell(player);
            if(itemInTheSameCell != null){

                player.takeItem(itemInTheSameCell);
                currentZone.removeItem(player);

               if (itemInTheSameCell instanceof Rupie){
                   setChanged();
                   notifyObservers(new PlayerTakeItem(itemInTheSameCell.getItemType(), player.getNumberOfRupies(), false, currentPlayerPosition));
               }else{
                   setChanged();
                   notifyObservers(new PlayerTakeItem(itemInTheSameCell.getItemType(), false, currentPlayerPosition));
               }



            }
        }



    }
    */


    public void playerTakeItem(){

        if(!(currentMap instanceof ShopMap)){

            Zone currentZone = currentMap.getZone(new Coordinates(player.getPlayerMapPosition().getX(), player.getPlayerMapPosition().getY()));

            //System.out.println("item:" + itemInTheZone);

            Position currentPlayerPosition = new Position(getPlayer().getPlayerMapPosition(), getPlayer().getPosition());

            Item itemInTheSameCell = currentZone.itemInTheSamePlayerCell(player);
            if (itemInTheSameCell != null) {

                player.takeItem(itemInTheSameCell);
                currentZone.removeItem(player);

                if (itemInTheSameCell instanceof Rupie) {
                    setChanged();
                    notifyObservers(new PlayerTakeItem(itemInTheSameCell.getItemType(), player.getNumberOfRupies(), false, currentPlayerPosition));
                } else {
                    setChanged();
                    notifyObservers(new PlayerTakeItem(itemInTheSameCell.getItemType(), false, currentPlayerPosition));
                }

            }
        }

    }

    public void playerBuyItem(Directions direction){

        Zone currentZone = currentMap.getZone(new Coordinates(player.getPlayerMapPosition().getX(), player.getPlayerMapPosition().getY()));

        if(currentMap instanceof ShopMap sm) {
            Item itemInTheZone = currentZone.itemInTheShopCell(player, direction);


            if (itemInTheZone != null && itemInTheZone.getItemType() == ItemType.SWORD) {
                player.takeItem(itemInTheZone);
                sm.removeItem();

                setChanged();
                notifyObservers(new PlayerTakeItem(itemInTheZone.getItemType(), StartEndStatus.START, true));

            }else if(itemInTheZone instanceof Buyable buyable){

                if(player.getNumberOfRupies() >= buyable.getCost()){

                    System.out.println(player.getNumberOfRupies()+ " - " + buyable.getCost());

                    //Decrements rupies
                    player.setNumberOfRupies(player.getNumberOfRupies()-buyable.getCost());

                    //remove item from shop
                    sm.removeItem();
                    player.setTakingItem(true);

                    //take item animation
                    setChanged();
                    notifyObservers(new PlayerTakeItem(itemInTheZone.getItemType(), StartEndStatus.START, true));

                    //Decrement rupies
                    setChanged();
                    notifyObservers(new BuyItemEvent(player.getNumberOfRupies(), itemInTheZone.getItemType()));


                    //Do item specific action
                    switch (itemInTheZone.getItemType()){
                        case LIFE -> {
                            player.incrementsLeftLives(1.0);

                            //view incremented lives
                            setChanged();
                            notifyObservers(new IncrementItems(itemInTheZone.getItemType(), player.getTotalLives(), player.getLivesLeft()));
                        }
                    }

                }

            }


        }
    }

    /**
     * The player moves to the next square according to the direction given in the input
     * @param direction direction pressed
     */
    private void playerWalk(Directions direction) {

        if(!currentMap.isZoneChanging()){
            Zone currentZone = currentMap.getZone(player.getPlayerMapPosition());


            Coordinates nextCoordinates = new Coordinates(
                    player.getPosition().getX() + direction.getX(),
                    player.getPosition().getY() + direction.getY()
            );

            if (currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable() && !player.isTakingItem()) {

                boolean collide = player.hasPlayerCollidedWithEnemy(currentMap.getZone(player.getPlayerMapPosition()), direction);

                if (collide && !player.isHasPlayerInjuring()) {
                    //Player has collided
                    player.takeDamage(DamageType.LIGHT);

                    setChanged();
                    notifyObservers(new PlayerTakeDamage(direction, player.getTotalLives(), player.getLivesLeft()));
                }

                player.move(direction);

                setChanged();
                notifyObservers(new PlayerMovement(direction));

            }

            if (!currentZone.getZone()[nextCoordinates.getY()][nextCoordinates.getX()].isWalkable()){

                setChanged();
                notifyObservers(new WalkMovement(direction));
            }
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
                            notifyObservers(new PlayerTakeDamage(player.getPlayerDirection(), player.getTotalLives(), player.getLivesLeft()));
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

                //Increment the player score
                player.setScore(player.getScore()+1);

                getCurrentMap().getZone(player.getPlayerMapPosition()).getEnemies().removeAll(removeEnemy);

                Position deathEnemyPosition = new Position(
                        removeEnemy.getFirst().getEnemyMapCoordinates(),
                        removeEnemy.getFirst().getPosition()
                );

                currentMap.addRupies(
                        removeEnemy.getFirst().getAmountOfRupies(),
                        deathEnemyPosition
                );

                setChanged();
                notifyObservers(new EnemyDeath(removeEnemy.getFirst(), player.getScore(),deathEnemyPosition));
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
                    notifyObservers(new PlayerTakeDamage(player.getPlayerDirection(), player.getTotalLives(), player.getLivesLeft()));


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

       if (player.getPlayerWeapon() != null && !player.isAttacking() && !player.isWeaponThrowing()){

           player.initializeAttackWithWeapon(player.getPlayerDirection());

           //Store the enemy that was hit and killed in the variable
           Enemy removeEnemy = player.killedEnemy(currentMap);

           //if an enemy is dead
           if (removeEnemy != null){

               Position deathEnemyPosition =  new Position(
                       removeEnemy.getEnemyMapCoordinates(),
                       removeEnemy.getPosition()
               );

               //System.out.println(deathEnemyPosition);

               currentMap.addRupies(
                       removeEnemy.getAmountOfRupies(),
                       deathEnemyPosition
               );


               //remove enemy from current zone enemies list
               getCurrentMap().getZone(player.getPlayerMapPosition()).getEnemies().remove(removeEnemy);

               //Increment the player score
               player.setScore(player.getScore()+1);

               //Remove enemy from the view
               setChanged();
               notifyObservers(new EnemyDeath(removeEnemy, player.getScore(),deathEnemyPosition ));
           }

           PlayerAttack attack = new PlayerAttack(
                   player.getPlayerWeapon(),
                   player.getTotalLives() == player.getLivesLeft(),
                   StartEndStatus.START
           );

           setChanged();
           notifyObservers(attack);

       }

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
            Coordinates nextPlayerWeaponCoordinates = new Coordinates(nextX, nextY);

            //Store the enemy that was hitted and killed
            List<Enemy> killedEnemy = new ArrayList<>();
            //System.out.println(player.getPlayerWeapon().getPosition());

            //for each enemy in the current zone
            currentPlayerZone.getEnemies().forEach(
                    (e)-> {

                        //if player weapon hit an enemy
                        if (player.getPlayerWeapon().getPosition().equals(e.getPosition())) {

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

                Position deathEnemyPosition =      new Position(
                        killedEnemy.getFirst().getEnemyMapCoordinates(),
                        killedEnemy.getFirst().getPosition()
                );



                currentMap.addRupies(
                        killedEnemy.getFirst().getAmountOfRupies(),
                        deathEnemyPosition
                );

                //increment player score
                player.setScore(player.getScore()+1);

                //Remove enemy from the view
                setChanged();
                notifyObservers(new EnemyDeath(killedEnemy.getFirst(), player.getScore(), deathEnemyPosition));
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


}
