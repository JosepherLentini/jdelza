package com.jdelza.view.screen;

import com.jdelza.utils.Dimensions;
import com.jdelza.view.overworld.Overworld;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GameScreen extends VBox {

    private final int gameScreenHeight;
    private final int gameScreenWidth;

    private Pane overworld;        //This is the container of overwold class not Overwolrd classs
    private String inventory;

    public GameScreen(Overworld overworld) {
        this.gameScreenHeight = Dimensions.RENDER_SCREEN_HEIGHT.getInt();
        this.gameScreenWidth = Dimensions.RENDER_SCREEN_WIDTH.getInt();
        this.overworld = overworld;

        //Rectancle clip
        Rectangle overwoldClip = new Rectangle(gameScreenWidth, Dimensions.MAP_HEIGHT.get());


        //OverwoldWrapper contains overwold
        Pane overworldWrapper = new Pane(overworld);
        overworldWrapper.setPrefSize(gameScreenWidth, Dimensions.MAP_HEIGHT.get());
        overworldWrapper.setMinSize(gameScreenWidth, Dimensions.MAP_HEIGHT.get());
        overworldWrapper.setMaxSize(gameScreenWidth, Dimensions.MAP_HEIGHT.get());
        overworldWrapper.setClip(overwoldClip);


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
                "-fx-border-color: blue; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
        );



        this.getChildren().addAll(p,overworldWrapper);
        Rectangle clip = new Rectangle(gameScreenWidth, gameScreenHeight);
        this.setClip(clip);






    }
}
