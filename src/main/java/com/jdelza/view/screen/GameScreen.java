package com.jdelza.view.screen;

import com.jdelza.model.enums.Directions;
import com.jdelza.utils.Dimensions;
import com.jdelza.view.PlayerView;
import com.jdelza.view.overworld.Overworld;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GameScreen extends VBox {

    private final int gameScreenHeight;
    private final int gameScreenWidth;

    //Main game screen components
    private Pane overworld;        //This is the container of overwold class not Overwolrd classs
    private String inventory;

    //Player
    private PlayerView player;

    public GameScreen(Overworld overworld, PlayerView player) {
        this.gameScreenHeight = Dimensions.RENDER_SCREEN_HEIGHT.get();
        this.gameScreenWidth = Dimensions.RENDER_SCREEN_WIDTH.get();
        this.overworld = overworld;
        this.player = player;

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
                "-fx-border-color: green; " +
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
    public void movePlayer(Directions direction){
        switch (direction){
            case UP:    player.setTranslateY(player.getTranslateY()-player.getHeight());break;
            case DOWN:  player.setTranslateY(player.getTranslateY()+player.getHeight());break;
            case LEFT:  player.setTranslateX(player.getTranslateX()-player.getWidth());break;
            case RIGHT: player.setTranslateX(player.getTranslateX()+player.getWidth());break;
        }
    }
}
