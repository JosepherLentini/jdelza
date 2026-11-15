package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;


public class StoryScreen extends Pane {


    private ImageView storyImageView;


    public StoryScreen() {


        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());


        this.storyImageView = new ImageView();
        storyImageView.setFitHeight(Dimensions.RENDER_SCREEN_HEIGHT.get());
        storyImageView.setFitWidth(Dimensions.RENDER_SCREEN_WIDTH.get());
        storyImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/jdelza/view/assets/start_data/STORY_SCREEN.png"))));

        this.setTranslateY(Dimensions.RENDER_SCREEN_HEIGHT.get());

        this.getChildren().add(storyImageView);

        //storyScreenAnimation();
    }

    public ImageView getStoryImageView() {
        return storyImageView;
    }

    public void storyScreenAnimation(){
        TranslateTransition transition = new TranslateTransition();
        System.out.println("e qua che inizia l'animazione");
        transition.setDuration(Duration.seconds(5));


        transition.setNode(this);

        transition.setToY(0);


        transition.play();
    }
}
