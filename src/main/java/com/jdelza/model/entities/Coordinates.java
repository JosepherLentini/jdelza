package com.jdelza.model.entities;

public class Coordinates {
    private int x,y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Get methods
    public int getX() {return x;}
    public int getY() {return y;}

    //Set methods
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

    @Override
    public String toString(){return  "(" + this.getX() + ", " + this.getY()+ ")";}

}
