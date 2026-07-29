package com.jdelza.controller;

import com.jdelza.model.GameModel;
import com.jdelza.view.MainView;

public class MainController {

    //Model
    private GameModel gameModel;

    private MainView mainView;

    public MainController(GameModel gameModel) {
        this.mainView = new MainView(gameModel);
    }

    //Get methods
    public MainView getMainView() {return mainView;}


}
