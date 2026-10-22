package com.jdelza.utils.interfaces;

import com.jdelza.model.entities.Coordinates;
import com.jdelza.utils.enums.DamageType;

public interface Damageable {
    void takeDamage(DamageType damageType);
    Coordinates getPosition();
}
