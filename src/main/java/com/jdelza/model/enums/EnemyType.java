package com.jdelza.model.enums;

public enum EnemyType {

    OCTOROK(1.0, 0.5, WeaponType.ROCK),
    MOLBLIN(2.0, 0.5, WeaponType.SPEAR ),
    TEKTITE(1.0, 0.5, WeaponType.NONE);


    private final double lifePoints;
    private final double contactDamage;
    private WeaponType weapon;
    EnemyType(double lifePoints, double attackPoints, WeaponType weapon) {
        this.lifePoints = lifePoints;
        this.contactDamage = attackPoints;
        this.weapon = weapon;
    }

    public double getLifePoints() { return lifePoints; }
    public double getContactDamage() { return contactDamage; }
    public WeaponType getWeapon() {return weapon;}
}
