package com.jdelza.view.status;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.Arrays;

public class ItemLine extends HBox {

    private Pane itemImage;
    private HBox itemQuantity;


    public ItemLine(String path) {
        this.itemImage = new Pane();

        //itemQuantity settings
        this.itemQuantity = new HBox();
        itemQuantity.setPrefSize(24, 72);
        itemQuantity.setMinSize(24, 72);
        itemQuantity.setMaxSize(24, 72);

        //ItemsBar dimensions
        this.setPrefSize(273, 24);
        this.setMinSize(273, 24);
        this.setMaxSize(273, 24);

        //ItemLine spacing
        this.setSpacing(5);

        //initialize quantity
        ImageView numberImage = new ImageView(new Image("com/jdelza/view/assets/user_section/alphabet_font/CHAR_"+0+".png"));
        numberImage.setFitHeight(24);
        numberImage.setFitWidth(24);
        itemQuantity.getChildren().add(numberImage);


        //ItemImage ImageView
        ImageView itemImage = new ImageView(new Image(path));
        itemImage.setFitWidth(48);
        itemImage.setFitHeight(24);
        this.itemImage.getChildren().add(itemImage);

        this.getChildren().addAll(itemImage, itemQuantity);

    }


    public void incrementItemQuantity(int newQuantity){
        itemQuantity.getChildren().clear();

        String[] scoreString = Integer.toString(newQuantity).split("");


        for (String number: scoreString) {
            ImageView numberImage = new ImageView(
                    new Image("com/jdelza/view/assets/user_section/alphabet_font/CHAR_"+number.toUpperCase()+".png")
            );
            numberImage.setFitHeight(24);
            numberImage.setFitWidth(24);
            itemQuantity.getChildren().add(numberImage);
        }
    }



}