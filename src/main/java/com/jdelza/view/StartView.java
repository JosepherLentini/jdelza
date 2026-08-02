package com.jdelza.view;

import com.jdelza.utils.enums.Screen;
import com.jdelza.utils.events.ChangeScreen;
import com.jdelza.view.screen.StartScreen;

import java.util.Observable;

public class StartView extends Observable{

    private StartScreen startScreen;

    public StartView() {
        this.startScreen = new StartScreen();

    }


    //Get methods
    public StartScreen getStartScreen() {
        return startScreen;
    }


    public void goToGame(){
            setChanged();
            notifyObservers(new ChangeScreen(Screen.GAME));

    }

}
