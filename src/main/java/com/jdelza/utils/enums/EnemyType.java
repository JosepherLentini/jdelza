package com.jdelza.utils.enums;

public enum EnemyType {

    OCTOROK("OKTOROK",1.0, 0.5, WeaponType.ROCK),
    MOLBLIN("MOLBLIN", 2.0, 0.5, WeaponType.SPEAR ),
    TEKTITE("TEKTITE",1.0, 0.5, WeaponType.NONE);

    private String name;
    private final double lifePoints;
    private final double contactDamage;
    private WeaponType weapon;
    EnemyType(String name, double lifePoints, double attackPoints, WeaponType weapon) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.contactDamage = attackPoints;
        this.weapon = weapon;
    }

    public double getLifePoints() { return lifePoints; }
    public double getContactDamage() { return contactDamage; }
    public WeaponType getWeapon() {return weapon;}
    public String getName() {return name;}
}
