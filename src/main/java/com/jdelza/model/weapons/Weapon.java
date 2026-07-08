package com.jdelza.model.weapons;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;
import com.jdelza.model.enums.DamageType;
import com.jdelza.model.interfaces.Damager;

public abstract class Weapon extends Entity implements Damager {

    private DamageType attackPoints;

    public Weapon(Coordinates position, DamageType attackPoints) {
        super(position);
        this.attackPoints = attackPoints;
    }

    //Get methods
    public DamageType getAttackPoints() {
        return attackPoints;
    }
}
