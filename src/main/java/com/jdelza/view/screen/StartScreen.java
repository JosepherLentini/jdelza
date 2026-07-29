package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StartScreen extends Pane {


    public StartScreen() {


        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());


        //LEGGE GLI EVENTI PRIMA DEL COMPONENTE WRAPPER
        this.setFocusTraversable(true);
        this.requestFocus();


        this.setStyle(
                "-fx-border-color: blue; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );


        Button change = new Button();
        change.setPrefSize(200, 70);
        change.setText("Click");
        change.setOnMouseClicked(e-> System.out.println("click"));


        //this.getChildren().add(change);


    }
}
