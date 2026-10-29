package com.jdelza.controller;

import com.jdelza.model.GameModel;
import com.jdelza.view.StartView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StartController {
    private StartView startView;
    private GameController gameController;

    public StartController(StartView startView, GameController gameController) {
        this.startView = startView;
        this.gameController = gameController;

        this.startView.getStartScreen().setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER){
                pressStart();

            }
        });


    }

    //Get methods
    public StartView getStartView() {
        return startView;
    }


    public void pressStart(){

        //startView.goToGame();
        gameController.startGame();
    }



}
