package com.jdelza.model;

import com.jdelza.controller.GameController;
import com.jdelza.view.MainView;

import java.util.Observable;
import java.util.Observer;

public class StartModel extends Observable  {

    private boolean storyScreen = false;


    public StartModel(MainView mainView, GameController gameController) {
        addObserver(mainView);
        addObserver(gameController);

    }


    public void changeScreenAndScene(){
        if (!storyScreen){
            goToStoryScene();
        }else{
            System.out.println("annamo");
            goToUsersScene();
        }
    }

    private void goToUsersScene(){
        setChanged();
        //notifyObservers("START_GAME");
        notifyObservers("USER");

    }

    private void goToStoryScene(){
        setChanged();
        notifyObservers("STORY");

    }


    public void setStoryScreen(boolean storyScreen) {
        this.storyScreen = storyScreen;
    }
}
