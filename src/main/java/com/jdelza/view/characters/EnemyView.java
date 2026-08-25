package com.jdelza.view.characters;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class EnemyView extends CharacterView{


    private ImageView tileImageView;          //Enemy imageView
    boolean frame = false;                    //This is used to switch player movement image

    private int EnemyViewID;    //Enemy id

    //Enemy position
    private Coordinates enemyViewMapPosition;
    private Coordinates enemyViewZonePosition;

    public EnemyView(Coordinates enemyViewMapPosition, Coordinates enemyViewZonePosition) {

        //Instance fields
        this.enemyViewMapPosition = enemyViewMapPosition;
        this.enemyViewZonePosition = enemyViewZonePosition;

        /*
        this.setStyle(
                "-fx-border-color: grey; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );

         */


        String path = "com/jdelza/view/assets/enemies/oktorock/OKTOROCK_BLUE_DOWN_0.png";
                    //"file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/OKTOROCK_BLUE_DOWN_0.png";

        tileImageView = new ImageView();
        tileImageView.setImage(new Image(path));

        tileImageView.setFitWidth(super.getTileWidth());
        tileImageView.setFitHeight(super.getTileHeight());
        tileImageView.setSmooth(false);

        this.getChildren().add(tileImageView);

        this.toFront();
    }


    //Get methods
    public int getEnemyViewID() {return EnemyViewID;}
    public Coordinates getEnemyViewMapPosition() {return enemyViewMapPosition;}
    public Coordinates getEnemyViewZonePosition() {return enemyViewZonePosition;}

    //Set methods
    public void setEnemyViewID(int enemyViewID) {EnemyViewID = enemyViewID;}





}
