package com.jdelza.model.enums;

public enum WeaponType {
    NONE(DamageType.NULL), SWORD(DamageType.NORMAL), ROCK(DamageType.LIGHT), SPEAR(DamageType.LIGHT);

    private DamageType weaponAttackPoints;

    WeaponType(DamageType weaponAttackPoints){
        this.weaponAttackPoints = weaponAttackPoints;
    }

}
