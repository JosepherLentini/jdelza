package com.jdelza.model.characters;

import com.jdelza.model.enums.Directions;
import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.interfaces.Damageable;
import com.jdelza.model.interfaces.Movable;


/**
 * This class define the player of the game
 */
public class Player extends GameCharacter implements Movable, Damageable {

    private double lifes;


    public Player(Coordinates position) {
        super(position);
    }

    //Get methods
    public double getLifes() {
        return lifes;
    }

    //Set methods
    public void setLifes(double lifes) {
        this.lifes = lifes;
    }

    @Override
    public void move(Directions direction) {
        this.setPosition(new Coordinates(
                this.getPosition().getX() + direction.getX(),
                this.getPosition().getY() + direction.getY()
        ));
    }

    @Override
    public String toString(){
        return "P ";
    }

    @Override
    public void takeDamage(double attackPoints) {
        double damage = this.getLifes() - attackPoints;

        if (damage < 0) {
            this.setLifes(0.0);
        } else {
            this.setLifes(damage);
        }
    }
}
