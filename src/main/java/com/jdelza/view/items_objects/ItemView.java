package com.jdelza.view.items_objects;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.ItemType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ItemView extends Pane {

    private ImageView itemImage;
    private String imagePath;

    public ItemView(ItemType itemType) {
        this.itemImage = new ImageView();

        switch (itemType){
            case SWORD -> imagePath = "com/jdelza/view/assets/items_objects/SWORD.png";
            case RUPEE -> imagePath = "com/jdelza/view/assets/items_objects/rupie/RUPIA_BLUE.png";
            case LIFE -> imagePath = "com/jdelza/view/assets/items_objects/LIFE.png";
        }

        this.setMinSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());
        this.setMaxSize(Dimensions.TILE_WIDTH.get(), Dimensions.TILE_HEIGT.get());

        itemImage.setImage(new Image(imagePath));
        itemImage.setFitWidth(Dimensions.TILE_WIDTH.get());
        itemImage.setFitHeight(Dimensions.TILE_HEIGT.get());
        this.getChildren().add(itemImage);
    }



    public void setPosition(double x, double y){
        this.setLayoutX(Dimensions.TILE_WIDTH.get()*x);
        this.setLayoutY(Dimensions.TILE_HEIGT.get()*y);
    }
}
