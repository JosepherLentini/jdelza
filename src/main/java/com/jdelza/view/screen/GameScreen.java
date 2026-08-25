package com.jdelza.view.screen;

import com.jdelza.model.characters.Enemy;
import com.jdelza.model.characters.Player;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.events.PlayerMovement;
import com.jdelza.view.characters.EnemyView;
import com.jdelza.view.characters.PlayerView;
import com.jdelza.view.overworld.Overworld;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

public class GameScreen extends VBox {

    private final int gameScreenHeight = Dimensions.RENDER_SCREEN_HEIGHT.get();
    private final int gameScreenWidth = Dimensions.RENDER_SCREEN_WIDTH.get();

    //Main game screen components
    private Pane overworld;        //This is the container of overwold class (not Overwolrd class)
    private String inventory;

    //Player
    private PlayerView player;  //player view
    private Player playerModel; //Instance of the player in the game

    //Overworld
    private Pane overworldWrapper;

    //Animation control
    private boolean isZoneChanging;
    private boolean isPlayerMoving;

    //All enemies
    private Set<EnemyView> allEnemies;

    /**
     * GameScreen constructor
     * @param overworld         //current overworld instance
     * @param player            //current view player instance
     * @param playerModel       //current logic player
     */
    public GameScreen(Overworld overworld, PlayerView player, Player playerModel) {
        this.overworld = overworld;
        this.player = player;
        this.allEnemies = new HashSet<>(){};
        this.overworldWrapper = new Pane(overworld);

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


        this.setStyle(
                "-fx-border-color: yellow; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );

        overworldWrapper.setStyle(
                "-fx-border-color: violet; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
        );

        /*
        Pane t = new Pane();
        t.setPrefSize(100,100);
        t.setStyle(
                "-fx-border-color: yellow; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );
        t.setLayoutX(100);
        t.setLayoutY(100);



        t.toFront();

         */

        this.getChildren().addAll(p,overworldWrapper);


        //overworld.getChildren().add(new EnemyView(new Coordinates(7,7), new Coordinates(7,7)));
        //placeEnemies();


        Rectangle clip = new Rectangle(gameScreenWidth, gameScreenHeight);
        this.setClip(clip);

    }

    //Get methods
    public PlayerView getPlayer() {return player;}
    public Pane       getOverworld() {return overworld;}
    public boolean    isZoneChanging() {return isZoneChanging;}
    public boolean    isPlayerMoving() {return isPlayerMoving;}

    //Set methods
    public void setZoneChanging(boolean zoneChanging) {isZoneChanging = zoneChanging;}
    public void setPlayerMoving(Boolean playerMoving){isPlayerMoving = playerMoving;}


    //Enemies
    /**
     * This method allow to add new enemyView in to allEnemy set
     * @param e new enemy
     */
    public void addEnemy(EnemyView e){allEnemies.add(e);this.overworld.getChildren().add(e);}
    public void placeEnemies(){

        overworld.getChildren().forEach(n-> {
            if (n instanceof EnemyView){

                EnemyView ew = (EnemyView)n;
                int x = ew.getEnemyViewMapPosition().getX()*Dimensions.ZONE_COLUMNS.get()+ew.getEnemyViewZonePosition().getX();
                int y = ew.getEnemyViewMapPosition().getY()*Dimensions.ZONE_ROWS.get()+ew.getEnemyViewZonePosition().getY();

                ew.setTranslateX(ew.getTileWidth()*x);
                ew.setTranslateY(ew.getTileHeight()*y);
            }
        });

;
    }

    //Player
    /**
     * Player movement and zone changing
     * @param playerMovement is the player move event
     */
    public void movePlayer(PlayerMovement playerMovement) {
        //placeEnemies();
        Directions dir = playerMovement.getDirection();
        if (playerMovement.isZoneChanged()) {
            setZoneChanging(true);

            //Create traslate transition
            TranslateTransition zoneSwitchTransition = new TranslateTransition();

            TranslateTransition playerSwitchTransition = new TranslateTransition();
            //Add node to transition
            zoneSwitchTransition.setNode(overworld);
            playerSwitchTransition.setNode(player);

            //Combine two transition witch works at the same time
            ParallelTransition parallel = new ParallelTransition(
                    zoneSwitchTransition,playerSwitchTransition
            );

            switch (dir) {
                case UP:
                    //World translation and player movement in tandem with the world
                    zoneSwitchTransition.setToY(overworld.getTranslateY() + Dimensions.MAP_HEIGHT.get());
                    playerSwitchTransition.setToY((Dimensions.ZONE_ROWS.get() - 1) * player.getTileHeight());
                    break;

                case DOWN:
                    //World translation and player movement in tandem with the world
                    zoneSwitchTransition.setToY(overworld.getTranslateY() - Dimensions.MAP_HEIGHT.get());
                    playerSwitchTransition.setToY(0);
                    break;

                case LEFT:
                    //World translation and player movement in tandem with the world
                    zoneSwitchTransition.setToX(overworld.getTranslateX() + Dimensions.MAP_WIDTH.get());
                    playerSwitchTransition.setToX((Dimensions.ZONE_COLUMNS.get() - 1) * player.getTileWidth());
                    break;

                case RIGHT:
                    //World translation and player movement in tandem with the world
                    zoneSwitchTransition.setToX(overworld.getTranslateX() - Dimensions.MAP_WIDTH.get());
                    playerSwitchTransition.setToX(0);
                    break;
            }

            //Transition duration
            zoneSwitchTransition.setDuration(Duration.millis(1000));
            playerSwitchTransition.setDuration(Duration.millis(1000));

            //Start transition
            parallel.play();

            //When transition ends
            parallel.setOnFinished(e-> {setZoneChanging(false);});


        } else {
            setPlayerMoving(true);  //Player is mmoving

            //Create transition
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(player);

            switch (dir){
                //Move the player in the indicated direction; translate the player by a certain distance
                case UP:    transition.setByY(-player.getHeight());break;
                case DOWN:  transition.setByY(+player.getHeight());break;
                case LEFT:  transition.setByX(-player.getWidth());break;
                case RIGHT: transition.setByX(+player.getWidth());break;
            }

            //Setting transition duration
            transition.setDuration(Duration.millis(200));

            //Start transition
            transition.play();

            //When transition ends
            transition.setOnFinished(e-> setPlayerMoving(false));

            /*
            switch (dir){
                case UP:    player.setTranslateY(player.getTranslateY()-player.getHeight());break;
                case DOWN:  player.setTranslateY(player.getTranslateY()+player.getHeight());break;
                case LEFT:  player.setTranslateX(player.getTranslateX()-player.getWidth());break;
                case RIGHT: player.setTranslateX(player.getTranslateX()+player.getWidth());break;
            }
             */


            //playerModel.setMoving(false);




            }


        }




}
