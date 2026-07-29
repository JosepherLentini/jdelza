package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


public class MainScreen extends StackPane {

    private final int mainScreenHeight;
    private final int mainScreenWidth;

    private Region currentSection;

    public MainScreen() {
        this.mainScreenHeight = Dimensions.RENDER_SCREEN_HEIGHT.get();
        this.mainScreenWidth  = Dimensions.RENDER_SCREEN_WIDTH.get();

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

    //Set methods
    /**
     * This method switch application section
     * @param currentSection
     */
    public void setCurrentSection(Region currentSection) {
        this.currentSection = currentSection;
        this.getChildren().setAll(currentSection);
    }
}
