package com.jdelza.view.user;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The UserSelect class represents the view where you select the player you want to play with
 */
public class UserSelect extends HBox {

    private SelectionBar selectionBar;
    private VBox usersList;


    private List<UserLine> usersLine;

    /**
     * UserSelect constructor
     */
    public UserSelect() {
        this.setFocusTraversable(true);

        //Set current class status
        this.selectionBar = new SelectionBar();
        this.usersList = new VBox();
        this.usersLine = new ArrayList<>(List.of(new UserLine(), new UserLine(), new UserLine()));


        //Set current class standard size
        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);

        //UserList size settings
        this.usersList.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 600); //704
        this.usersList.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 600);
        this.usersList.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 600);

        //Current class style
        this.setStyle(
                "-fx-border-width: 5;"+
                        "-fx-border-color: blue;"+
                        "-fx-border-radius: 5 5 5 5;"
        );


        //Add userLine to users list box
        for (UserLine ul: usersLine){
            usersList.getChildren().add(ul);
        }

        this.getChildren().addAll(this.selectionBar, this.usersList);

    }

    //get methods
    public SelectionBar getSelectionBar() {return selectionBar;}
    public VBox getUsersList() {return usersList;}
    public List<UserLine> getUsersLine() {return usersLine;}

    /**
     * This function allows you to display the new name entered by the user on the user selection screen.
     */
    public void addNameToSelectedUSer(String name, int selectedUser){
        this.getUsersList().getChildren().stream()
                .filter( node -> node instanceof UserLine)
                .map(ul-> (UserLine)ul).toList()
                .get(selectedUser-1).getNameBar().appendName(name);
    }




}
