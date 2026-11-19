package com.jdelza.view.user;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * This class contains a user’s details on the user registration screen
 */
public class UserLine extends HBox {

    private NameBar nameBar;

    private LifeBox lifeBox;
    private PointsBox pointsBox;

    public UserLine() {

        //Initialize class status
        this.nameBar = new NameBar();
        this.lifeBox = new LifeBox();
        this.pointsBox = new PointsBox();

        //Initialize current class dimensions
        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 200); //704
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 200);
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 200);

        //Creation of the node that will contain the user image
        StackPane imageLine = new StackPane();

        //ImageLine dimensions
        imageLine.setPrefSize(120, 200);
        imageLine.setMaxSize(120, 200);
        imageLine.setMinSize(120, 200);


        //imageLIne imageView
        ImageView userImage = new ImageView(new Image("com/jdelza/view/assets/user_section/USER.png"));
        userImage.setFitHeight(80);
        userImage.setFitWidth(80);
        imageLine.getChildren().add(userImage);

        //Add children nodes to the class
        this.getChildren().addAll(imageLine, nameBar, pointsBox, lifeBox);

    }



    public NameBar getNameBar() {
        return nameBar;
    }


    /**
     * This function is called when the user has registered,
     * at which point information about their scores and lives will be displayed.
     */
    public void initializeLifeAndPoints(){

        //Set points visible
        this.pointsBox.addPointsLine();

        //Set life visible
        this.lifeBox.addLifeLine();
    }



}
