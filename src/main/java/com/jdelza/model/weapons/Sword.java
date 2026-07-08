package com.jdelza.model.weapons;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.enums.DamageType;
import com.jdelza.model.interfaces.Damageable;


public class Sword extends Weapon {
    public Sword(Coordinates position) {
        super(position, );
    }


    @Override
    public void toDamage(Damageable damageable, double attackPoints) {

        //When a collision occurs between sword and game characters, points are deducted
        if (this.getPosition().equals(damageable.getPosition())){
            damageable.takeDamage(this.getAttackPoints().getDamage());
        }
    }
}
