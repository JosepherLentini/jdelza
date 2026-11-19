package com.jdelza.view.items_objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * This class represent a hearth
 */
public class LifeView extends StackPane {

    private double height;
    private double width;

    private Image fullHearth;
    private Image middleHearth;
    private Image emptyHearth;

    private ImageView lifeImage;

    public LifeView(double height, double width) {
        this.height = height;
        this.width = width;

        //initialize images
        this.emptyHearth = new Image("com/jdelza/view/assets/items_objects/hearth/EMPTY_HEARTH.png");
        this.fullHearth = new Image("com/jdelza/view/assets/items_objects/hearth/FULL_HEARTH.png");
        this.middleHearth =new Image("com/jdelza/view/assets/items_objects/hearth/MIDDLE_HEARTH.png");

        //Initialize life image view
        this.lifeImage = new ImageView();
        lifeImage.setFitHeight(this.height);
        lifeImage.setFitWidth(this.width);
        lifeImage.setImage(fullHearth);

        this.getChildren().add(lifeImage);


    }

    /**
     * Set class image to middle hearth
     */
    public void setToMiddleHearth(){
        lifeImage.setImage(this.middleHearth);
    }

    /**
     * Set class image to full hearth
     */
    public void setToFullHearth(){
        lifeImage.setImage(this.fullHearth);
    }

    /**
     * Set class image to empty hearth
     */
    public void setToEmptyHearth(){
        this.lifeImage.setImage(this.emptyHearth);
    }
}
