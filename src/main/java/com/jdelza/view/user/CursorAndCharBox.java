package com.jdelza.view.user;

import com.jdelza.utils.enums.GameKey;
import com.jdelza.view.screen.UserScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class CursorAndCharBox extends CharBox{


    private boolean isCursor = true;
    private AnimationTimer cursor;



    //Class imageview
    private ImageView charImageView;


    public CursorAndCharBox() {

        //set char box dimensions
        this.setPrefSize(24,24);
        this.setMaxSize(24,24);
        this.setMinSize(24,24);


        //Initialize char image view
        this.charImageView = new ImageView();
        this.charImageView.setFitWidth(24);
        this.charImageView.setFitHeight(24);







        //create cursor animation
        this.cursor = new AnimationTimer() {

            boolean color = true;
            long current = 0;
            final long period = 300_000_000;


            @Override
            public void handle(long l) {

                if (l-current >= period){
                    cursorChangeColor(color);
                    current = l;
                    color = !color;
                }

            }
        };

        cursor.start();



    }


    /**
     * This function uses when cursor animation requests to change color
     * @param color boolean which define the color
     */
    private void cursorChangeColor(boolean color){
        if (color){
            this.setStyle(
                    "-fx-background-color: black;"
            );
        }else{
            this.setStyle(
                    "-fx-background-color: white;"
            );
        }
    }

    /**
     * It takes a character as input and transforms the cursor into the
     * image representing the character typed by the user
     * @param key
     */
    public void fromCursorToChar(GameKey key){
        cursor.stop();
        this.setStyle(
                "-fx-background-color: pink;"
        );

        //then it will give the char image
        this.charImageView.setImage(UserScreen.getKeysImages().get(key));
        this.getChildren().add(charImageView);
    }

    /**
     * When the user wishes to delete a character,
     * this class reverts to displaying a cursor by
     * removing the image and resuming the cursor animatio
     */
    public void fromCharToCursor(){
        this.getChildren().remove(this.charImageView);
        cursor.start();
    }
}
