package com.jdelza.utils.events;

import com.jdelza.utils.enums.Screen;

public class ChangeScreen {

    private Screen screen;

    public ChangeScreen(Screen screen) {
        this.screen = screen;
    }

    //Get methods
    public Screen getScreen() {return screen;}
}
