package com.jdelza.view;

import com.jdelza.model.enums.Directions;
import com.jdelza.utils.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


public class PlayerView extends Pane {

    private int playerWidth;
    private int playerHeight;

    private Map<Directions, ImageView> playerDirectionImage;

    public PlayerView() {

        playerWidth = Dimensions.TILE_WIDTH.get();
        playerHeight = Dimensions.TILE_HEIGT.get();
        playerDirectionImage = new HashMap<>();

        this.setPrefSize(72 , 45);
        this.setMaxSize(72, 45);
        this.setMinSize(72,45);

        Image playerDirectionImage = new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player/row-1-column-1.png");
        ImageView imwPDI = new ImageView(playerDirectionImage);

        imwPDI.setFitWidth(playerWidth);
        imwPDI.setFitHeight(playerHeight);
        imwPDI.setSmooth(false);

        this.getChildren().add(imwPDI);



        this.setStyle(
                    "-fx-border-color: brown; " +
                            "-fx-border-style: solid; " +
                            "-fx-border-width: 3px; "

        );

        this.toFront();

    }


    //Get methods
    public int getPlayerWidth() {
        return playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }
}
