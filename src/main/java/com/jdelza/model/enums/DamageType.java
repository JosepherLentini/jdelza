package com.jdelza.model.enums;

public enum DamageType {
    LIGHT(0.5),
    NORMAL(1.0),
    STRONG(2.0),
    FATAL(4.0);

    private final double damage;

    DamageType(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }
}