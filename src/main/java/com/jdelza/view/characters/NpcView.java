package com.jdelza.view.characters;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.NPCType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class NpcView extends Pane {

    private ImageView npcImageView;
    private String imagePath;


    public NpcView(NPCType npcType) {

        this.npcImageView = new ImageView();

        switch (npcType){
            case OLD_MAN -> imagePath = "com/jdelza/view/assets/items_objects/NPCs/OLD_MAN.png";
            case MERCHANT -> imagePath = "com/jdelza/view/assets/items_objects/NPCs/MERCHANT.png";
        }

        this.setMinSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());
        this.setMaxSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());

        this.setLayoutX(Dimensions.TILE_WIDTH.get()*7.5);
        this.setLayoutY(Dimensions.TILE_HEIGT.get()*4.0);


        npcImageView.setImage(new Image(imagePath));
        npcImageView.setFitWidth(Dimensions.TILE_WIDTH.get());
        npcImageView.setFitHeight(Dimensions.TILE_HEIGT.get());
        this.getChildren().add(npcImageView);
    }
}
