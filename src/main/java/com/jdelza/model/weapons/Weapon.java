package com.jdelza.model.weapons;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.entities.Entity;
import com.jdelza.utils.enums.DamageType;
import com.jdelza.utils.enums.WeaponType;
import com.jdelza.utils.interfaces.Damageable;
import com.jdelza.utils.interfaces.Damager;

/**
 * Weapon represent the type of weapon used by enemies or player
 */
public class Weapon extends Entity implements Damager {

    private DamageType attackPoints;
    private WeaponType weapon;

    /**
     * Constructor
     * @param position      coordinates of weapon
     * @param weapon        type of weapon
     */
    public Weapon(Coordinates position, WeaponType weapon) {
        super(position);
        this.weapon = weapon;
        this.attackPoints = weapon == WeaponType.SWORD ? DamageType.NORMAL : DamageType.LIGHT;

    }

    //Get methods
    public DamageType getAttackPoints() {
        return attackPoints;
    }

    @Override
    public void toDamage(Damageable damageable) {
        //When a collision occurs between sword and game characters, points are deducted
        damageable.takeDamage(this.attackPoints.getDamage());
    }

}
