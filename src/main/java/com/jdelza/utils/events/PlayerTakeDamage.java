package com.jdelza.utils.events;

import com.jdelza.utils.enums.Directions;

public class PlayerTakeDamage {
    private Directions playerDirection;
    private boolean playerInjuring;
    private double totalLives;
    private double livesLeft;

    public PlayerTakeDamage(Directions playerDirection, double totalLives, double livesLeft) {
        this.playerDirection = playerDirection;
        this.totalLives = totalLives;
        this.livesLeft = livesLeft;
    }

    public PlayerTakeDamage(boolean playerInjuring) {
        this.playerInjuring = playerInjuring;
    }

    //Get methods
    public Directions getPlayerDirection() {return playerDirection;}
    public boolean isPlayerInjuring() {return playerInjuring;}
    public double getTotalLives() {return totalLives;}
    public double getLivesLeft() {return livesLeft;}
}
