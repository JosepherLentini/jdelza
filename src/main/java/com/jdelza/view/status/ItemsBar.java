package com.jdelza.view.status;

import com.jdelza.utils.enums.Dimensions;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ItemsBar extends VBox {


    private ItemLine rupie;
    private ItemLine key;


    public ItemsBar() {

        this.rupie = new ItemLine("com/jdelza/view/assets/status_bar_assets/RUPIA_QUANTITY.png");
        this.key = new ItemLine("com/jdelza/view/assets/status_bar_assets/KEY_QUANTITY.png");


        //ItemsBar dimensions
        this.setPrefSize(273, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMinSize(273, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMaxSize(273, Dimensions.STATUS_BAR_HEIGT.get());

        /*
        this.setStyle(

                "-fx-border-color: yellow; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
        );

         */




        this.getChildren().addAll(rupie, key);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);


    }

    //Get method
    public ItemLine getRupie() {return rupie;}
}
