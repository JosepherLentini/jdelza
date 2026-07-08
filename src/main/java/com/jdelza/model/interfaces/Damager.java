package com.jdelza.model.interfaces;

import com.jdelza.model.characters.GameCharacter;

public interface Damager {
    void toDamage(Damageable damageable, double attackPoints);
}
