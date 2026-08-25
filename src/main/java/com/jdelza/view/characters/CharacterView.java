package com.jdelza.view.characters;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.layout.Pane;

public abstract class CharacterView extends Pane {

    //Dimensions
    private final int tileWidth = Dimensions.TILE_WIDTH.get();        //Character height
    private final int tileHeight = Dimensions.TILE_HEIGT.get();       //Character width


    public CharacterView() {

        //Set max, min and pref size to maintain the standard size
        this.setPrefSize(tileWidth , tileHeight);
        this.setMaxSize(tileWidth, tileHeight);
        this.setMinSize(tileWidth,tileHeight);


        this.setStyle(
                "-fx-border-color: yellow; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "
        );
    }



    //Get methods
    public int getTileWidth() {return tileWidth;}
    public int getTileHeight() {return tileHeight;}
}
