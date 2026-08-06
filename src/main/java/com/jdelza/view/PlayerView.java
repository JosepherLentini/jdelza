package com.jdelza.view;

import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


public class PlayerView extends Pane {

    private final int playerWidth = Dimensions.TILE_WIDTH.get();        //Player height
    private final int playerHeight = Dimensions.TILE_HEIGT.get();       //Player width


    private ImageView imwPDI;
    boolean frame = false;

    private Map<Directions, Image[]> walkingImage = new HashMap<>();    //Key: direction, Value: Image of direction

    public PlayerView() {



        this.setPrefSize(72 , 45);
        this.setMaxSize(72, 45);
        this.setMinSize(72,45);


        for (Directions dir : Directions.values()) {

            String path = "file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player/";
            Image[] images = new Image[2];

            for (int i = 1; i < 3; i++) {

                images[i - 1] = new Image(path + dir.name()+ "_" + i + ".png");

            }

            walkingImage.put(dir, images);

        }


        imwPDI = new ImageView();
        imwPDI.setImage(walkingImage.get(Directions.DOWN)[0]);

        imwPDI.setFitWidth(playerWidth);
        imwPDI.setFitHeight(playerHeight);
        imwPDI.setSmooth(false);

        this.getChildren().add(imwPDI);

        this.toFront();

    }


    //Get methods
    public int getPlayerWidth() {return playerWidth;}
    public int getPlayerHeight() {return playerHeight;}


    public void changeSprite(Directions direction){
        if(frame)
            imwPDI.setImage(walkingImage.get(direction)[0]);
        else
            imwPDI.setImage(walkingImage.get(direction)[1]);

        frame = !frame;

    }
}
