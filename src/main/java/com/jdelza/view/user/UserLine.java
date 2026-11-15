package com.jdelza.view.user;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class UserLine extends HBox {


    public UserLine() {


        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 200); //704
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 200);
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 200);


        //StackPane selectLine = new StackPane();
        StackPane nameLine = new StackPane();
        StackPane imageLine = new StackPane();
        StackPane lifeLine = new StackPane();
        StackPane pointsLine = new StackPane();

        /*
        selectLine.setPrefSize(80, 200); //704
        selectLine.setMaxSize(80, 200);
        selectLine.setMinSize(80, 200);

         */

        imageLine.setPrefSize(120, 200); //704
        imageLine.setMaxSize(120, 200);
        imageLine.setMinSize(120, 200);

        nameLine.setPrefSize(552, 200); //704
        nameLine.setMaxSize(552, 200);
        nameLine.setMinSize(552, 200);


        lifeLine.setPrefSize(200, 200); //704
        lifeLine.setMaxSize(200, 200);
        lifeLine.setMinSize(200, 200);


        pointsLine.setPrefSize(200, 200); //704
        pointsLine.setMaxSize(200, 200);
        pointsLine.setMinSize(200, 200);


        ImageView nameLineImageView = new ImageView();
        nameLineImageView.setImage(new Image("com/jdelza/view/assets/user_section/REGISTER_YOUR_NAME.png"));
        nameLineImageView.setFitWidth(552);
        nameLineImageView.setFitHeight(100);
        nameLine.getChildren().add(nameLineImageView);

        /*
        this.setStyle(
                "-fx-background-color: brown;"
        );

        /*
        selectLine.setStyle(
                "-fx-background-color: black;"
        );


        imageLine.setStyle(
                "-fx-background-color: grey;"
        );
        nameLine.setStyle(
                "-fx-background-color: purple;"
        );
        pointsLine.setStyle(
                "-fx-background-color: blue;"
        );
        lifeLine.setStyle(
                "-fx-background-color: red;"
        );

          */

        //selectLine.getChildren().add(selectImage);

        //imageLIne imageView
        ImageView userImage = new ImageView(new Image("com/jdelza/view/assets/user_section/USER.png"));
        userImage.setFitHeight(80);
        userImage.setFitWidth(80);
        imageLine.getChildren().add(userImage);



        this.getChildren().addAll(imageLine, nameLine, pointsLine, lifeLine);
    }
}
