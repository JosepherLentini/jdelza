package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.EnvironmentType;
import com.jdelza.utils.enums.Level;
import com.jdelza.utils.interfaces.EnvironmentSwitcher;
import com.jdelza.view.characters.PlayerView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DungeonScreen extends Pane implements EnvironmentSwitcher {

    private final double larghezzaMappa = Dimensions.MAP_WIDTH.get();
    private final double altezzaMappa = Dimensions.MAP_HEIGHT.get();

    private Level dungeonLevel;
    private PlayerView playerView;

    public DungeonScreen(Level dungeonLevel) {
        this.dungeonLevel = dungeonLevel;
        this.playerView = playerView;

        this.setPrefSize(larghezzaMappa, altezzaMappa);

        this.setMinSize(this.larghezzaMappa, this.altezzaMappa);
        this.setMaxSize(this.larghezzaMappa, this.altezzaMappa);

        drawDungeon(dungeonLevel);

        /*
        ImageView im = new ImageView(new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/Shop.png"));

        im.setFitHeight(altezzaMappa);
        im.setFitWidth(larghezzaMappa);

        this.getChildren().add(im);

         */




    }


    public void drawDungeon(Level level){


        for (int y = 0; y < 3; y++){

            for (int x = 0; x<3; x++){

                //"C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/dungeons/FIRST_LEVEL/FIRST_0_0.png"
                String path = "file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/dungeons/"+ level.name()+"_LEVEL/";


                Pane dungeonZone = new Pane();
                dungeonZone.setMinSize(Dimensions.MAP_WIDTH.get(), Dimensions.MAP_HEIGHT.get());
                dungeonZone.setMaxSize(Dimensions.MAP_WIDTH.get(), Dimensions.MAP_HEIGHT.get());


                ImageView dungeonImw = new ImageView(new Image(path+level.name()+"_"+ y +"_"+x+".png"));
                dungeonImw.setFitHeight(Dimensions.MAP_HEIGHT.get());
                dungeonImw.setFitWidth(Dimensions.MAP_WIDTH.get());
                dungeonImw.setSmooth(false);



                dungeonImw.setLayoutY(y*Dimensions.MAP_HEIGHT.get());
                dungeonImw.setLayoutX(x*Dimensions.MAP_WIDTH.get());


                this.getChildren().add(dungeonImw);


            }
        }

    }


    @Override
    public EnvironmentType getEnvironmentType() {
        return EnvironmentType.DUNGEON;
    }
}
