package com.jdelza.utils.events;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.model.weapons.Weapon;
import com.jdelza.utils.enums.Directions;
import com.jdelza.utils.enums.StartEndStatus;

public class PlayerAttack {

    private Directions attackDirection;
    private boolean throwWeapon;
    private StartEndStatus status;

    private Weapon playerWeapon;

    public PlayerAttack(/*Directions attackDirection*/ Weapon playerWeapon , boolean throwWeapon, StartEndStatus status /* Coordinates swordCoordinates*/) {
        this.attackDirection = playerWeapon.getWeaponDirection();
        this.throwWeapon = throwWeapon;
        this.status = status;
        this.playerWeapon = playerWeapon;
    }

    public Directions getAttackDirection() {
        return attackDirection;
    }

    public boolean isThrowWeapon() {
        return throwWeapon;
    }

    public Weapon getPlayerWeapon() {
        return playerWeapon;
    }
}
