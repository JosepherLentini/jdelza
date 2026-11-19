package com.jdelza.view;

import com.jdelza.utils.enums.GameKey;
import com.jdelza.utils.events.SaveNewPlayerName;
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
            this.getUserScreen().getUserSelect().setFocusTraversable(true);
            this.getUserScreen().getUserSelect().requestFocus();

        }

        if (arg instanceof GameKey key){
            this.userScreen.getUserRegistrationScreen().writeName(key);
        }


        if (arg instanceof String str){

            if(str.equals("REGISTER")){
                this.userScreen.goToUserRegistrationScreen();
            }



            if (str.equals("CANCEL")){

                this.userScreen.getUserRegistrationScreen().cancelLastChar();
            }



        }


        if(arg instanceof SaveNewPlayerName save){
            userScreen.getUserRegistrationScreen().resetTypedName();
            userScreen.getUserSelect().addNameToSelectedUSer(save.getPlayerName(), save.getSelectedUser());
            userScreen.getUserSelect().getUsersLine().get(save.getSelectedUser()-1).initializeLifeAndPoints();
            userScreen.goToSelectScreen();

        }


    }
}
