package com.jdelza.view.characters;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class EnemyView extends CharacterView{


    protected ImageView tileImageView;          //Enemy imageView
    boolean frame = false;                    //This is used to switch player movement image

    protected String enemyViewID;    //Enemy id

    //Enemy previous position
    protected double prevX;
    protected double prevY;

    //Enemy direction
    protected Directions enemyDirection;



    public EnemyView(Coordinates enemyViewMapPosition, Coordinates enemyViewZonePosition) {

        //Instance fields
        //this.enemyViewMapPosition = enemyViewMapPosition;
        //this.enemyViewZonePosition = enemyViewZonePosition;



        /*
        this.setStyle(
                "-fx-border-color: grey; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );

         */


        this.toFront();
    }

    //Get methods
    public String getEnemyViewID() {return enemyViewID;}
    public Directions getEnemyDirection() {return enemyDirection;}

    public double getPrevX() {
        return prevX;
    }

    public double getPrevY() {
        return prevY;
    }

    //Set methods
    public void setEnemyViewID(String id) {enemyViewID = id;}
    public void setEnemyDirection(Directions enemyDirection) {this.enemyDirection = enemyDirection;}

    public void setPreviousPosition(double currX, double currY){
        this.prevX = currX;
        this.prevY = currY;
    }

    @Override
    public String toString(){
        return "enemy: "+ this.enemyViewID;
    }

    public abstract void changeSprite(Directions direction);



}
