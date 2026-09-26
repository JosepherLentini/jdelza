package com.jdelza.view.characters;

import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;


public class PlayerView extends CharacterView {


    private ImageView tileImageView;             //Player imageView
    boolean frame = false;                      //This is used to switch player movement image

    private Map<Directions, Image[]> walkingImage = new HashMap<>();    //Key: direction, Value: Image of direction

    private boolean injuring = false;

    //Direction
    private Directions playerDirection;

    public PlayerView() {

        for (Directions dir : Directions.values()) {

            String path = "com/jdelza/view/assets/player/";
                        //"file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/player/";
            Image[] images = new Image[2];

            for (int i = 1; i < 3; i++) {

                images[i - 1] = new Image(path + dir.name()+ "_" + i + ".png");

            }

            walkingImage.put(dir, images);

        }


        tileImageView = new ImageView();
        tileImageView.setImage(walkingImage.get(Directions.DOWN)[0]);

        tileImageView.setFitWidth(super.getTileWidth());
        tileImageView.setFitHeight(super.getTileHeight());
        tileImageView.setSmooth(false);

        this.getChildren().add(tileImageView);

        this.toFront();

    }


    //Get methods
    public Map<Directions, Image[]> getWalkingImage() {return walkingImage;}
    public boolean isInjuring() {return injuring;}

    //Set methods
    public void setInjuring(boolean injuring) {this.injuring = injuring;}
    public void setTileImageView(Image image){tileImageView.setImage(image);}

    /**
     * This method occurs when player hit with weapons or enemies
     */
    public void playerInjured(){

        if(frame)
            tileImageView.setImage(new Image("com/jdelza/view/assets/enemies/oktorock/OKTOROCK_BLUE_UP_0.png"));
        else
            tileImageView.setImage(new Image("com/jdelza/view/assets/enemies/oktorock/OKTOROCK_WEAPON.png"));

        frame = !frame;
    }


    /**
     * This method occur when player is moving. It is used to change image for animate the player.
     * @param direction
     */
    public void changeSprite(Directions direction){
        if (isInjuring()){
            playerInjured();
        }
        else{
            if(frame)
                tileImageView.setImage(walkingImage.get(direction)[0]);
            else
                tileImageView.setImage(walkingImage.get(direction)[1]);

            frame = !frame;

        }


    }






}
