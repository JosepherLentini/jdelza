package com.jdelza.view.screen;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.GameKey;
import com.jdelza.view.user.UserRegistrationScreen;
import com.jdelza.view.user.UserSelect;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the parent class of the classes that manage users.
 */
public class UserScreen extends VBox {

    //All permitted keys or characters
    public static Map<GameKey, Image> keysImages;

    //Sections
    private UserSelect userSelcet;
    private UserRegistrationScreen userRegistrationScreen;

    //Image
    private ImageView titleImageView;

    //Images paths
    private final String selectImagePath = "com/jdelza/view/assets/user_section/SELECT.png";
    private final String registrationImagePath = "com/jdelza/view/assets/user_section/REGISTER_TITLE.png";




    public UserScreen() {

        //this.setFocusTraversable(true);

        //Initialise userSelect instance
        keysImages = new HashMap<>();


        //Add all game permitted keys
        for(GameKey key : GameKey.values()){

            //System.out.println("charboxç " + key.getValue().toUpperCase());
            String path = "com/jdelza/view/assets/user_section/alphabet_font/CHAR_";

            keysImages.putIfAbsent(key, new Image(path + key.getValue().toUpperCase() + ".png"));
        }


        this.userSelcet = new UserSelect();
        this.userRegistrationScreen = new UserRegistrationScreen();


        //Set userScreen standard size
        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get()); //704
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), Dimensions.RENDER_SCREEN_HEIGHT.get());


        Pane title = new Pane();
        title.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 104); //704
        title.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 104);
        title.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 104);


        this.titleImageView = new ImageView(new Image(selectImagePath));
        title.getChildren().add(titleImageView);


        this.getChildren().addAll(title, this.userSelcet);

    }

    //Get methods
    public UserSelect getUserSelect() {return userSelcet;}
    public UserRegistrationScreen getUserRegistrationScreen() {return userRegistrationScreen;}
    public static Map<GameKey, Image> getKeysImages() {return keysImages;}

    /**
     * Set image title to registration image
     */
    public void setRegistrationTitleImage(){
        this.titleImageView.setImage(new Image(registrationImagePath));
    }

    /**
     *Set image title to select image
     */
    public void setSelectTitleImage(){
        this.titleImageView.setImage(new Image(selectImagePath));
    }

    /**
     * Change screen to user registration screen
     */
    public void goToUserRegistrationScreen(){
        setRegistrationTitleImage();
        this.getChildren().remove(this.userSelcet);
        this.getChildren().add(this.userRegistrationScreen);

        this.userRegistrationScreen.requestFocus();
        this.setFocusTraversable(true);
    }

    /**
     * When this function is called, it will show the user select.
     */
    public void goToSelectScreen(){
        setSelectTitleImage();
        this.getChildren().remove(this.userRegistrationScreen);
        this.getChildren().add(this.userSelcet);

        this.userSelcet.requestFocus();
        this.setFocusTraversable(true);
    }



}
