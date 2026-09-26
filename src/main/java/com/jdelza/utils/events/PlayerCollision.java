package com.jdelza.utils.events;

import com.jdelza.utils.enums.Directions;

public class PlayerCollision {
    private Directions playerDirection;
    private boolean playerInjuring;

    public PlayerCollision(Directions playerDirection) {
        this.playerDirection = playerDirection;
    }

    public PlayerCollision(boolean playerInjuring) {
        this.playerInjuring = playerInjuring;
    }

    //Get method
    public Directions getPlayerDirection() {return playerDirection;}
    public boolean isPlayerInjuring() {return playerInjuring;}
}
