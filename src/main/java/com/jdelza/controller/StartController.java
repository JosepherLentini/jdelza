package com.jdelza.controller;

import com.jdelza.model.GameModel;
import com.jdelza.model.StartModel;
import com.jdelza.view.StartView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StartController {
    private StartView startView;
    private GameController gameController;

    private StartModel startModel;

    public StartController(StartView startView, GameController gameController, StartModel startModel) {
        this.startView = startView;
        this.gameController = gameController;
        this.startModel = startModel;

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

        //gameController.startGame();

        startModel.changeScreenAndScene();
    }



}
