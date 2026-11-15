package com.jdelza.view;

import com.jdelza.utils.enums.Screen;
import com.jdelza.utils.events.ChangeScreen;
import com.jdelza.view.screen.StartScreen;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;

public class StartView extends Observable implements Observer {

    private final StartScreen startScreen;

    public StartView() {
        this.startScreen = new StartScreen();
        

    }


    //Get methods
    public StartScreen getStartScreen() {
        return startScreen;
    }




    @Override
    public void update(Observable o, Object arg) {

    }
}
