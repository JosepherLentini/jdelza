package com.jdelza.model.interfaces;

import com.jdelza.model.characters.GameCharacter;
import com.jdelza.model.enums.WeaponType;

public interface Damager {
    void toDamage(Damageable damageable);
}
