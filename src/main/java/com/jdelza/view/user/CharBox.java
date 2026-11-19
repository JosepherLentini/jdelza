package com.jdelza.view.user;

import com.jdelza.utils.enums.GameKey;
import com.jdelza.view.screen.UserScreen;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * This class represents a character
 */
public class CharBox extends Pane {


    //Class imageview
    private ImageView charImageView;

    /**
     * CharBox constructor
     */
    public CharBox() {

        //set char box dimensions
        this.setPrefSize(24,24);
        this.setMaxSize(24,24);
        this.setMinSize(24,24);


        //Initialize char image view
        this.charImageView = new ImageView();
        this.charImageView.setFitWidth(24);
        this.charImageView.setFitHeight(24);

    }


    protected void setCharImage(GameKey key){
        //then it will give the char image
        this.charImageView.setImage(UserScreen.getKeysImages().get(key));
        this.getChildren().add(charImageView);
    }




}
