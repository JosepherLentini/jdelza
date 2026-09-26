package com.jdelza.view.characters;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Directions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OctorockView extends EnemyView{

    private final Map<Directions, Image[]> walkingImage = new HashMap<>();


    public OctorockView(Coordinates enemyViewMapPosition, Coordinates enemyViewZonePosition) {
        super(enemyViewMapPosition, enemyViewZonePosition);

        //Inizialize enemy RANDOM direction
        Random random = new Random();
        int directionIndex = random.nextInt(4);
        this.enemyDirection = Directions.values()[directionIndex];


        for (Directions dir : Directions.values()) {

            String path = "file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/oktorok/OKTOROCK_BLUE_";
            //"file:///"C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/oktorok/OKTOROCK_BLUE_DOWN_0.png";
            Image[] images = new Image[2];

            for (int i = 0; i < 2; i++) {

                images[i] = new Image(path + dir.name()+ "_" + i + ".png");

            }

            walkingImage.put(dir, images);

        }


        String path = "com/jdelza/view/assets/enemies/oktorock/OKTOROCK_BLUE_" + enemyDirection.name() +"_0.png";
        //"file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/OKTOROCK_BLUE_DOWN_0.png";

        tileImageView = new ImageView();
        tileImageView.setImage(new Image(path));

        tileImageView.setFitWidth(super.getTileWidth());
        tileImageView.setFitHeight(super.getTileHeight());
        tileImageView.setSmooth(false);

        this.getChildren().add(tileImageView);



    }

    @Override
    public void changeSprite(Directions direction){
        if(frame)
            tileImageView.setImage(walkingImage.get(direction)[0]);
        else
            tileImageView.setImage(walkingImage.get(direction)[1]);

        frame = !frame;

    }








}
