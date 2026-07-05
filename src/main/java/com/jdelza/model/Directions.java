package com.jdelza.model;

public enum Directions {
    UP(0,-1), RIGHT(1,0), DOWN(0,1), LEFT(-1,0);

    final int x;
    final int y;
    Directions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Get methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
