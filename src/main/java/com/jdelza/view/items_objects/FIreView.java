package com.jdelza.view.items_objects;

import com.jdelza.utils.enums.Dimensions;
import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FIreView extends Pane {

    private double x;
    private double y;

    private boolean changeImage;

    private ImageView fireImage;

    public FIreView(Double x, Double y) {
        this.x = x;
        this.y = y;

        fireImage = new ImageView();

        /*
        this.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "



        );

         */
        //this.setMinSize(this.larghezzaMappa-4*Dimensions.TILE_WIDTH.get(), this.altezzaMappa-4* Dimensions.TILE_HEIGT.get());
        //this.setMaxSize(this.larghezzaMappa-4*Dimensions.TILE_WIDTH.get(), this.altezzaMappa-4* Dimensions.TILE_HEIGT.get());



        this.setMinSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());
        this.setMaxSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());

        this.setLayoutX(Dimensions.TILE_WIDTH.get()*x);
        this.setLayoutY(Dimensions.TILE_HEIGT.get()*y);


        fireImage.setImage(new Image("com/jdelza/view/assets/enemies/oktorock/OKTOROCK_BLUE_DOWN_1.png"));
        fireImage.setFitWidth(Dimensions.TILE_WIDTH.get());
        fireImage.setFitHeight(Dimensions.TILE_HEIGT.get());
        this.getChildren().add(fireImage);
        fireAnimation();
    }

    private void fireAnimation(){

        TranslateTransition transition = new TranslateTransition();

        transition.setDuration(Duration.millis(200));
        transition.setNode(fireImage);

        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        AnimationTimer fireAnimation = new AnimationTimer() {

            long current = 0;
            final long period = 500_000_000;

            @Override
            public void handle(long l) {
                    if (l - current > period){

                        changeHandle();
                        current = l;

                    }
            }

            private void changeHandle(){
                int fire = changeImage ? 0 : 1;
                if (changeImage){
                    fireImage.setImage(new Image("com/jdelza/view/assets/items_objects/fire/FIRE_"+fire+".png"));
                }else{
                    fireImage.setImage(new Image("com/jdelza/view/assets/items_objects/fire/FIRE_"+fire+".png"));
                }

                changeImage = !changeImage;
            }


        };

        fireAnimation.start();




    }




}
