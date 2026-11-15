package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.view.user.UserSelect;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UserScreen extends VBox {

    private UserSelect userSelcet;

    public UserScreen() {

        this.setFocusTraversable(true);



        //Initialise userSelect instance
        this.userSelcet = new UserSelect();

        //Set userScreen standard size
        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get()); //704
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());


        Pane title = new Pane();
        title.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 104); //704
        title.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 104);
        title.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 104);
        ImageView titleImageView = new ImageView(new Image("com/jdelza/view/assets/user_section/SELECT.png"));
        title.getChildren().add(titleImageView);



        this.getChildren().addAll(title, this.userSelcet);



    }

    //Get methods
    public UserSelect getUserSelect() {
        return userSelcet;
    }
}
