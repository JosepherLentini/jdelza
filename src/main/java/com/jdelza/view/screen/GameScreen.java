package com.jdelza.view.screen;

import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.events.PlayerMovement;
import com.jdelza.view.PlayerView;
import com.jdelza.view.overworld.Overworld;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GameScreen extends VBox {

    private final int gameScreenHeight = Dimensions.RENDER_SCREEN_HEIGHT.get();
    private final int gameScreenWidth = Dimensions.RENDER_SCREEN_WIDTH.get();

    //Main game screen components
    private Pane overworld;        //This is the container of overwold class not Overwolrd class
    private String inventory;

    //Player
    private PlayerView player;

    public GameScreen(Overworld overworld, PlayerView player) {
        this.overworld = overworld;
        this.player = player;

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
        Pane overworldWrapper = new Pane(overworld);
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


        this.getChildren().addAll(p,overworldWrapper);
        Rectangle clip = new Rectangle(gameScreenWidth, gameScreenHeight);
        this.setClip(clip);

    }

    //Get methods
    public PlayerView getPlayer() {
        return player;
    }

    public Pane getOverworld() {
        return overworld;
    }

    //Player
    public void movePlayer(PlayerMovement playerMovement){

        if (playerMovement.isZoneChanged()){

            switch (playerMovement.getDirection()){
                case UP:    {

                    overworld.setLayoutY(overworld.getLayoutY() + Dimensions.MAP_HEIGHT.get());
                    player.setTranslateY((Dimensions.ZONE_ROWS.get()-1)* player.getHeight());

                }; break;
                case DOWN: {

                    overworld.setLayoutY(overworld.getLayoutY() - Dimensions.MAP_HEIGHT.get());
                    player.setTranslateY(0);

                }; break;
                case LEFT: {
                    overworld.setLayoutX(overworld.getLayoutX() + Dimensions.MAP_WIDTH.get());
                    player.setTranslateX((Dimensions.ZONE_COLUMNS.get()-1)*player.getPlayerWidth());

                }; break;


                case RIGHT:{
                    overworld.setLayoutX(overworld.getLayoutX() - Dimensions.MAP_WIDTH.get());
                    player.setTranslateX(0);

                }; break;
            }

        }
        else{
            switch (playerMovement.getDirection()){
                case UP:    player.setTranslateY(player.getTranslateY()-player.getHeight());break;
                case DOWN:  player.setTranslateY(player.getTranslateY()+player.getHeight());break;
                case LEFT:  player.setTranslateX(player.getTranslateX()-player.getWidth());break;
                case RIGHT: player.setTranslateX(player.getTranslateX()+player.getWidth());break;
            }
        }



    }




}
