package com.jdelza.view.overworld;


import com.jdelza.utils.Dimensions;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Overworld extends Pane {

    private final int tileSize = 16;

    // Definiamo la scala (moltiplicatore x4) e le dimensioni
    private final int scala = 4;
    private final double larghezzaMappa = Dimensions.MAP_WIDTH.getInt();
    private final double altezzaMappa = Dimensions.MAP_HEIGHT.getInt();

    public Overworld(){

        // Aggiungi la mappa al Pane
        this.setStyle(
                "-fx-border-color: red; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-width: 3px; "

        );

        OverworldMap m = new OverworldMap();
        ImageView[][] t = m.getOverworldMap();

        for (int i = 0; i< t.length; i++){
            for (int j = 0; j< t[0].length; j++){

                t[i][j].setLayoutY(i*4);
                t[i][j].setLayoutY(i*Dimensions.MAP_HEIGHT.get());
                t[i][j].setLayoutX(j*Dimensions.MAP_WIDTH.get());
                this.getChildren().add(t[i][j]);
            }
        }



        this.setPrefSize(larghezzaMappa, altezzaMappa);

        //If you set minimum and maximum dimensions, the component will not adapt to its parent
        this.setMinSize(this.larghezzaMappa, this.altezzaMappa);
        this.setMaxSize(this.larghezzaMappa, this.altezzaMappa);


    }

    public void aggiungiVista(ImageView image){
        this.getChildren().add(image);
    }


}
