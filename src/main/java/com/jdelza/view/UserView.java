package com.jdelza.view;

import com.jdelza.view.screen.UserScreen;

import java.util.Observable;
import java.util.Observer;

public class UserView implements Observer {

    private final UserScreen userScreen;

    public UserView() {

        this.userScreen = new UserScreen();
    }

    //Get methods
    public UserScreen getUserScreen() {
        return userScreen;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer selected){
            this.getUserScreen().getUserSelect().getSelectionBar().selectUser(selected);
            System.out.println(selected);
        }

        if (arg instanceof String mstring){

        }
    }
}
