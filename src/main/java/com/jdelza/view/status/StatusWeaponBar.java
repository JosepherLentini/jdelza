package com.jdelza.view.status;

import com.jdelza.utils.enums.Dimensions;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StatusWeaponBar extends StackPane {


    private VBox swordBox;
    private ImageView swordImageVIew;

    public StatusWeaponBar() {

        this.swordBox = new VBox();
        this.swordImageVIew = new ImageView(new Image("com/jdelza/view/assets/items_objects/SWORD.png"));

        //Current class dimensions
        this.setPrefSize(125.5, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMinSize(125.5, Dimensions.STATUS_BAR_HEIGT.get());
        this.setMaxSize(125.5, Dimensions.STATUS_BAR_HEIGT.get());

        /*
        this.setStyle(

                "-fx-border-color: green; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 2px; "
        );

         */


        //SwordBox dimensions
        swordBox.setPrefSize(80,110);
        swordBox.setMinSize(80,110);
        swordBox.setMaxSize(80,110);
        swordBox.setAlignment(Pos.CENTER);

        swordImageVIew.setFitWidth(80);
        swordImageVIew.setFitHeight(80);


        swordBox.setStyle(

                "-fx-border-color: blue; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 4px; "+
                        "-fx-border-radius: 5px;"
        );

        this.getChildren().add(swordBox);

    }

    /**
     * When player take sword, this function will show the sword in the box
     */
    public void addSword(){
        swordBox.getChildren().add(swordImageVIew);
    }


}
