package com.jdelza.view.status;

import com.jdelza.utils.enums.Dimensions;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatusLifeBar extends VBox {


    private ImageView lifeBarTitle;
    private HBox lifeIndicator;

    public StatusLifeBar() {

        //lifeBarTitle initialization
        this.lifeBarTitle = new ImageView(new Image("com/jdelza/view/assets/status_bar_assets/LIFE_WORD.png"));
        lifeBarTitle.setFitWidth(200);
        lifeBarTitle.setFitHeight(15);

        //lifeIndicator initialization
        this.lifeIndicator = new HBox();
        lifeIndicator.setAlignment(Pos.CENTER);
        lifeIndicator.setSpacing(5);
        drawHearts(3,3);


        //Current class dimensions
        this.setPrefSize(273, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMinSize(273, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMaxSize(273, Dimensions.STATUS_BAR_HEIGT.get());

        /*
        this.setStyle(

                "-fx-border-color: yellow; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
        );

        lifeBarTitle.setStyle(

                "-fx-border-color: green; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
        );

         */

        //Current class settings
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);
        this.getChildren().addAll(lifeBarTitle, lifeIndicator);

    }



    public void drawHearts(double totalLives, double livesLeft){
        //remove all children to reset
        this.lifeIndicator.getChildren().clear();


        double notEmptyLives = livesLeft;
        double emptyLives = totalLives - livesLeft;

        while (notEmptyLives > 0){


            ImageView lifeTobeAdded = new ImageView();
            lifeTobeAdded.setFitHeight(24);
            lifeTobeAdded.setFitWidth(24);

            if(notEmptyLives >= 1){
                lifeTobeAdded.setImage(new Image("com/jdelza/view/assets/items_objects/hearth/FULL_HEARTH.png"));
                //com/jdelza/view/assets/items_objects/hearth/FULL_HEARTH.png

            } else if (notEmptyLives == 0.5 ) {
                lifeTobeAdded.setImage(new Image("com/jdelza/view/assets/items_objects/hearth/MIDDLE_HEARTH.png"));
            }

            this.lifeIndicator.getChildren().add(lifeTobeAdded);
            notEmptyLives--;

        }



        while(emptyLives >= 1){
            ImageView emptyLife = new ImageView();
            emptyLife.setFitHeight(24);
            emptyLife.setFitWidth(24);
            emptyLife.setImage(new Image("com/jdelza/view/assets/items_objects/hearth/EMPTY_HEARTH.png"));

            this.lifeIndicator.getChildren().add(emptyLife);
            emptyLives--;
        }

    }



}
