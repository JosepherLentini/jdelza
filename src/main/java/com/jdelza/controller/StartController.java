package com.jdelza.controller;

import com.jdelza.view.StartView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StartController {
    private StartView startView;

    public StartController(StartView startView) {
        this.startView = startView;



        this.startView.getStartScreen().setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.ENTER){System.out.println("eeee"); pressStart();}
        });


    }

    //Get methods
    public StartView getStartView() {
        return startView;
    }


    public void pressStart(){
        startView.goToGame();
    }



}
