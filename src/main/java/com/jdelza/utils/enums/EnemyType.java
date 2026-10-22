package com.jdelza.utils.enums;

public enum EnemyType {

    OCTOROK("OKTOROK",0.5, DamageType.LIGHT, WeaponType.ROCK),
    MOLBLIN("MOLBLIN", 2.0, DamageType.LIGHT, WeaponType.SPEAR ),
    TEKTITE("TEKTITE",0.5, DamageType.LIGHT, WeaponType.NONE);

    private String name;
    private final double lifePoints;
    private final DamageType collisionDamage;
    private WeaponType weapon;
    EnemyType(String name, double lifePoints, DamageType collisionDamage, WeaponType weapon) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.collisionDamage = collisionDamage;
        this.weapon = weapon;
    }

    public double getLifePoints() { return lifePoints; }
    public DamageType getCollisionDamage() { return collisionDamage; }
    public WeaponType getWeapon() {return weapon;}
    public String getName() {return name;}
}
