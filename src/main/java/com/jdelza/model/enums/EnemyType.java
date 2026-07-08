package com.jdelza.model.enums;

public enum EnemyType {

    OCTOROK(1.0, 0.5),
    MOLBLIN(2.0, 0.5),
    TEKTITE(1.0, 0.5);


    private final double lifePoints;
    private final double collisionPoints;

    EnemyType(double lifePoints, double attackPoints) {
        this.lifePoints = lifePoints;
        this.collisionPoints = attackPoints;
    }

    public double getLifePoints() { return lifePoints; }
    public double getCollisionPoints() { return collisionPoints; }

}
