package com.jdelza.view.status;

import com.jdelza.utils.enums.Dimensions;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class StatusMap extends StackPane {

    private Pane indicator;
    private Pane map;

    public StatusMap() {

        this.indicator = new Pane();
        this.map = new Pane();


        //StatusMap dimensions
        this.setPrefSize(273, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMinSize(273, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMaxSize(273, Dimensions.STATUS_BAR_HEIGT.get());

        //map dimensions
        this.map.setPrefSize(272, 136);
        this.map.setMinSize(272, 136);
        this.map.setMaxSize(272, 136);

        map.setStyle(
                "-fx-background-color: grey; "
        );

        //Indicator dimensions
        this.indicator.setPrefSize(17, 17);
        this.indicator.setMinSize(17, 17);
        this.indicator.setMaxSize(17, 17);

        indicator.setStyle(
                "-fx-background-color: green; "
        );

        /*
        this.setStyle(

                        "-fx-border-color: blue; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
        );

         */



        this.setAlignment(Pos.CENTER);
        //this.setTranslateX(10);


        map.getChildren().add(indicator);
        this.getChildren().add(map);
    }



    public void moveIndicatorToCurrentPlayerPosition(double YCoordinate, double XCoordinate){
        //reset all position settings
        indicator.setLayoutX(0);
        indicator.setLayoutY(0);
        indicator.setTranslateX(0);
        indicator.setTranslateY(0);


        //set to position by coordinates
        indicator.setTranslateX(17*XCoordinate);
        indicator.setTranslateY(17*YCoordinate);


    }


    public void drawMap(int rows, int columns, int indicatorPositionX, int indicatorPositionY){

        this.map.setPrefSize(17*columns, 17*rows);
        this.map.setMinSize(17*columns, 17*rows);
        this.map.setMaxSize(17*columns, 17*rows);


        moveIndicatorToCurrentPlayerPosition( indicatorPositionY, indicatorPositionX);

    }


}
