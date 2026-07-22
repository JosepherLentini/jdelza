package com.jdelza.view.screen;

import com.jdelza.utils.Dimensions;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;


public class MainScreen extends StackPane {

    private final int mainScreenHeight;
    private final int mainScreenWidth;

    public MainScreen() {
        this.mainScreenHeight = Dimensions.RENDER_SCREEN_HEIGHT.getInt();
        this.mainScreenWidth  = Dimensions.RENDER_SCREEN_WIDTH.getInt();

        /*
        this.setStyle(
                "-fx-border-color: blue; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );

         */


        this.setPrefSize(mainScreenWidth, mainScreenHeight);

        //If you set minimum and maximum dimensions, the component will not adapt to its parent
        this.setMinSize(mainScreenWidth, mainScreenHeight);
        this.setMaxSize(mainScreenWidth, mainScreenHeight);




        //This prevents the contents from spilling over the edge of the component, making everything outside it invisible

        /*
        Rectangle clip = new Rectangle(mainScreenWidth,mainScreenWidth);
        this.setClip(clip);

         */



    }




}
