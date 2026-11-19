package com.jdelza.controller;

import com.jdelza.model.UserModel;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.GameKey;
import com.jdelza.view.UserView;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class UserController {


    private UserModel userModel ;
    private UserView userView ;

    public UserController(UserModel userModel, UserView userView) {

            this.userModel = userModel;
            this.userView = userView;

            //key event on UserSelect focus
            this.userView.getUserScreen().getUserSelect().setOnKeyPressed(keyEvent -> {
                //System.out.println("io clicco ma non funziona un cazzo" + keyEvent.getCode());
                userSelectKeyEvents(keyEvent);
            });

            //Key event on UserRegistrationScreen
            this.userView.getUserScreen().getUserRegistrationScreen().setOnKeyPressed(keyEvent -> {
                //System.out.println("io clicco ma non funziona un cazzo" + keyEvent.getCode());
                userRegistrationKeyEvents(keyEvent);
                saveTheName(keyEvent);
            });




    }

    public void userSelectKeyEvents(KeyEvent event){
        switch (event.getCode()){
            case DOWN -> changeSelectedUser(Directions.DOWN);
            case UP -> changeSelectedUser(Directions.UP);
            case ENTER -> userModel.registerOrPlay();

        }
    }

    public void userRegistrationKeyEvents(KeyEvent event){
        String permittedKeys = "abcdefghijklmnopqrstuvwxyz-.0123456789";


        if (event.getCode() == KeyCode.BACK_SPACE){
            System.out.println(event.getText());
            userModel.cancelKeys();
        }else if(permittedKeys.contains(event.getCode().getChar().toLowerCase())){


            userModel.writeName(GameKey.fromValue(event.getCode().getChar().toLowerCase()));
        }

    }

    private void saveTheName(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            userModel.nameRegistration();
        }
    }

    /**
     * Initialize userModel instasnce
     * @param userModel
     */
    public void setUserModel(UserModel userModel) {
        if (this.userModel == null){
            this.userModel = userModel;
        }
    }

    /**
     * Initialize userView instance
     * @param userView
     */
    public void setUserView(UserView userView) {
        if (this.userView == null){
            this.userView = userView;
        }
    }


    public void changeSelectedUser(Directions direction){
        userModel.selectUser(direction);
    }



}
