package com.jdelza.model.interfaces;

import com.jdelza.model.entities.Coordinates;

public interface Damageable {
    void takeDamage(double attackPoints);
    Coordinates getPosition();
}
