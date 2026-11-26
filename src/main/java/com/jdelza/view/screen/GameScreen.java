package com.jdelza.view.screen;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Position;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.*;
import com.jdelza.utils.events.PlayerMovement;
import com.jdelza.view.characters.EnemyView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.characters.TektiteView;
import com.jdelza.view.items_objects.ItemView;
import com.jdelza.view.status.StatusBar;
import com.jdelza.view.weapons.RockView;
import com.jdelza.view.weapons.WeaponView;
import com.jdelza.view.overworld.Overworld;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameScreen extends VBox {

    private final int gameScreenHeight = Dimensions.RENDER_SCREEN_HEIGHT.get();
    private final int gameScreenWidth = Dimensions.RENDER_SCREEN_WIDTH.get();

    //Main game screen components
    private Overworld overworld;        //This is the container of overwold class (not Overwolrd class)
    private StatusBar statusBar;

    private Pane currentEnvironment;

    //Player
    private final PlayerView player;  //player view


    //Overworld
    private final Pane overworldWrapper;

    //Animation control
    private boolean isZoneChanging;
    private boolean isPlayerMoving;



    /**
     * GameScreen constructor
     *
     * @param overworld   //current overworld instance
     * @param player      //current view player instance
     */
    public GameScreen(Overworld overworld, PlayerView player) {
        this.overworld = overworld;
        this.player = player;
        this.statusBar = new StatusBar();

        this.overworldWrapper = new Pane(overworld);

        this.currentEnvironment = overworld;

        this.isZoneChanging = false;


        // IMPEDISCE AL VBOX DI ESPANDERSI OLTRE LE SUE DIMENSIONI DESIDERATE
        this.setMinSize(gameScreenWidth, gameScreenHeight);
        this.setPrefSize(gameScreenWidth, gameScreenHeight);
        this.setMaxSize(gameScreenWidth, gameScreenHeight);

        //LEGGE GLI EVENTI PRIMA DEL COMPONENTE WRAPPER
        this.setFocusTraversable(true);
        this.requestFocus();

        //Rectancle clip
        Rectangle overwoldClip = new Rectangle(gameScreenWidth, Dimensions.MAP_HEIGHT.get());


        //OverwoldWrapper contains overwold

        overworldWrapper.setPrefSize(gameScreenWidth, Dimensions.MAP_HEIGHT.get());
        overworldWrapper.setMinSize(gameScreenWidth, Dimensions.MAP_HEIGHT.get());
        overworldWrapper.setMaxSize(gameScreenWidth, Dimensions.MAP_HEIGHT.get());
        overworldWrapper.setClip(overwoldClip);
        overworldWrapper.getChildren().add(player);

        Pane p = new Pane();

        p.setPrefSize(gameScreenWidth, 208);

        //If you set minimum and maximum dimensions, the component will not adapt to its parent
        p.setMinSize(gameScreenWidth, 208);
        p.setMaxSize(gameScreenWidth, 208);


        overworldWrapper.setStyle(

                "-fx-border-color: blue; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
                +"-fx-background-color:red;"
        );


        this.getChildren().addAll(this.statusBar, overworldWrapper);
        //this.getChildren().addAll(p, new ShopScreen(player));


        Rectangle clip = new Rectangle(gameScreenWidth, gameScreenHeight);
        this.setClip(clip);

    }

    //Get methods
    public PlayerView getPlayer() {
        return player;
    }
    public Overworld getOverworld() {
        return overworld;
    }
    public Pane getOverworldWrapper() {
        return overworldWrapper;
    }
    public Pane getCurrentEnvironment() {return currentEnvironment;}
    public StatusBar getStatusBar() {return statusBar;}

    //Set methods
    public void setZoneChanging(boolean zoneChanging) {
        isZoneChanging = zoneChanging;
    }
    public void setPlayerMoving(Boolean playerMoving) {
        isPlayerMoving = playerMoving;
    }
    public Set<EnemyView> getAllEnemies() {
        return overworld.getChildren().stream().filter(n -> n instanceof EnemyView).map(n -> (EnemyView) n).collect(Collectors.toSet());
    }

    public void setCurrentEnvironment(Pane currentEnvironment) {
        this.currentEnvironment = currentEnvironment;
    }

    //Enemies
    public void addAndPlaceEnemy(EnemyView e, Coordinates modelEnemyMapCoordinates, Coordinates modelEnemyZoneCoordinates) {

        int x = modelEnemyMapCoordinates.getX() * Dimensions.ZONE_COLUMNS.get() + modelEnemyZoneCoordinates.getX();
        int y = modelEnemyMapCoordinates.getY() * Dimensions.ZONE_ROWS.get() + modelEnemyZoneCoordinates.getY();

        int newX = e.getTileWidth() * x;
        int newY = e.getTileHeight() * y;


        e.setTranslateX(newX);
        e.setTranslateY(newY);

        e.setPreviousPosition(newX, newY);


        this.overworld.getChildren().add(e);

    }

    //Player

    /**
     * Player movement and zone changing
     *
     * @param playerMovement is the player move event
     */
    public void movePlayer(PlayerMovement playerMovement) {

        Directions dir = playerMovement.getDirection();

            setPlayerMoving(true);  //Player is moving
            getPlayer().setPlayerDirection(dir);
            //Create transition
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(player);
            player.changeSprite(playerMovement.getDirection());

            switch (dir) {
                //Move the player in the indicated direction; translate the player by a certain distance
                case UP:
                    transition.setByY(-player.getHeight());
                    break;
                case DOWN:
                    transition.setByY(+player.getHeight());
                    break;
                case LEFT:
                    transition.setByX(-player.getWidth());
                    break;
                case RIGHT:
                    transition.setByX(+player.getWidth());
                    break;
            }

            //Setting transition duration
            transition.setDuration(Duration.millis(160));

            //Start transition
            transition.play();

            //When transition ends
            transition.setOnFinished(e -> setPlayerMoving(false));

    }


    public void moveEnemies(List<Enemy> enemies){

      List<String> ids = enemies.stream().map(e-> e.getEnemyID()).collect(Collectors.toCollection(ArrayList::new));

      overworld.getChildren().stream().forEach(node-> {
          if (node instanceof EnemyView){
              EnemyView ew = (EnemyView) node;


              if (ids.contains(ew.getEnemyViewID())){


                  //System.out.println(ew);
                  Enemy enemy = enemies.stream().reduce((e,f)-> e.getEnemyID().equals(ew.getEnemyViewID())  ? e : f).get();

                  if (!(ew.getClass() == TektiteView.class))ew.setEnemyDirection(enemy.getEnemyDirection());

                  int x = enemy.getEnemyMapCoordinates().getX()*Dimensions.ZONE_COLUMNS.get()+enemy.getPosition().getX();
                  int y = enemy.getEnemyMapCoordinates().getY()*Dimensions.ZONE_ROWS.get()+enemy.getPosition().getY();

                  x = ew.getTileWidth()*x;
                  y = ew.getTileHeight()*y;

                  double newX = x-ew.getTranslateX();
                  double newY = y-ew.getTranslateY();

                  if (ew.getClass() == TektiteView.class){
                      TektiteView tk = (TektiteView)ew;

                      //Transition creation
                      TranslateTransition transition = new TranslateTransition();

                      transition.setDuration(Duration.millis(300.0));
                      transition.setByX(newX);
                      transition.setByY(newY);
                      transition.setNode(tk);

                      //Start transition
                      transition.play();


                  }
                  else{
                      TranslateTransition transition = new TranslateTransition();
                      transition.setNode(ew);

                      transition.setByX(newX);
                      transition.setByY(newY);

                      //Setting transition duration
                      transition.setDuration(Duration.millis(250));

                      //Start transition
                      transition.play();
                  }


              }

          }
      });



  }


    public void addWeapon(Weapon weapon){

      WeaponView weaponView = null;

      switch (weapon.getWeaponType()){
          case ROCK -> weaponView  = new RockView(weapon.getWeaponID());
      }

      int x = weapon.getWeaponMapPosition().getX()*Dimensions.ZONE_COLUMNS.get()+weapon.getPosition().getX();
      int y = weapon.getWeaponMapPosition().getY()*Dimensions.ZONE_ROWS.get()+weapon.getPosition().getY();

      weaponView.setTranslateX(Dimensions.TILE_WIDTH.get()*x);
      weaponView.setTranslateY(Dimensions.TILE_HEIGT.get()*y);

      weaponView.setWeaponDirection(weapon.getWeaponDirection());
      this.overworld.getChildren().add(weaponView);
  }

    public void moveWeapons(List<Weapon> weapons) {


      if (!weapons.isEmpty()) {
          overworld.getChildren().forEach(node -> {
              if (node instanceof WeaponView wv) {

                  // Find the weapon with the same ID directly in the list
                  weapons.stream()
                          .filter(w -> w.getWeaponID().equals(wv.getWeaponID()))
                          .findFirst()
                          .ifPresent(updatedWeapon -> {

                              // Create transition
                              TranslateTransition transition = new TranslateTransition(Duration.millis(100), wv);

                              //Apply the changes
                              switch (wv.getWeaponDirection()) {
                                  case UP -> transition.setByY(-Dimensions.TILE_HEIGT.get());
                                  case DOWN -> transition.setByY(Dimensions.TILE_HEIGT.get());
                                  case LEFT -> transition.setByX(-Dimensions.TILE_WIDTH.get());
                                  case RIGHT -> transition.setByX(Dimensions.TILE_WIDTH.get());
                              }

                              //Play the transition
                              transition.play();
                          });
              }
          });
      }


  }

  /**
   * This function allows you to remove the required weapons
   * @param weapons      weapons from model
   */
    public void removeEnemyWeapon(List<Weapon> weapons){

        //list of IDs from the model’s weapons to be removed
        List<String> removeIDs = weapons.stream().map(Weapon::getWeaponID).toList();

        //list of WeaponView to be removed
        List<WeaponView> removeWeaponViews = new ArrayList<>();

        //Find the weapons to be removed by their ID
        overworld.getChildren().forEach(node-> {
            //cast node into a weapon
            if (node instanceof WeaponView) {
                WeaponView w = (WeaponView)node;
                //Add to the list of enemies to be removed
                if (removeIDs.contains(w.getWeaponID())){
                    removeWeaponViews.add(w);
                }
            }
        });

        // if list of enemies to be removed in not empty, remove the weapons contained
        if (!removeWeaponViews.isEmpty()){overworld.getChildren().removeAll(removeWeaponViews);}
    }

    /**
     * It takes the enemy as input from the model that is to be removed and removes it from the view
     * @param deathEnemy       enemy from model
     */
    public void removeEnemies(Enemy deathEnemy){

        //Find the corresponding enemy by ID
        EnemyView deadEnemyView = overworld.getChildren().stream()

                .filter(n -> n instanceof EnemyView)               //filter by enemy in the view
                .map(n -> (EnemyView) n)                            //Cast it into an EnemyView
                .filter(ne -> ne.getEnemyViewID().equals(deathEnemy.getEnemyID()))  //filter by id
                .findFirst()
                .orElse(null);

        //if enemy from model and EnemyView aren't null
        if(deathEnemy != null && deadEnemyView != null){
            //Set enemy status to dead
            deadEnemyView.setDead(true);
            //Call enemy death animation
            deadEnemyView.enemyDeath((Overworld) overworld);

        }


    }



    public void enterOverworld(Position position, Directions direction){

        getOverworldWrapper().getChildren().remove(getCurrentEnvironment());
        getOverworldWrapper().getChildren().add(getOverworld());
        setCurrentEnvironment(getOverworld());

        overworld.changeOverWorldPosition(position);
        getOverworldWrapper().getChildren().remove(player);
        player.playerChangePosition(position);
        getOverworldWrapper().getChildren().add(player);

        player.changePlayerDirection(direction);

    }

    public void enterTheShopView(Position playerEntryPosition, NPCType npcType, ItemType itemType, Directions direction, int itemCost){

        Pane newEnvironment = new ShopScreen(npcType, itemType);

        ShopScreen env = (ShopScreen)newEnvironment;
        env.drawCost(itemCost);
        getOverworldWrapper().getChildren().remove(getOverworld());
        getOverworldWrapper().getChildren().add(newEnvironment);
        setCurrentEnvironment(newEnvironment);

        overworldWrapper.getChildren().remove(player);
        player.playerChangePosition(playerEntryPosition);
        overworldWrapper.getChildren().add(player);

        player.changePlayerDirection(direction);
    }


    public void enterDungeon(Position playerEntryPosition, Level dungeonLevel, Directions direction){

        Pane newEnvironment = new DungeonScreen(dungeonLevel);

        getOverworldWrapper().getChildren().remove(getOverworld());
        getOverworldWrapper().getChildren().add(newEnvironment);
        setCurrentEnvironment(newEnvironment);

        currentEnvironment.setTranslateX(-playerEntryPosition.getZoneCoordinates().getX()*Dimensions.MAP_WIDTH.get());
        currentEnvironment.setTranslateY(-playerEntryPosition.getZoneCoordinates().getY()*Dimensions.MAP_HEIGHT.get());


        overworldWrapper.getChildren().remove(player);
        player.playerChangePosition(playerEntryPosition);
        overworldWrapper.getChildren().add(player);

        player.changePlayerDirection(direction);

    }


    public void addItemInToTheMap(Position position, ItemType itemType){

        ItemView itemView = new ItemView(itemType);

        int x = position.getZoneCoordinates().getX() * Dimensions.MAP_COLUMNS.get();
        int y = position.getZoneCoordinates().getY() * Dimensions.MAP_ROWS.get();


        itemView.setLayoutY(position.getCellCoordinates().getY()*Dimensions.TILE_HEIGT.get());
        itemView.setLayoutX(position.getCellCoordinates().getX()*Dimensions.TILE_WIDTH.get());

        overworldWrapper.getChildren().add(itemView);
        itemView.toFront();
    }


    public void deleteItem(Position position){

            List<ItemView> items = new ArrayList<>();

            if (position != null) {
                int y = position.getCellCoordinates().getY()*Dimensions.TILE_HEIGT.get();
                int x = position.getCellCoordinates().getX()*Dimensions.TILE_WIDTH.get();

                overworldWrapper.getChildren().stream().filter(
                        node -> node.getLayoutX() == x && node.getLayoutY() == y && node instanceof ItemView

                ).map(
                        node -> (ItemView)node
                ).forEach(
                        items::add
                );
            }

            overworldWrapper.getChildren().removeAll(items);
    }



}
