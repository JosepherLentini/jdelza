package com.jdelza.view.user;

import com.jdelza.utils.enums.Dimensions;
import com.jdelza.utils.enums.GameKey;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationScreen extends VBox {


    private HBox text;

    public UserRegistrationScreen() {


        //Set user registration creen standard size
        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);

        //Children pane creation
        StackPane imagePane = new StackPane();
        StackPane nameTextPane = new StackPane();
        StackPane enterPane = new StackPane();

        //ImagePane dimensions
        imagePane.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);
        imagePane.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);
        imagePane.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);

        ImageView imagePaneView = new ImageView(new Image("com/jdelza/view/assets/user_section/USER.png"));
        imagePaneView.setFitHeight(80);
        imagePaneView.setFitWidth(80);

        ImageView typeYourNameImage = new ImageView(new Image("com/jdelza/view/assets/user_section/TYPE YOUR NAME.png"));
        typeYourNameImage.setFitWidth(250);
        typeYourNameImage.setFitHeight(17);
        typeYourNameImage.setTranslateY(80);

        //imagePane.setAlignment(imagePaneView, Pos.TOP_CENTER);
        //imagePane.setAlignment(typeYourNameImage, Pos.BOTTOM_CENTER);

        imagePane.getChildren().addAll(imagePaneView, typeYourNameImage);


        //NameTextPane dimensions
        nameTextPane.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);
        nameTextPane.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);
        nameTextPane.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);

        this.text = new HBox();
        text.setPrefSize(480, 24); // max length = 552px, || 1 char = 24px || 20x char = 480px
        text.setMaxSize(480, 24);
        text.setMinSize(480, 24);
        CursorAndCharBox cb = new CursorAndCharBox();
        cb.toFront();
        text.getChildren().add(cb);

        this.setStyle(
                "-fx-border-width: 5;"+
                        "-fx-border-color: blue;"+
                        "-fx-border-radius: 5 5 5 5;"
        );



        text.setAlignment(Pos.CENTER);

        nameTextPane.getChildren().add(text);


        //EnterPane dimensions
        enterPane.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);
        enterPane.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);
        enterPane.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 200);

        ImageView enterPaneImageView = new ImageView(new Image("com/jdelza/view/assets/user_section/PUSH ENTER TO REGISTER.png"));
        enterPaneImageView.setFitHeight(50);
        enterPaneImageView.setFitWidth(300);

        enterPane.getChildren().add(enterPaneImageView);


        /*
        imagePane.setStyle(
                "-fx-background-color: blue;"
        );


        nameTextPane.setStyle(
                "-fx-background-color: green;"
        );
        enterPane.setStyle(
                "-fx-background-color: yellow;"
        );

         */



        this.getChildren().addAll(imagePane, nameTextPane, enterPane);



    }

    /**
     * When user type a key, the typed key will be drawn by this function
     * @param key typed key
     */
    public void writeName(GameKey key){

        if (!text.getChildren().isEmpty() && text.getChildren().getLast() instanceof CursorAndCharBox charBox){
            charBox.fromCursorToChar(key);
        }

        CursorAndCharBox charX = new CursorAndCharBox();
        charX.toFront();
        this.text.getChildren().add(charX);

    }

    /**
     * This function delete last character when user wants it
     */
    public void cancelLastChar(){


        if (text.getChildren().getLast() instanceof CursorAndCharBox){
            text.getChildren().remove(text.getChildren().getLast());
        }

        if (text.getChildren().getLast() instanceof CursorAndCharBox cursorChar){
            cursorChar.fromCharToCursor();
        }



    }

    public void resetTypedName(){

        List<CursorAndCharBox> allTypedCharacters = new ArrayList<>();

        text.getChildren().stream().forEach(
                node -> {
                    if (node instanceof CursorAndCharBox curChar){
                        allTypedCharacters.add(curChar);
                    }
                }
        );

        this.text.getChildren().removeAll(allTypedCharacters);

        CursorAndCharBox cb = new CursorAndCharBox();
        cb.toFront();
        text.getChildren().add(cb);

    }


}
