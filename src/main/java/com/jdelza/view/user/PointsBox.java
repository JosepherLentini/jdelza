package com.jdelza.view.user;

import com.jdelza.utils.enums.GameKey;
import com.jdelza.view.screen.UserScreen;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * This class displays the score on the user registration screen
 */
public class PointsBox extends StackPane {

    private HBox pointsLine;

    public PointsBox() {


        this.pointsLine = new HBox();
        pointsLine.setPrefSize(100,24);
        pointsLine.setMaxSize(100,24);
        pointsLine.setMinSize(100,24);
        pointsLine.setSpacing(10);



        //set current class dimension
        this.setPrefSize(200, 200);
        this.setMaxSize(200, 200);
        this.setMinSize(200, 200);

    }


    public void addNumber(GameKey key){

        Pane newNumber = new Pane();
        newNumber.setPrefSize(24, 24);
        newNumber.setMaxSize(24, 24);
        newNumber.setMinSize(24, 24);

        ImageView numberImageView = new ImageView(UserScreen.getKeysImages().get(key));
        numberImageView.setFitHeight(24);
        numberImageView.setFitWidth(24);


        newNumber.getChildren().add(numberImageView);

        this.pointsLine.getChildren().add(newNumber);
    }


    public void addPointsLine(){
        StackPane.setAlignment(pointsLine, Pos.CENTER);

        addNumber(GameKey.ZERO);

        this.getChildren().add(pointsLine);
    }
}
