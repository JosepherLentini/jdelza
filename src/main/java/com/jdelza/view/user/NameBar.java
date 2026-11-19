package com.jdelza.view.user;

import com.jdelza.utils.enums.GameKey;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class NameBar extends StackPane {


    private ImageView nameBarImageView = new ImageView();
    private HBox nameBox;

    public NameBar() {

        this.nameBox = new HBox();

        this.setPrefSize(552, 200);
        this.setMaxSize(552, 200);
        this.setMinSize(552, 200);



        nameBarImageView.setImage(new Image("com/jdelza/view/assets/user_section/REGISTER_YOUR_NAME.png"));
        nameBarImageView.setFitWidth(552);
        nameBarImageView.setFitHeight(100);
        this.getChildren().add(nameBarImageView);



        nameBox.setPrefSize(552, 24);
        nameBox.setMaxSize(552, 24);
        nameBox.setMinSize(552, 24);
        nameBox.setTranslateX(48);
        this.getChildren().add(nameBox);






    }



    public void appendName(String name){

        this.getChildren().remove(this.nameBarImageView);

        for(int i = 0; i <  name.length(); i++){

            GameKey currentKey = GameKey.fromValue(Character.toString(name.charAt(i)));
            CharBox charBox = new CharBox();
            charBox.setCharImage(currentKey);

            System.out.println(currentKey);

            nameBox.getChildren().add(charBox);



        }
    }
}
