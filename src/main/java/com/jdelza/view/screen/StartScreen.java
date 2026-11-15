package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.awt.*;

public class StartScreen extends Pane {



    private ImageView startImageView;

    private boolean storySection = false;
    private StoryScreen storyScreen = new StoryScreen();

    private AnimationTimer startAnimation;


    public StartScreen() {



        //"file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/Shop.png"
        startImageView = new ImageView(new Image("com/jdelza/view/assets/start_data/START_1.png"));

        startImageView.setFitWidth(Dimensions.RENDER_SCREEN_WIDTH.get());
        startImageView.setFitHeight(Dimensions.RENDER_SCREEN_HEIGHT.get());

        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());

        //LEGGE GLI EVENTI PRIMA DEL COMPONENTE WRAPPER
        this.setFocusTraversable(true);
        this.requestFocus();


        this.setStyle(
                "-fx-background-color: black;"
        );


        this.startAnimation = new AnimationTimer() {

            long current = 0;
            final long period = 50_000_000;
            int imageIndex = 1;

            @Override
            public void handle(long l) {
                if (l - current > period){

                    if(imageIndex == 7){
                        imageIndex =1;
                    }

                    System.out.println(imageIndex);

                    startImageView.setImage(
                            new Image(
                                    "com/jdelza/view/assets/start_data/START_"+imageIndex+".png"));
                    imageIndex++;
                    current = l;

                }
            }

        };

        /*
        this.setStyle(
                "-fx-border-color: blue; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );

         */

        this.getChildren().add(startImageView);


        startAnimation.start();



    }

    public StoryScreen getStoryScreen() {
        return storyScreen;
    }

    public void setStoryScreen(StoryScreen storyScreen) {
        this.storyScreen = storyScreen;
    }

    public void setStartImageViewImage(Image image) {
        this.startImageView.setImage(image);
    }

    public void stopTitleBackgroundAnimation(){
        this.startAnimation.stop();
    }

    public void goToStorySection(){
        storySection = true;

        this.getChildren().setAll(getStoryScreen());
        startAnimation.stop();

        storyScreen.storyScreenAnimation();
    }



}
