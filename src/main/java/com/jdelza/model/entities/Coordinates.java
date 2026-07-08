package com.jdelza.model.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o ){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;

        Coordinates c = (Coordinates)o;

        return c.getX() == this.getX() && c.getY() == this.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
