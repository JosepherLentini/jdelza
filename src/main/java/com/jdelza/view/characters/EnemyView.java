package com.jdelza.view.characters;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import com.jdelza.view.overworld.Overworld;
import com.jdelza.view.screen.GameScreen;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.*;

public abstract class EnemyView extends CharacterView{


    protected ImageView tileImageView;          //Enemy imageView
    boolean frame = false;                    //This is used to switch player movement image

    protected String enemyViewID;    //Enemy id

    //Enemy previous position
    protected double prevX;
    protected double prevY;

    //Enemy direction
    protected Directions enemyDirection;

    //Dead
    private boolean dead = false;



    public EnemyView(String enemyViewID) {

        this.enemyViewID = enemyViewID;
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
    public boolean isDead() {return dead;}

    //Set methods
    public EnemyView setEnemyViewID(String id) {enemyViewID = id;return this;}
    public void setEnemyDirection(Directions enemyDirection) {this.enemyDirection = enemyDirection;}
    public void setDead(boolean dead) {this.dead = dead;}

    public void setPreviousPosition(double currX, double currY){
        this.prevX = currX;
        this.prevY = currY;
    }

    public void enemyDeath(Overworld ow){
        Timer timer = new Timer();
        TimerTask collisionTask = new TimerTask() {

            int count = 1;
            String path  ="file:///C:/Users/Giuseppe Lentini/Downloads/DEATH_";


            @Override
            public void run() {

                Platform.runLater(()->{

                    tileImageView.setImage(new Image(path+count+".png"));
                    count++;

                    if (count>= 5) {
                        ow.getChildren().remove(EnemyView.this);
                        timer.cancel();
                    }
                });

            }

        };


        timer.scheduleAtFixedRate(collisionTask, 0, 100);
    }

    @Override
    public String toString(){
        return "enemy: "+ this.enemyViewID;
    }

    public abstract void changeSprite(Directions direction);





}
