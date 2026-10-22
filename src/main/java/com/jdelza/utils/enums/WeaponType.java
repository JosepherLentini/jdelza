package com.jdelza.utils.enums;

public enum WeaponType {
    NONE(DamageType.NULL), SWORD(DamageType.NORMAL), ROCK(DamageType.LIGHT), SPEAR(DamageType.LIGHT);

    private final DamageType damageType;

    WeaponType(DamageType damageType){
        this.damageType = damageType;
    }

    public DamageType getDamageType() {
        return damageType;
    }
}
