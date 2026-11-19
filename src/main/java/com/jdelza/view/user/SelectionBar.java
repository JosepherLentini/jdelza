package com.jdelza.view.user;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * This class displays the sidebar which shows which user has been selected
 */
public class SelectionBar extends StackPane {

    //ImageView
    private final ImageView selectImage;      //This ImageView represents the cursor

    public SelectionBar() {

        //Set class dimensions
        this.setPrefSize(80, 600); //704
        this.setMaxSize(80, 600);
        this.setMinSize(80, 600);

        //Initialize class imageView
        this.selectImage = new ImageView(new Image("com/jdelza/view/assets/items_objects/SWORD.png"));
        selectImage.setFitHeight(60);
        selectImage.setFitWidth(60);

        //select image should be start to the top
        selectImage.setTranslateY(-200);


        this.getChildren().add(selectImage);


    }

    /**
     * This function moves the selection cursor to the selected user
     * @param userSelectedIndex user selected index
     */
    public void selectUser(int userSelectedIndex){

        switch(userSelectedIndex){
            case 1: this.selectImage.setTranslateY(-200);break;
            case 2: this.selectImage.setTranslateY(0);break;
            case 3: this.selectImage.setTranslateY(200);break;
        }
    }



}
