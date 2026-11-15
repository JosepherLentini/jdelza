package com.jdelza.controller;

import com.jdelza.model.UserModel;
import com.jdelza.utils.enums.Directions;
import com.jdelza.view.UserView;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class UserController {


    private UserModel userModel ;
    private UserView userView ;

    public UserController(UserModel userModel, UserView userView) {

            this.userModel = userModel;
            this.userView = userView;

            this.userView.getUserScreen().setOnKeyPressed(keyEvent -> {
                //System.out.println("io clicco ma non funziona un cazzo" + keyEvent.getCode());
                keyEvents(keyEvent);
            });


        /*
        this.userView.getUserScreen().setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        System.out.println("funziona?");
                    }

                }
        );

         */


    }

    public void keyEvents(KeyEvent event){
        switch (event.getCode()){
            case DOWN -> changeSelectedUser(Directions.DOWN);
            case UP -> changeSelectedUser(Directions.UP);
            case ENTER -> userModel.registerOrPlay();
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
