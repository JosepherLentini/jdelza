package com.jdelza.view.weapons;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.Directions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class WeaponView extends Pane {

    private Directions weaponDirection;
    private String weaponID;

    public WeaponView(String weaponID) {
        this.weaponID = weaponID;

        ImageView img  = new ImageView();
        img.setImage(new Image("file:///C:/Users/Giuseppe Lentini/OneDrive/Immagini/Zelda/OKTOROCK_WEAPON.png"));

        img.setFitWidth(Dimensions.TILE_WIDTH.get());
        img.setFitHeight(Dimensions.TILE_HEIGT.get());
        img.setSmooth(false);

        this.getChildren().add(img);

    }

    //Get methods
    public Directions getWeaponDirection() {return weaponDirection;}
    public String getWeaponID() {return weaponID;}

    //Set methods
    public void setWeaponDirection(Directions weaponDirection) {
        this.weaponDirection = weaponDirection;
    }

    //public void setWeaponID(String weaponID) {this.weaponID = weaponID;}
}
