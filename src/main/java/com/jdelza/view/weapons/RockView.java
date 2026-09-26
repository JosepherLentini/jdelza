package com.jdelza.view.weapons;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RockView extends WeaponView{

    public RockView(String weaponID) {
        super(weaponID);

        ImageView img  = new ImageView();
        img.setImage(new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/OKTOROCK_WEAPON.png"));

        img.setFitWidth(Dimensions.TILE_WIDTH.get());
        img.setFitHeight(Dimensions.TILE_HEIGT.get());
        img.setSmooth(false);

        this.getChildren().add(img);
    }
}
