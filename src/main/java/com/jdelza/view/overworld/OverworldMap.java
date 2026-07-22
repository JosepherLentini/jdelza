package com.jdelza.view.overworld;

import com.jdelza.utils.Dimensions;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OverworldMap {

    private ImageView[][] overworldMap;
    private final int scala = 4;
    private final double larghezzaMappa = Dimensions.MAP_WIDTH.getInt();
    private final double altezzaMappa = Dimensions.MAP_HEIGHT.getInt();   // 704 pixel

    public OverworldMap(){
        this.overworldMap = new ImageView[8][16];

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 16; j++){

                String path = "file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/overworld/row-" + (i+1) + "-column-" + (j+1) + ".png";
                Image zona = new Image(path);


                ImageView vistaZona = new ImageView(zona);

                // 1. Ritaglio originale (256x176) le dimensioni devono essere quelle originali dall immagine
                vistaZona.setViewport(new Rectangle2D(1, 1, 256, 176));

                // 2. Ingrandisci l'immagine di 4 volte e rendila nitida (senza sfocatura)
                vistaZona.setFitWidth(larghezzaMappa);
                vistaZona.setFitHeight(altezzaMappa);
                vistaZona.setSmooth(false);

                this.overworldMap[i][j] = vistaZona;

            }
        }


    }


    public ImageView[][] getOverworldMap() {
        return overworldMap;
    }
}
