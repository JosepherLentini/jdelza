package com.jdelza.view.user;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SelectionBar extends StackPane {

    private ImageView selectImage;

    public SelectionBar() {

        this.setPrefSize(80, 600); //704
        this.setMaxSize(80, 600);
        this.setMinSize(80, 600);

        this.selectImage = new ImageView(new Image("com/jdelza/view/assets/items_objects/SWORD.png"));
        selectImage.setFitHeight(60);
        selectImage.setFitWidth(60);

        //select image should be start to the top
        selectImage.setTranslateY(-200);


        this.getChildren().add(selectImage);


    }

    public void selectUser(int userSelectedIndex){

        switch(userSelectedIndex){
            case 1: this.selectImage.setTranslateY(-200);break;
            case 2: this.selectImage.setTranslateY(0);break;
            case 3: this.selectImage.setTranslateY(200);break;
        }
    }



}
