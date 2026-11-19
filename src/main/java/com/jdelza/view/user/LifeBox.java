package com.jdelza.view.user;

import com.jdelza.view.items_objects.LifeView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class LifeBox extends StackPane {


    private final HBox lifeline;

    /**
     * Class constructor
     */
    public LifeBox() {

        this.lifeline = new HBox();
        lifeline.setPrefSize(100, 100);
        lifeline.setMaxSize(100, 100);
        lifeline.setMinSize(100, 100);
        lifeline.setSpacing(10);

        //set current class dimension
        this.setPrefSize(200, 200);
        this.setMaxSize(200, 200);
        this.setMinSize(200, 200);


    }

    /**
     * Add to this class 3 hearth representation
     */
    private void initializeLifes(){
        for(int i = 0; i < 3; i++){

            this.lifeline.getChildren().add(new LifeView(24, 24));

        }
    }

    /**
     * This function allows to display the lifeLine when user is registered.
     */
    public void addLifeLine(){
        initializeLifes();

        this.getChildren().add(lifeline);

        StackPane.setAlignment(lifeline, Pos.CENTER);
    }



}
