package com.jdelza.view.user;

import com.jdelza.utils.enums.Dimensions;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class UserSelect extends HBox {

    private SelectionBar selectionBar;
    private VBox usersList;
    private int selectedUser = 1;

    private List<UserLine> usersLine;


    public UserSelect() {

        //Status initialization
        this.selectionBar = new SelectionBar();
        this.usersList = new VBox();
        this.usersLine = new ArrayList<>(List.of(new UserLine(), new UserLine(), new UserLine()));


        //Set UserSelect standard size
        this.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600); //704
        this.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);
        this.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get(), 600);

        //UserList size settings
        this.usersList.setPrefSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 600); //704
        this.usersList.setMaxSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 600);
        this.usersList.setMinSize(Dimensions.RENDER_SCREEN_WIDTH.get()-80, 600);


        //Add userLine to users list box
        for (UserLine ul: usersLine){
            usersList.getChildren().add(ul);
        }

        this.getChildren().addAll(this.selectionBar, this.usersList);

    }

    //get methods
    public SelectionBar getSelectionBar() {return selectionBar;}
    public int getSelectedUser() {return selectedUser;}
    public VBox getUsersList() {return usersList;}
}
